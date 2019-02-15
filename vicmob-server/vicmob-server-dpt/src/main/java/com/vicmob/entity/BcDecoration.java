package com.vicmob.entity;

import lombok.Data;

import java.util.Date;

/**
 * 自定义官网实体类
 * @author  ricky
 * @date 2019-02-13
 */
@Data
public class BcDecoration {

    /**
     * 主键ID
     */
    private Integer decorationId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 小程序ID
     */
    private Integer minaId;

    /**
     * 数据Json串（存放模板数据）
     */
    private String dataJson;

    /**
     * 删除标记（0：删除；1：未删除）
     */
    private Integer deleteFlag;

    /**
     * 创建时间
     */
    private Date createTime;

}
