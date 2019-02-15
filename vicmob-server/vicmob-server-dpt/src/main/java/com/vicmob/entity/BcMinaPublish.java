package com.vicmob.entity;

import lombok.Data;

import java.util.Date;

/**
 * 发布信息实体类
 * @author ziv
 * @date 2019-02-14
 */
@Data
public class BcMinaPublish {
    /**
     * 发布信息主键
     */
    private Integer publishId;

    /**
     * 用户主键
     */
    private Integer userId;

    /**
     * 小程序账号ID
     */
    private Integer minaId;

    /**
     * 用户APPID
     */
    private String appId;

    /**
     * 类目ID
     */
    private String categoryID;

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 小程序标签
     */
    private String minaTag;

    /**
     * 审核编号
     */
    private String auditId;

    /**
     * 审核状态：0-未通过 1-审核通过 2-审核失败
     */
    private Integer auditState;

    /**
     * 提交审核时间
     */
    private Date auditTime;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 审核失败时间
     */
    private Date failTime;
}
