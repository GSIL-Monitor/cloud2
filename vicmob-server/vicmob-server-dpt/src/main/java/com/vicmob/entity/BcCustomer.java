package com.vicmob.entity;

import lombok.Data;

import java.util.Date;

/**
 * 客户实体类
 * @author  ricky
 * @date 2019-01-29
 */
@Data
public class BcCustomer {
    /**
     * 客户id
     */
    private Integer customerId;

    /**
     * 平台id
     */
    private Integer minaId;

    /**
     * openid
     */
    private String openId;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 性别（0：没有设置；1：男；2：女）
     */
    private Integer sex;

    /**
     * 微信头像
     */
    private String avatar;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 聊天状态：0-否 1-是
     */
    private Integer chatState;

    /**
     * 消息推送状态：0-未发送 1-已发送
     */
    private Integer messageState;
}
