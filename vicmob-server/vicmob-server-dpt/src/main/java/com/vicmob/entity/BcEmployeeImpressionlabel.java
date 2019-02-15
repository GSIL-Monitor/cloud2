package com.vicmob.entity;

import lombok.Data;

/**
 * 员工印象标签实体类
 * @author ricky
 * @date 2019-01-31
 */
@Data
public class BcEmployeeImpressionlabel {
    /**
     * 印象标签id
     */
    private Integer impressionLabelId;

    /**
     * 员工id
     */
    private Integer employeeId;

    /**
     * 印象标签
     */
    private String impressionLabel;

    /**
     * 点赞个数
     */
    private Integer pointsNum;

    /**
     * 删除标记（0：删除；1：未删除）
     */
    private Integer deleteFlag;

    /**
     * 该客户是否点赞（0：未点赞；1：已点赞）
     */
    private Integer chooseLike;
}
