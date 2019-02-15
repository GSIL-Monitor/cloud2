package com.vicmob.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 企业资讯实体类
 * @author  ricky
 * @date 2019-02-13
 */
@Data
public class BcInformation {

    /**
     * 资讯ID
     */
    private Integer infoId;

    /**
     * 管理员id
     */
    private Integer userId;

    /**
     * 资讯标题
     */
    private String infoTitle;

    /**
     * 资讯详情
     */
    private String infoDetails;


    /**
     * 发布时间（添加时间）
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date infoAddTime;

    /**
     * 删除标记（0：删除；1：未删除）
     */
    private Integer deleteFlag;

    /**
     * 发布时间
     */
    private String infoAddDate;

    /**
     * 企业资讯图片
     */
    private String imgUrl;
}
