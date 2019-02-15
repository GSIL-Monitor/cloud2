package com.vicmob.entity;

import lombok.Data;

import java.util.Date;

/**
 * 客户浏览实体类
 * @author ricky
 * @date 2019-01-29
 */
@Data
public class BcEmployeeView {

    /**
     * 员工id
     */
    private Integer employeeId;

    /**
     * 客户id
     */
    private Integer customerId;

    /**
     * 点赞时间
     */
    private Date viewDateTime;

    /**
     * 微信頭像
     */
    private String avatar;
}
