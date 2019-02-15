package com.vicmob.controller.minathird;

import com.vicmob.entity.BcMinaInfo;
import com.vicmob.service.mina.BcMinaInfoService;
import com.vicmob.service.minathird.MinaThirdService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.third.JwThirdAPI;
import org.jeewx.api.third.model.ApiGetAuthorizerRet;
import org.jeewx.api.third.model.ApiGetAuthorizerRetAuthorizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 小程序授权业务控制层
 * @author ziv
 * @date 2019-01-22
 */
@ApiIgnore
@Slf4j
@Controller
@RequestMapping(value = "mina")
public class MinaAuthorController {

    @Value("${mina.appId}")
    private String COMPONENT_APP_ID;

    @Value("${mina.encodingKey}")
    private String COMPONENT_ENCODING_KEY;

    @Value("${mina.token}")
    private String COMPONENT_TOKEN;

    @Value("${serviceUrl}")
    private String SERVICE_URL;

    @Resource
    private MinaThirdService minaThirdService;

    @Resource
    private BcMinaInfoService minaInfoService;

    /**
     * 用户小程序已授权
     */
    private static final int AUTHOR = 1;

    /**
     * 发起授权，跳转授权页面
     * @param model 前后台传值参数
     * @return String 发起授权页面路径
     */
    @RequestMapping(value = "preAuthor")
    public String preAuthor(Model model) {
        // 初始化授权开关
        boolean authorSwitch = false;

        // TODO: 获取用户信息,目前写成固定值,后续增加用户验证
        int userId = 9550;
        // 获取用户小程序
        BcMinaInfo userMina = minaInfoService.getByUserId(userId);
        // 判断用户是否存在小程序
        if (!StringUtils.isEmpty(userMina)) {
            // 判断用户是否授权
            if (userMina.getAuthorState().intValue() != AUTHOR) {
                // 用户未授权
                authorSwitch = true;
            }
        } else {
            // 用户已授权
            authorSwitch = true;
        }

        model.addAttribute("hello", "ziv");
        model.addAttribute("authorSwitch", authorSwitch);

        if (authorSwitch) {
            String authorUrl = SERVICE_URL + "/VicmobBusinessCard/mina/author";
            model.addAttribute("authorUrl", authorUrl);
        }

        return "author";
    }

    /**
     * 发起微信小程序授权
     * @return String 重定向到微信授权页面
     */
    @RequestMapping(value = "author")
    public String author() {
        // 回调url
        String redirectUri = SERVICE_URL + "/VicmobBusinessCard/mina/authorization/authorCallback";
        StringBuilder builder = new StringBuilder();
        builder.append("https://mp.weixin.qq.com/cgi-bin/componentloginpage");
        try {
            // 获取预授权码
            String preAuthCode = minaThirdService.getPreAuthCode(COMPONENT_APP_ID);
            builder.append("?component_appid=")
                    .append(COMPONENT_APP_ID)
                    .append("&pre_auth_code=")
                    .append(preAuthCode)
                    .append("&redirect_uri=")
                    .append(redirectUri);
        } catch (WexinReqException e) {
            log.error("获取预授权码异常");
        }

        return "redirect:" + builder.toString();
    }

    /**
     * 微信授权回调
     * @param request 请求参数
     * @param model 前后台传值参数
     * @return String 授权成功页面
     */
    @RequestMapping(value = "authorization/authorCallback")
    public String authorCallback(HttpServletRequest request, Model model) {
        // 初始化结果状态
        boolean successFlag = true;
        // 获取授权码
        String authorizationCode = request.getParameter("auth_code");
        try {
            // 获取授权小程序接口调用凭据
            JSONObject authorInfo = minaThirdService.getAuthorInfo(COMPONENT_APP_ID, authorizationCode);
            // 获取授权小程序appId
            String authorAppId = authorInfo.getString("authorizer_appid");
            int authorNum = minaInfoService.getAuthorNumByAppId(authorAppId);
            if (authorNum > 0) {
                // 小程序已授权
                successFlag = false;
                model.addAttribute("msg", "该小程序账号已授权");
            } else {
                // 获取授权小程序基本信息
                ApiGetAuthorizerRet authInfo = minaThirdService.getAuthMinaInfo(COMPONENT_APP_ID, authorAppId);
                // TODO: 获取用户信息,目前写成固定值,后续增加用户验证
                int userId = 9550;
                // 保存用户小程序信息
                BcMinaInfo minaInfo = minaInfoService.saveInfo(userId, authorInfo, authInfo);
                model.addAttribute("minaInfo", minaInfo);
            }
        } catch (WexinReqException e) {
            log.error("获取授权小程序接口调用凭证异常，或者获取授权小程序基本信息异常");
            successFlag = false;
            model.addAttribute("msg", "微信接口调用异常");
        }

        log.info(model.asMap().toString());
        model.addAttribute("successFlag", successFlag);
        log.info("授权完成，返回页面");
        return "authorSuccess";
    }
}
