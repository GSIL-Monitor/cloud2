package com.vicmob.utils;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jeewx.api.core.common.WxstoreUtils;
import org.jeewx.api.core.exception.WexinReqException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 小程序第三方业务工具类
 * @author ziv
 * @date 2019-02-12
 */
@Slf4j
public class MinaThirdUtils {

    /**
     * 微信请求成功
     */
    private static final String WX_SUCCESS_CODE = "0";

    /**
     * 修改业务域名请求地址
     */
    private static final String MODIFY_DOMAIN = "https://api.weixin.qq.com/wxa/modify_domain?access_token=";

    /**
     * 设置小程序业务域名请求地址
     */
    private static final String SET_WEB_VIEW_DOMAIN = "https://api.weixin.qq.com/wxa/setwebviewdomain?access_token=";

    /**
     * 上传小程序代码请求地址
     */
    private static final String COMMIT = "https://api.weixin.qq.com/wxa/commit?access_token=";

    /**
     * 获取体验小程序的体验二维码请求路径
     */
    private static final String GET_QR_CODE = "https://api.weixin.qq.com/wxa/get_qrcode?access_token=";

    /**
     * 获取小程序已设置类目请求路径
     */
    private static final String GET_CATEGORY = "https://api.weixin.qq.com/wxa/get_category?access_token=";

    /**
     * 获取小程序的第三方提交代码的页面配置请求路径
     */
    private static final String GET_PAGE = "https://api.weixin.qq.com/wxa/get_page?access_token=";

    /**
     * 提交审核请求路径
     */
    private static final String SUBMIT_AUDIT = "https://api.weixin.qq.com/wxa/submit_audit?access_token=";

    /**
     * 小程序提交发布请求路径
     */
    private static final String PUBLISH = "https://api.weixin.qq.com/wxa/release?access_token=";

    /**
     * 添加模板请求路径
     */
    private static final String ADD_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/wxopen/template/add?access_token=";

    /**
     * 获取授权小程序接口调用凭证请求路径
     */
    private static final String API_AUTHORIZER_TOKEN = "https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token?component_access_token=";
    /**
     * 设置小程序服务器域名
     * @param accessToken 授权小程序调用凭证
     * @param requestDomain request合法域名
     * @param wsRequestDomain socket合法域名
     * @param uploadDomain uploadFile合法域名
     * @param downloadDomain downloadFile合法域名
     */
    public static void modifyDomain(String accessToken, String[] requestDomain, String[] wsRequestDomain,
                             String[] uploadDomain, String[] downloadDomain) {
        String url = MODIFY_DOMAIN + accessToken;

        JSONObject param = new JSONObject();
        param.put("action", "add");
        param.put("requestdomain", requestDomain);
        param.put("wsrequestdomain", wsRequestDomain);
        param.put("uploaddomain", uploadDomain);
        param.put("downloaddomain", downloadDomain);
        log.info(param.toString());

        // 发送微信请求
        JSONObject result = WxstoreUtils.httpRequest(url, "POST", param.toString());
        String errCode = result.getString("errcode");

        if (WX_SUCCESS_CODE.equals(errCode)) {
            log.info("修改服务器域名成功");
        } else {
            log.warn("修改服务器域名异常");
            log.warn(result.toString());
        }
    }

    /**
     * 设置小程序业务域名
     * @param accessToken 授权小程序调用凭证
     * @param webViewDomain 小程序业务域名
     */
    public static void setWebViewDomain(String accessToken, String[] webViewDomain) {
        String url = SET_WEB_VIEW_DOMAIN + accessToken;

        JSONObject param = new JSONObject();
        param.put("action", "add");
        param.put("webviewdomain", webViewDomain);
        log.info(param.toString());

        // 发送微信请求
        JSONObject result = WxstoreUtils.httpRequest(url, "POST", param.toString());
        String errCode = result.getString("errcode");

        if (WX_SUCCESS_CODE.equals(errCode)) {
            log.info("设置小程序业务域名成功");
        } else {
            log.warn("设置小程序业务域名异常");
            log.warn(result.toString());
        }
    }

    /**
     * 提交小程序代码
     * @param accessToken 授权小程序调用凭证
     * @param templateId 小程序模板ID
     * @param extJson 第三方自定义的配置
     * @param version 代码版本号
     * @param desc 代码描述
     * @throws WexinReqException
     */
    public static void commitCode(String accessToken, String templateId, String extJson,
                           String version, String desc) throws WexinReqException {
        String url= COMMIT + accessToken;

        // 拼接参数
        Map<String, String> mapJson = new HashMap<String, String>();
        mapJson.put("template_id", templateId);
        mapJson.put("ext_json", extJson);
        mapJson.put("user_version", version);
        mapJson.put("user_desc", desc);

        JSONObject param = JSONObject.fromObject(mapJson);
        log.info(accessToken);
        log.info("数据格式");
        log.info(param.toString());
        // 发送微信请求
        JSONObject result = WxstoreUtils.httpRequest(url, "POST", param.toString());
        String errCode = result.getString("errcode");
        if (WX_SUCCESS_CODE.equals(errCode)) {
            log.info("提交小程序代码成功");
        } else {
            log.error("提交小程序代码异常");
            log.error(result.toString());
            throw new WexinReqException("提交小程序代码出错！" + result.toString());
        }
    }

    /**
     * 获取小程序体验二维码
     * @param accessToken 授权小程序调用凭证
     * @return JSONObject 微信返回信息
     * @throws WexinReqException
     */
    public static JSONObject getQrcode(String accessToken) throws WexinReqException {
        String url = GET_QR_CODE + accessToken;
        JSONObject result = WxstoreUtils.httpRequest(url, "GET", "");

        String errCode = result.getString("errcode");
        if (WX_SUCCESS_CODE.equals(errCode)) {
            log.info("获取小程序体验二维码成功");
        } else {
            log.error("获取小程序体验二维码异常");
            log.error(result.toString());
            throw new WexinReqException("获取小程序体验二维码出错！" + result.toString());
        }
        return result;
    }

    /**
     * 获取小程序已设置的类目
     * @param accessToken 授权小程序调用凭证
     * @return JSONArray 小程序已设置的类目
     * @throws WexinReqException
     */
    public static JSONArray getCategory(String accessToken) throws WexinReqException {
        String url = GET_CATEGORY + accessToken;

        JSONObject result = WxstoreUtils.httpRequest(url, "GET", "");

        String errCode = result.getString("errcode");
        if (WX_SUCCESS_CODE.equals(errCode)) {
            log.info("获取小程序已设置的类目成功");
            return result.getJSONArray("category_list");
        } else {
            log.error("获取小程序已设置的类目异常");
            log.error(result.toString());
            throw new WexinReqException("获取小程序已设置的类目出错！:" + result.toString());
        }
    }

    /**
     * 获取小程序页面配置
     * @param accessToken 授权小程序调用凭证
     * @return JSONArray 小程序页面配置
     * @throws WexinReqException
     */
    public static JSONArray getPage(String accessToken) throws WexinReqException {
        String url = GET_PAGE + accessToken;

        JSONObject result = WxstoreUtils.httpRequest(url, "GET", "");
        String errCode = result.getString("errcode");
        if (WX_SUCCESS_CODE.equals(errCode)) {
            log.info("获取小程序页面配置成功");
            return result.getJSONArray("page_list");
        } else {
            log.error("获取小程序页面配置异常");
            log.error(result.toString());
            throw new WexinReqException("获取小程序页面配置出错！:" + result.toString());
        }
    }

    /**
     * 小程序提交审核
     * @param accessToken 授权小程序调用凭证
     * @param itemList 审核项列表
     * @return String 审核编号
     * @throws WexinReqException
     */
    public static String submitAudit(String accessToken, List<JSONObject> itemList) throws WexinReqException {
        String url = SUBMIT_AUDIT + accessToken;

        JSONObject param = new JSONObject();
        param.put("item_list", itemList);

        JSONObject result = WxstoreUtils.httpRequest(url, "POST", param.toString());

        String errCode = result.getString("errcode");
        if (WX_SUCCESS_CODE.equals(errCode)) {
            log.info("小程序提交审核成功");
            return result.getString("auditid");
        } else {
            log.error("小程序提交审核异常");
            log.error(result.toString());
            throw new WexinReqException("小程序提交审核出错！:" + result.toString());
        }
    }

    /**
     * 小程序提交发布
     * @param accessToken 授权小程序调用凭证
     * @throws WexinReqException
     */
    public static void publish(String accessToken) throws WexinReqException {
        String url = PUBLISH + accessToken;

        JSONObject param = new JSONObject();
        JSONObject result = WxstoreUtils.httpRequest(url, "POST", param.toString());

        String errCode = result.getString("errcode");
        if (WX_SUCCESS_CODE.equals(errCode)) {
            log.info("小程序提交发布成功");
        } else {
            log.error("小程序提交发布异常");
            log.error(result.toString());
            throw new WexinReqException("小程序提交发布出错！:" + result.toString());
        }
    }

    /**
     * 增加模板消息
     * @param accessToken 授权小程序调用凭证
     * @param templateId 模板标题id
     * @param keywordIdList 模板关键词列表
     * @return String 添加至帐号下的模板id
     * @throws WexinReqException
     */
    public static String addTemplate(String accessToken, String templateId, String[] keywordIdList) throws WexinReqException {
        String url= ADD_TEMPLATE + accessToken;
        JSONObject param = new JSONObject();

        param.put("id", templateId);
        param.put("keyword_id_list", keywordIdList);

        JSONObject result = WxstoreUtils.httpRequest(url, "POST", param.toString());

        String errCode = result.getString("errcode");
        if (WX_SUCCESS_CODE.equals(errCode)) {
            log.info("增加模板消息成功");
            return result.getString("page_list");
        } else {
            log.error("增加模板消息异常");
            log.error(result.toString());
            throw new WexinReqException("增加模板消息出错！" + result.toString());
        }
    }

    /**
     * 刷新授权小程序接口调用凭证
     * @param componentAccessToken 第三方接口调用凭证
     * @param componentAppId 第三方appID
     * @param authorizerAppId 授权小程序appId
     * @param refreshToken 刷新令牌
     * @return JSONObject 微信返回结果
     */
    public static JSONObject getAccessToken(String componentAccessToken, String componentAppId,
                                            String authorizerAppId, String refreshToken) throws WexinReqException {
        String url = API_AUTHORIZER_TOKEN + componentAccessToken;

        JSONObject param = new JSONObject();
        param.put("component_appid", componentAppId);
        param.put("authorizer_appid", authorizerAppId);
        param.put("authorizer_refresh_token", refreshToken);

        JSONObject result = WxstoreUtils.httpRequest(url, "POST", param.toString());
        if (result.containsKey("errcode")) {
            log.error("刷新接口调用凭证异常");
            log.error(result.toString());
            throw new WexinReqException("刷新接口调用凭证出错！" + result.toString());
        }

        log.info("刷新接口调用凭证成功");
        return result;
    }

}