package com.vicmob.service.minathird.impl;

import com.vicmob.entity.MinaThird;
import com.vicmob.mapper.minathird.MinaThirdMapper;
import com.vicmob.service.minathird.MinaThirdService;
import net.sf.json.JSONObject;
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.third.JwThirdAPI;
import org.jeewx.api.third.model.ApiComponentToken;
import org.jeewx.api.third.model.ApiGetAuthorizer;
import org.jeewx.api.third.model.ApiGetAuthorizerRet;
import org.jeewx.api.third.model.ApiGetAuthorizerRetAuthorizer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * 小程序第三方业务逻辑实现类
 * @author ziv
 * @date 2019-01-24
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MinaThirdServiceImpl implements MinaThirdService {

    @Resource
    private MinaThirdMapper minaThirdMapper;

    @Override
    public MinaThird getByAppId(String appId) {
        return minaThirdMapper.getByAppId(appId);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public void updateTicket(MinaThird minaThird) {
        minaThirdMapper.updateTicket(minaThird);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getPreAuthCode(String appId) throws WexinReqException {
        String accessToken = getAccessToken(appId);
        String preAuthCode = JwThirdAPI.getPreAuthCode(appId, accessToken);
        return preAuthCode;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject getAuthorInfo(String appId, String authorizationCode) throws WexinReqException  {
        String accessToken = getAccessToken(appId);
        // 获取授权方小程序账户信息
        JSONObject authorInfo = JwThirdAPI.getApiQueryAuthInfo(appId, authorizationCode, accessToken);
        JSONObject authorMinaInfo = authorInfo.getJSONObject("authorization_info");
        return authorMinaInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiGetAuthorizerRet getAuthMinaInfo(String thirdAppId, String authorAppId) throws WexinReqException {
        String accessToken = getAccessToken(thirdAppId);
        ApiGetAuthorizer apiGetAuthorizer =new ApiGetAuthorizer();
        apiGetAuthorizer.setComponent_appid(thirdAppId);
        apiGetAuthorizer.setAuthorizer_appid(authorAppId);
        ApiGetAuthorizerRet userBaseInfo = JwThirdAPI.apiGetAuthorizerInfo(apiGetAuthorizer, accessToken);
        return userBaseInfo;
    }

    @Override
    public String getAccessToken(String appId) throws WexinReqException {
        // 获取小程序信息
        MinaThird minaThird = getByAppId(appId);
        // 毫秒进制
        int millSecondToSecond = 1000;
        // 两小时秒数
        int twoHourSecond = 7200;
        // 当前秒数
        int currentSeconds = (int)(System.currentTimeMillis()/millSecondToSecond);
        // 判断第三方调用凭证是否过期
        if (!StringUtils.isEmpty(minaThird.getAccessToken()) || currentSeconds >= minaThird.getTonkenExpire().intValue()) {
            // 过期刷新
            ApiComponentToken apiComponentToken = new ApiComponentToken();
            apiComponentToken.setComponent_appid(minaThird.getAppId());
            apiComponentToken.setComponent_appsecret(minaThird.getAppSecret());
            apiComponentToken.setComponent_verify_ticket(minaThird.getTicket());
            String accessToken = JwThirdAPI.getAccessToken(apiComponentToken);
            int expireTime = (int)(System.currentTimeMillis()/millSecondToSecond) + twoHourSecond;
            minaThird.setAccessToken(accessToken);
            minaThird.setTonkenExpire(expireTime);
            // 更新accessToken
            minaThirdMapper.updateToken(minaThird);
        }
        return minaThird.getAccessToken();
    }
}
