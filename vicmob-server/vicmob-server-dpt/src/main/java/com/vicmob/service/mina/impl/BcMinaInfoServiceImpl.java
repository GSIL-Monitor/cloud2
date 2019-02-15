package com.vicmob.service.mina.impl;

import com.vicmob.entity.BcMinaInfo;
import com.vicmob.entity.BcStoreConfig;
import com.vicmob.entity.BcSysUser;
import com.vicmob.entity.MinaTemplate;
import com.vicmob.mapper.finance.BcMinaProductMapper;
import com.vicmob.mapper.mina.BcMinaInfoMapper;
import com.vicmob.mapper.minathird.MinaTemplateMapper;
import com.vicmob.mapper.web.BcStoreConfigMapper;
import com.vicmob.mapper.web.BcSysUserMapper;
import com.vicmob.service.mina.BcMinaInfoService;
import com.vicmob.service.minathird.MinaThirdService;
import com.vicmob.utils.MinaThirdUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.third.model.ApiAuthorizerToken;
import org.jeewx.api.third.model.ApiGetAuthorizerRet;
import org.jeewx.api.third.model.ApiGetAuthorizerRetAuthorizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import utils.AesEncrypt;
import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 炫销宝用户小程序业务逻辑实现类
 * @author ziv
 * @date 2019-2-11
 */
@Slf4j
@Service
@Transactional(readOnly = true, rollbackFor = SQLException.class)
public class BcMinaInfoServiceImpl implements BcMinaInfoService {

    /**
     * 第三方平台appId
     */
    @Value("${mina.appId}")
    private String thirdAppId;

    /**
     * request合法域名
     */
    @Value("${requestDomain}")
    private String requestDomain;

    /**
     * socket合法域名
     */
    @Value("${wsRequestDomain}")
    private String wsRequestDomain;

    /**
     * uploadFile合法域名
     */
    @Value("${uploadDomain}")
    private String uploadDomain;

    /**
     * downloadFile合法域名
     */
    @Value("${downloadDomain}")
    private String downloadDomain;

    /**
     * 小程序业务域名
     */
    @Value("${webViewDomain}")
    private String webViewDomain;

    /**
     * 小程序导航栏配置
     */
    @Value("${tabBar}")
    private String tabBar;

    /**
     * 导航栏商城索引
     */
    @Value("${tabIndex}")
    private String tabIndex;

    /**
     * 金融炫销宝导航栏
     */
    @Value("${financialTabBar}")
    private String financialTabBar;

    //
    /**
     * 用户-金融版-中国银行
     */
    private static final String USER_VERSION_BOC = "BOC";

    /**
     * 用户-金融版-中国银行
     */
    private static final String USER_VERSION_ICBC = "ICBC";

    /**
     * 定义其他商城类小程序
     */
    private static final int OTHER_MALL_TYPE = 2;

    @Resource
    private MinaThirdService minaThirdService;

    @Resource
    private BcMinaInfoMapper minaInfoMapper;

    @Resource
    private MinaTemplateMapper minaTemplateMapper;

    @Resource
    private BcStoreConfigMapper storeConfigMapper;

    @Resource
    private BcSysUserMapper userMapper;

    @Resource
    private BcMinaProductMapper minaProductMapper;

    @Override
    public BcMinaInfo getByUserId(int userId) {
        return minaInfoMapper.getByUserId(userId);
    }

    @Override
    public int getAuthorNumByAppId(String appId) {
        return minaInfoMapper.getAuthorNumByAppId(appId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BcMinaInfo saveInfo(int userId, JSONObject authorInfo, ApiGetAuthorizerRet authInfo) {
        BcMinaInfo userMinaInfo = minaInfoMapper.getByUserId(userId);
        BcMinaInfo minaInfo = authorInfoToMinaInfo(authorInfo, authInfo);
        minaInfo.setUserId(userId);
        // 判断新建还是更新
        if (StringUtils.isEmpty(userMinaInfo)) {
            minaInfoMapper.insert(minaInfo);
        } else {
            minaInfoMapper.renew(minaInfo);
        }
        return minaInfo;
    }

    /**
     * 获取授权小程序调用凭证超时秒数
     * @return
     */
    private Integer getAccessTokenEndTime() {
        // 毫秒进制
        int millSecondToSecond = 1000;
        // 两小时秒数
        int twoHourSecond = 7200;
        int expireTime = (int)(System.currentTimeMillis()/millSecondToSecond) + twoHourSecond;

        return Integer.valueOf(expireTime);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean submitMinaCode(int minaId, String userName) {
        log.info("开始发布处理");
        boolean successFlag = true;
        try {
            // 获取授权小程序信息
            BcMinaInfo minaInfo = getMinaAccessToken(minaId);
            // 修改服务器域名
            MinaThirdUtils.modifyDomain(minaInfo.getAccessToken(), requestDomain.split(","), wsRequestDomain.split(","),
                    uploadDomain.split(","), downloadDomain.split(","));
            // 修改业务域名
            MinaThirdUtils.setWebViewDomain(minaInfo.getAccessToken(), webViewDomain.split(","));
            // 获取小程序模板
            MinaTemplate minaTemplate = minaTemplateMapper.getTemplate();
            // 获取第三方自定义配置
            String extJson = getExtConfig(minaInfo);
            MinaThirdUtils.commitCode(minaInfo.getAccessToken(), minaTemplate.getTemplateId(),
                    extJson, "1.0", "one button publishing");
        } catch (WexinReqException e) {
            log.error("提交代码异常");
            log.error(e.getMessage());
            successFlag = false;
        }
        log.info("结束发布处理");
        return successFlag;
    }

    /**
     * 获取授权小程序调用凭证
     * @param minaId 小程序ID
     * @return BcMinaInfo 授权小程序信息，且调用凭证未过期
     * @throws WexinReqException
     */
    private BcMinaInfo getMinaAccessToken(int minaId) throws WexinReqException {
        BcMinaInfo minaInfo = minaInfoMapper.getByMinaId(minaId);
        // 毫秒进制
        int millSecondToSecond = 1000;
        // 当前秒数
        int currentSeconds = (int)(System.currentTimeMillis()/millSecondToSecond);
        // 判断调用凭证是否过期
        if (currentSeconds >= minaInfo.getAccessTokenEndTime().intValue()) {
            // 过期刷新
            String thirdAccessToken = minaThirdService.getAccessToken(thirdAppId);
            ApiAuthorizerToken apiAuthorizerToken = new ApiAuthorizerToken();
            apiAuthorizerToken.setAuthorizer_appid(minaInfo.getAppId());
            apiAuthorizerToken.setAuthorizer_refresh_token(minaInfo.getRefreshToken());
            apiAuthorizerToken.setComponent_appid(thirdAppId);
            try {
                JSONObject token = MinaThirdUtils.getAccessToken(thirdAccessToken, thirdAppId, minaInfo.getAppId(), minaInfo.getRefreshToken());
                log.info(token.toString());
                minaInfo.setAccessToken(token.getString("authorizer_access_token"));
                minaInfo.setRefreshToken(token.getString("authorizer_refresh_token"));
                int expireTime = Integer.parseInt(token.getString("expires_in"));
                int tokenExpire = (int)(System.currentTimeMillis()/millSecondToSecond) + expireTime;
                minaInfo.setAccessTokenEndTime(tokenExpire);
                minaInfoMapper.updateAccessToken(minaInfo);
            } catch (WexinReqException e) {
                log.error("获取第三方接口调用凭证出错");
                log.error(e.getMessage());
                throw new WexinReqException("获取第三方接口调用凭证出错");
            }
        }
        return minaInfo;
    }

    /**
     * 授权信息转小程序信息
     * @param authorInfo 授权调用凭证
     * @param authInfo 授权小程序基本信息
     * @return BcMinaInfo 用户小程序信息
     */
    private BcMinaInfo authorInfoToMinaInfo(JSONObject authorInfo, ApiGetAuthorizerRet authInfo) {
        BcMinaInfo minaInfo = new BcMinaInfo();
        ApiGetAuthorizerRetAuthorizer authMinaInfo = authInfo.getAuthorizer_info();
        minaInfo.setAppId(authorInfo.getString("authorizer_appid"));
        minaInfo.setNickName(authMinaInfo.getNick_name());
        minaInfo.setHeadImg(authMinaInfo.getHead_img());
        minaInfo.setUserName(authMinaInfo.getUser_name());
        minaInfo.setVerifyType(authMinaInfo.getVerify_type_info().getId().toString());
        minaInfo.setServiceType(authMinaInfo.getService_type_info().getId().toString());
        minaInfo.setAccessToken(authorInfo.getString("authorizer_access_token"));
        minaInfo.setAccessTokenEndTime(getAccessTokenEndTime());
        minaInfo.setRefreshToken(authorInfo.getString("authorizer_refresh_token"));
        minaInfo.setQrCode(authInfo.getQrcode_url());
        return minaInfo;
    }

    /**
     * 获取小程序第三方配置
     * @param minaInfo 小程序信息
     * @return String 第三方配置
     */
    private String getExtConfig(BcMinaInfo minaInfo) {
        // 定义第三方配置
        JSONObject extJson = new JSONObject();
        // 开启第三方配置
        extJson.put("extEnable", true);
        // 设置appId
        extJson.put("extAppid", minaInfo.getAppId());
        // 小程序页面自定义数据
        JSONObject ext = new JSONObject();
        // 加密minaId
        ext.put("minaId", AesEncrypt.aesEncryption(minaInfo.getAppId(), String.valueOf(minaInfo.getMinaId())));
        ext.put("appId", minaInfo.getAppId());
        ext.put("minaNickName", minaInfo.getNickName());
        // 导航栏和小程序跳转配置
        BcStoreConfig storeConfig = storeConfigMapper.findByUserId(minaInfo.getUserId().intValue());
        BcSysUser user = userMapper.findByUserId(minaInfo.getUserId().intValue());

        // 判断是否为金融版
        if (USER_VERSION_BOC.equals(user.getSysVersion()) || USER_VERSION_ICBC.equals(user.getSysVersion())) {
            ext.put("sysVersion", user.getSysVersion());
            // 定义appId列表
            List<String> appIdList = new ArrayList<>();
            // 查询金融跳转的appId
            String financialAppId = minaProductMapper.getAppIdList(minaInfo.getUserId().intValue());
            // 获取appId列表并加入跳转名单
            if (!StringUtils.isEmpty(financialAppId)) {
                appIdList.add(financialAppId);
            }
            // 定义导航栏对象
            JSONObject tabBar = new JSONObject();
            // 配置导航栏
            tabBar.put("list", financialTabBar);
            extJson.put("tabBar", tabBar.toString());
            // 配置跳转小程序appId
            if (appIdList.size() > 0) {
                extJson.put("navigateToMiniProgramAppIdList", appIdList);
            }
        } else {
            // 判断是否设置商城配置,未设置不做修改
            if (!StringUtils.isEmpty(storeConfig)) {
                // 判断商城类型是否为其他商城类小程序
                if (storeConfig.getMallType().intValue() == OTHER_MALL_TYPE) {
                    // 定义导航栏对象
                    JSONObject tabBar = new JSONObject();
                    // 初始化appId
                    String appId = "";
                    // 判断跳转哪种小程序
                    if (storeConfig.getMinaSelect() == 1) {
                        // 跳转微炫客小程序,获取小程序appId
                        appId = userMapper.getAppIdByName(user.getLoginName());
                        ext.put("navigaterAppId", appId);
                    } else {
                        // 跳转其他小程序
                        appId = storeConfig.getAppId();
                        ext.put("navigaterAppId", appId);
                        ext.put("navigaterPage", storeConfig.getWebsiteUrl());
                    }
                    // 转化成JSON列表
                    JSONArray tabList = JSONArray.fromObject(tabBar);
                    int index = Integer.parseInt(tabIndex);
                    // 获取商城导航栏配置
                    JSONObject storTab = JSONObject.fromObject(tabList.get(index));
                    // 修改导航栏名称
                    storTab.put("text", storeConfig.getNavigationName());
                    // 修改导航栏设置
                    tabList.set(index, storTab);
                    tabBar.put("list", tabList.toString());
                    // 配置跳转跳转小程序appId
                    String[] appIdList = {appId};
                    extJson.put("tabBar", tabBar.toString());
                    extJson.put("navigateToMiniProgramAppIdList", appIdList);
                }
            }
        }
        // 设置第三方配置
        extJson.put("ext", ext.toString());

        return extJson.toString();
    }


    @Override
    public BcMinaInfo get(String minaId){
        return minaInfoMapper.get(minaId);
    }
}
