package com.vicmob.entity;

import lombok.Data;

import java.util.Date;

/**
 * 客户实体类
 * @author  ricky
 * @date 2019-01-29
 */
@Data
public class BcAutomaticRecovery {

    /**
     * 主键id
     */
    private Integer replyId;

    /**
     * 用户Id
     */
    private Integer userId;

    /**
     * 问题
     */
    private String question;

    /**
     * 答案
     */
    private String answer;

    /**
     * 是否显示（0：展示，1：不展示）
     */
    private Integer isShow;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 删除标记（0：删除；1：未删除）
     */
    private Integer deleteFlag;
}
