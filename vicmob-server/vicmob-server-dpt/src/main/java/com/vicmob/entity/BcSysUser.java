package com.vicmob.entity;

import lombok.Data;

import java.util.Date;

/**
 * 炫销宝用户实体类
 * @author ziv
 * @date 2019-02-14
 */
@Data
public class BcSysUser {
    /**
     * 主键（用户ID）
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String loginPassword;

    /**
     * 联系人
     */
    private String contactName;

    /**
     * 联系方式
     */
    private String contactPhone;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 企业地址
     */
    private String companyAddress;

    /**
     * 最后登录时间
     */
    private Date loginDate;

    /**
     * 是否可登陆
     */
    private String loginFlag;

    /**
     * 创建时间
     */
    private Date joinTime;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 到期时间
     */
    private Date endTime;

    /**
     * 删除标记
     */
    private Integer deleteFlag;

    /**
     * 成交率是否开启（0：开启1：关闭）
     */
    private Integer dealIsOpen;

    /**
     * 新增客户是否开启（0：开启1：关闭）
     */
    private Integer newCustIsOpen;

    /**
     * 咨询客户是否开启（0：开启1：关闭）
     */
    private Integer conCustIsOpen;

    /**
     * 跟进客户折线图是否开启（0：开启1：关闭）
     */
    private Integer folCustIsOpen;

    /**
     * 跟进客户饼图是否开启（0：开启1：关闭）
     */
    private Integer picCustIsOpen;

    /**
     * 活跃度是否开启（0：开启1：关闭）
     */
    private Integer activeIsOpen;

    /**
     * 客户互动是否开启（0：开启1：关闭）
     */
    private Integer interIsOpen;

    /**
     * 提交订单是否开启（0：开启1：关闭）
     */
    private Integer subOrderIsOpen;

    /**
     * 成交订单是否开启（0：开启1：关闭）
     */
    private Integer dealOrderIsOpen;

    /**
     * 退款订单是否开启（0：开启1：关闭）
     */
    private Integer refundOrderIsOpen;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 是否带支付（0：不带支付；1：带支付）
     */
    private Integer isPayment;

    /**
     * 名片数
     */
    private Integer cardsNum;

    /**
     * 系统版本（中行：BOC;工行：ICBC）
     */
    private String sysVersion;

}
