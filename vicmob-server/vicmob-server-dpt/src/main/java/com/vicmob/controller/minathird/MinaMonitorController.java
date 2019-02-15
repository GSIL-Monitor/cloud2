package com.vicmob.controller.minathird;

import beans.WxInfoType;
import com.vicmob.entity.MinaThird;
import com.vicmob.service.minathird.MinaThirdService;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jeewx.api.mp.aes.AesException;
import org.jeewx.api.mp.aes.WXBizMsgCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import utils.WxUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * 微信推送事件监听控制类
 * @author ziv
 * @date 2019-01-22
 */
@ApiIgnore
@RestController
@RequestMapping(value = "a/mina/releasewx")
@Slf4j
public class MinaMonitorController {

    @Value("${mina.token}")
    private String COMPONENT_TOKEN;

    @Value("${mina.appId}")
    private String COMPONENT_APP_ID;

    @Value("${mina.encodingKey}")
    private String COMPONENT_ENCODING_KEY;

    @Resource
    private MinaThirdService minaThirdService;

    /**
     * 微信授权事件推送处理
     * @param request 请求参数
     * @param response 响应参数
     * @throws IOException
     */
    @PostMapping(value = "/event")
    public void acceptAuthorizeEvent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("接收到授权事件推送");
        // 解析XML
        Element element = resolverXML(request);
        if (!StringUtils.isEmpty(element)) {
            String infoType = element.elementText("InfoType");
            if (!infoType.isEmpty()) {
                if (infoType.equals(WxInfoType.TICKET)) {
                    log.info("ticket更新");
                    String ticket = element.elementText("ComponentVerifyTicket");
                    log.info(ticket);
                    // 查询第三方信息
                    MinaThird thirdInfo = minaThirdService.getByAppId(COMPONENT_APP_ID);
                    if (StringUtils.isEmpty(thirdInfo)) {
                        // 保存第三方平台信息
                        thirdInfo.setAppId(COMPONENT_APP_ID);
                        thirdInfo.setEncodingKey(COMPONENT_ENCODING_KEY);
                        thirdInfo.setToken(COMPONENT_TOKEN);
                        thirdInfo.setTicket(ticket);
                    } else {
                        // 更新ticket
                        thirdInfo.setTicket(ticket);
                        minaThirdService.updateTicket(thirdInfo);
                    }


                }
            }
            output(response, "success");
        }
    }

    /**
     * 处理微信消息与事件推送
     * @param request 请求参数
     * @param response 响应参数
     * @param appID 事件相关appID
     */
    @PostMapping(value = "{appID}/event")
    public void acceptMessageAndEvent(HttpServletRequest request, HttpServletResponse response, @PathVariable String appID) {
        log.info("接收到微信消息事件");
        log.info("时间相关APPID：" + appID);
        // 解析XML
        Element element = resolverXML(request);
        if (!StringUtils.isEmpty(element)) {
            String toUserName = element.elementText("ToUserName");
            String fromUserName = element.elementText("FromUserName");
            String createTime = element.elementText("CreateTime");
            String msgType =  element.elementText("MsgType");

            if (!StringUtils.isEmpty(msgType)) {
                log.info("消息类型：" + msgType);
                if (msgType.equals("event")) {
                    String event = element.elementText("Event");
                    if ("weapp_audit_success".equals(event)) {
                        // TODO 发布小程序
                    }

                    if ("weapp_audit_fail".equals(event)) {
                        // TODO 小程序发布失败
                    }

                }
            }

            String replyMsg = "<xml>"
                    + "<ToUserName><![CDATA["+ fromUserName +"]]></ToUserName>"
                    + "<FromUserName><![CDATA["+ toUserName +"]]></FromUserName>"
                    + "<CreateTime>"+ createTime +"</CreateTime>"
                    + "<MsgType><![CDATA[transfer_customer_service]]></MsgType>"
                    + "</xml>";
            try {
                String nonce = request.getParameter("nonce");
                WXBizMsgCrypt crypt = new WXBizMsgCrypt(COMPONENT_TOKEN, COMPONENT_ENCODING_KEY, COMPONENT_APP_ID);
                String msg = crypt.encryptMsg(replyMsg, createTime.toString(), nonce);
                output(response, msg);
            } catch (AesException e) {
                log.error("微信第三方平台配置异常");
            } catch (IOException e) {
                log.info("回复微信失败");
            }
        }
    }

    /**
     * 解析微信XML消息
     * @param request 请求参数
     * @return Element XML文本
     */
    private Element resolverXML(HttpServletRequest request) {
        // 随机数
        String nonce = request.getParameter("nonce");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 签名用于验证是否是微信消息
        String signature = request.getParameter("signature");
        // 消息体签名，用于验证消息体的正确性
        String msgSignature = request.getParameter("msg_signature");
        log.info("nonce:" + nonce);
        log.info("timestamp:" + timestamp);
        log.info("signature:" + signature);
        log.info("msgSignature:" + msgSignature);
        // 初始化返回值
        Element element = null;
        if (checkSignature(COMPONENT_TOKEN, signature, timestamp, nonce)) {
            try {
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = request.getReader();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                reader.close();
                // 解密XML文本
                WXBizMsgCrypt crypt = new WXBizMsgCrypt(COMPONENT_TOKEN, COMPONENT_ENCODING_KEY, COMPONENT_APP_ID);

                String xml = crypt.decryptMsg(msgSignature, timestamp, nonce, builder.toString());
                log.info("微信发送的XML：" + xml);
                // 解析XML
                Document document = DocumentHelper.parseText(xml);
                element = document.getRootElement();
            } catch (IOException e) {
                log.error("解密失败");
            } catch (AesException e) {
                log.error("微信第三方平台配置异常");
            } catch (DocumentException e) {
                log.error("解析XML文本失败");
            }
        } else {
            log.info("验证未通过");
        }

        return element;
    }


    /**
     * 微信消息加解密验证
     * @param componentToken 第三方平台消息校验token
     * @param signature 签名串
     * @param timestamp 时间戳
     * @param nonce 随机串
     * @return boolean 是/否
     */
    public static boolean checkSignature(String componentToken, String signature, String timestamp, String nonce) {
        boolean flag = false;
        if (signature != null && !signature.equals("") && timestamp != null && !timestamp.equals("") && nonce != null && !nonce.equals("")) {
            String sha1 = "";
            String[] ss = new String[] {componentToken, timestamp, nonce};
            for (String str : ss) {
                System.out.println(str);
            }
            Arrays.sort(ss);
            for (String s : ss) {
                sha1 += s;
            }

            sha1 = WxUtils.SHA1(sha1);

            if (sha1.equals(signature)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 回复微信消息
     * @param response 响应参数
     * @param msg 回复内容
     * @throws IOException
     */
    public void output(HttpServletResponse response, String msg) throws IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.write(msg);
        printWriter.flush();
    }

}
