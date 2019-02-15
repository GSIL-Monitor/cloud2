package com.vicmob.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 员工实体类
 * @author ricky
 * @date 2019-01-29
 */
@Data
public class BcEmployee {
    /**
     * 员工id
     */
    private Integer employeeId;

    /**
     * 管理员id
     */
    private Integer userId;

    /**
     * 小程序ID
     */
    private Integer minaId;

    /**
     * 微信用户id
     */
    private String wxUserId;

    /**
     * 一级部门id
     */
    private Integer firDepartmentId;

    /**
     * 二级部门ID
     */
    private Integer secDepartmentId;

    /**
     * 头像
     */
    private String headPortrait;

    /**
     * 员工编号
     */
    private String empNumber;

    /**
     * 员工姓名
     */
    private String empName;

    /**
     * 员工职位
     */
    private String empPosition;

    /**
     * 员工手机号
     */
    private String empPhone;

    /**
     * 员工座机
     */
    private String empTel;

    /**
     * 员工微信
     */
    private String empWeChat;

    /**
     * 员工邮箱
     */
    private String empMailbox;

    /**
     * AI雷达/名片（1：开启 0：关闭）
     */
    private Integer empAI;

    /**
     * Boss雷达（1：开启 0：关闭）
     */
    private Integer empBoss;

    /**
     * 个人介绍
     */
    private String personalDesc;

    /**
     * 员工照片
     */
    private String empWelmsg;

    /**
     * 欢迎语
     */
    private Integer deleteFlag;

    /**
     * 删除标记（0：删除；1：未删除）
     */
    private String empPhotos;

    /**
     * 是否屏蔽
     */
    private Integer custIsShield;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 是否已读
     */
    private Integer isRead;

    /**
     * 小程序码
     */
    private String qrCode;

    /**
     * 开启AI雷达/名片短信验证码
     */
    private String empCode;

    /**
     * 本地库录音路径
     */
    private String voiceUrl;

    /**
     * 微信的录音ID
     */
    private String wxVoice;

    /**
     * 点赞数
     */
    private Integer praise;

    /**
     * 浏览数
     */
    private Integer views;

    /**
     * 部门集合
     */
    private List<BcDepartment> list;

    /**
     * 部门ID结合
     */
    private String[] bcDepartmentList;

    /**
     * 部门
     */
    private String departments;

    /**
     * 上级部门
     */
    private String extDepartment;

    /**
     * 公司简称
     */
    private String nickName;

    /**
     * 企业logo
     */
    private String logoPic;

    /**
     * 企业bussinesAdddress
     */
    private String bussinesAdddress;

    /**
     * 客户Id
     */
    private Integer customerId;

    /**
     * 是否点赞
     */
    private Integer custIsLike;

    /**
     * 授权企业在微工作台（原企业号）的二维码
     */
    private String corpWxqrcode;

    /**
     * 每日员工点赞数量
     */
    private Integer dailyLikeCount;

    /**
     * 员工是否对其点赞
     */
    private Integer empIsLike;

    /**
     * 该员工当天所有客户的活跃度值（两小时统计一次）
     */
    private Integer activitynums;

    /**
     * 浏览客户
     */
    List<BcEmployeeView> bcEmployeeViews;

    /**
     * 客户数
     */
    private Integer count;

    /**
     * 查询使用（员工id；例：1,2,3）
     */
    private String empIds;

    /**
     * 查询使用（天数）
     */
    private Integer days;

    /**
     * 部门ID
     */
    private Integer wxdptId;

    /**
     * 录音时长，单位毫秒
     */
    private Integer voiceTime;

    /**
     * 上级部门拼接
     */
    private String departmentNames;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 录音路径
     */
    private String videoPath;

    /**
     * 录音图片路径
     */
    private String videoImg;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 维度
     */
    private String latitude;

    /**
     * 员工添加时间
     */
    private Date empAddTime;

    /**
     * 订单总数
     */
    private Integer orderCount;

    /**
     * 提交订单数
     */
    private Integer comOrderTotal;

    /**
     * 成交订单总数
     */
    private Integer dealOrder;

    /**
     * 退款订单总数
     */
    private Integer refundOrder;

    /**
     * 已结算佣金
     */
    private BigDecimal commissionCash;

    /**
     * 已申请提现佣金
     */
    private BigDecimal cashApply;

    /**
     * 可以提现佣金
     */
    private BigDecimal canWithdraw;

    /**
     * 累计提成
     */
    private BigDecimal totalWithdraw;

    /**
     * 未入账
     */
    private BigDecimal unAccountedWithdraw;

    /**
     *  系统版本（中行：BOC;工行：ICBC）
     */
    private String sysVersion;

}
