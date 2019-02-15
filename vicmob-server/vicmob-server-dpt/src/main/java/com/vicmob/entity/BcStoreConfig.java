package com.vicmob.entity;

import lombok.Data;

/**
 * 小程序商城配置实体类
 * @author ziv
 * @date 2019-02-14
 */
@Data
public class BcStoreConfig {
    /**
     * 商城配置主键
     */
    private Integer configId;

    /**
     * 管理员Id
     */
    private Integer userId;

    /**
     * 商城类型（1：炫销宝展示商城；2：其他商城类小程序）
     */
    private Integer mallType;

    /**
     * 小程序类型（1：微炫客小程序；2：同一主体下其他小程序）
     */
    private Integer minaSelect;

    /**
     * 小程序APPID
     */
    private String appId;

    /**
     * 跳转路径
     */
    private String websiteUrl;

    /**
     * 导航栏名称
     */
    private String navigationName;

    /**
     * 选择小程序（1.微商店）
     */
    private Integer minaType;
}
