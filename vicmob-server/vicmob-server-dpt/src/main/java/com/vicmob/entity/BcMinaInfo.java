package com.vicmob.entity;

import lombok.Data;

/**
 * 炫销宝用户小程序实体类
 * @author ziv
 * @date 2019-02-11
 */
@Data
public class BcMinaInfo {

    /**
     * 主键（授权小程序id）
     */
    private Integer minaId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 授权小程序的appId
     */
    private String appId;

    /**
     * 授权小程序的appSecret
     */
    private String appSecret;

    /**
     * 授权小程序昵称
     */
    private String nickName;

    /**
     * 授权小程序头像
     */
    private String headImg;

    /**
     * 授权小程序微信原始ID
     */
    private String userName;

    /**
     * 授权方认证类型，-1代表未认证，0代表微信认证
     */
    private String verifyType;

    /**
     * 默认为0（代表小程序）
     */
    private String serviceType;

    /**
     * 授权小程序access_token
     */
    private String accessToken;

    /**
     * access_token到期时间
     */
    private Integer accessTokenEndTime;

    /**
     * 授权小程序刷新令牌
     */
    private String refreshToken;

    /**
     * 授权状态：1-授权 2-未授权
     */
    private Integer authorState;

    /**
     * 小程序二维码
     */
    private String qrCode;
}
