package com.vicmob.service.minathird;

import com.vicmob.entity.MinaThird;
import net.sf.json.JSONObject;
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.third.model.ApiGetAuthorizerRet;
import org.jeewx.api.third.model.ApiGetAuthorizerRetAuthorizer;

/**
 * 小程序第三方业务逻辑接口
 * @author ziv
 * date 2019-01-24
 */
public interface MinaThirdService {

    /**
     * 通过appID获取小程序第三方平台信息
     * @param appId 第三方平台appID
     * @return MinaThird 第三方平台信息
     */
    MinaThird getByAppId(String appId);

    /**
     * 更新ticket
     * @param minaThird 第三方平台信息
     */
    void updateTicket(MinaThird minaThird);

    /**
     * 获取预授权码
     * @param appId 小程序第三方appId
     * @return String 预授权码
     * @throws WexinReqException
     */
    String getPreAuthCode(String appId) throws WexinReqException;

    /**
     * 获取授权小程序接口调用凭证
     * @param appId 第三方平台appId
     * @param authorizationCode 授权码
     * @return JSONObject 授权小程序接口调用凭证
     * @throws WexinReqException
     */
    JSONObject getAuthorInfo(String appId, String authorizationCode) throws WexinReqException;

    /**
     * 获取授权小程序基本信息
     * @param thirdAppId 第三方平台appId
     * @param authorAppId 授权小程序appId
     * @return ApiGetAuthorizerRet 授权小程序基本信息
     * @throws WexinReqException
     */
    ApiGetAuthorizerRet getAuthMinaInfo(String thirdAppId, String authorAppId) throws WexinReqException;

    /**
     * 获取accessToken
     * @param appId 第三方平台appId
     * @return String accessToken
     * @throws WexinReqException
     */
    String getAccessToken(String appId) throws WexinReqException;
}
