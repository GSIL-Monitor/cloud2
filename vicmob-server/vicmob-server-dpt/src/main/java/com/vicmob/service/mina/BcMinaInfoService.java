package com.vicmob.service.mina;

import com.vicmob.entity.BcMinaInfo;
import net.sf.json.JSONObject;
import org.jeewx.api.third.model.ApiGetAuthorizerRet;
import org.jeewx.api.third.model.ApiGetAuthorizerRetAuthorizer;

/**
 * 炫销宝用户小程序业务逻辑接口
 * @author ziv
 * @date 2019-02-11
 */
public interface BcMinaInfoService {

    /**
     * 通过userId获取用户小程序信息
     * @param userId 用户主键
     * @return BcMinaInfo 用户小程序信息
     */
    BcMinaInfo getByUserId(int userId);

    /**
     * 通过appId获取授权小程序数量
     * @param appId 查询的appId
     * @return int 授权小程序数量
     */
    int getAuthorNumByAppId(String appId);

    /**
     * 保存用户小程序信息
     * @param userId 用户主键
     * @param authorInfo 小程序接口调用凭证
     * @param authInfo 授权小程序基本信息
     */
    BcMinaInfo saveInfo(int userId, JSONObject authorInfo, ApiGetAuthorizerRet authInfo);

    /**
     * 提交小程序代码
     * @param minaId 小程序ID
     * @param userName 用户名称
     * @return boolean true-提交成功 false-提交失败
     */
    boolean submitMinaCode(int minaId, String userName);

    /**
     * 通过minaId获取小程序信息
     * @param minaId 小程序id
     * @return 授权小程序基本信息
     */
    BcMinaInfo get(String minaId);
}
