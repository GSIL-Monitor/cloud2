package com.vicmob.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 客户员工关系实体
 * @author rivky
 * @date 2019-01-29
 */
@Data
public class BcCustomerEmployee {
    /**
     * 关系主键id
     */
    private Integer relationshipId;

    /**
     * 客户id
     */
    private Integer customerId;

    /**
     * 员工id
     */
    private Integer employeeId;

    /**
     * 客户备注名
     */
    private String custNickname;

    /**
     * 性别（1：男；2：女）
     */
    private Integer gender;

    /**
     * 客户手机号
     */
    private String custPhone;

    /**
     * 客户邮箱
     */
    private String custEmail;

    /**
     * 客户公司
     */
    private String custCompany;

    /**
     * 客户职位
     */
    private String custPosition;

    /**
     * 客户详细地址
     */
    private String custAddress;

    /**
     * 客户备注
     */
    private String custRemarks;

    /**
     * 是否屏蔽消息推送（0：屏蔽1：未屏蔽）
     */
    private Integer isShield;

    /**
     * 客户标签（逗号分隔）
     */
    private String custLabel;

    /**
     * 预计成交时间
     */
    private Date transactionTime;

    /**
     * 预计成交率
     */
    private BigDecimal transactionPer;

    /**
     * 客户是否屏蔽该员工（0：屏蔽1：未屏蔽）
     */
    private Integer custIsShield;

    /**
     * 客户是否对员工点赞（1：点赞0：未点赞）
     */
    private Integer custIsLike;

    /**
     * 客户是否对员工保存（1：报存0：未保存）
     */
    private Integer cusIsSave;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 微信头像
     */
    private String avatar;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 最后活动时间
     */
    private Date activityTime;

    /**
     * 用于手机端搜索条件 1：最后活动时间 2：新增客户
     */
    private Integer searchType;

    /**
     * 用户手机端搜索关键字
     */
    private String searchWord;

    /**
     * 客户跟进进度
     */
    private String custFollowup;

    /**
     * 预计成交时间String类型
     */
    private String transactionTimeString;

    /**
     * 最后活动时间String类型
     */
    private String activityTimeString;

    /**
     * 新增客戶數
     */
    private Integer newCust;

    /**
     * 是否交接
     */
    private Integer isHandover;

    /**
     * 能否设置预计成交时间 1:不能设置
     */
    private Integer isSetTransactionTime;

    /**
     * 客户授权的手机号
     */
    private String authorizationPhone;

    /**
     * 客户端消息是否已读（0已读，大于0表示未读多少条）
     */
    private Integer isRead;
}
