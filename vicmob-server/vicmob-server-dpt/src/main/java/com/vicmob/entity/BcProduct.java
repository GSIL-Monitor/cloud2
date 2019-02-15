package com.vicmob.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 产品实体类
 * @author ricky
 * @date 2019-01-31
 */
@Data
public class BcProduct {

    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 管理员id
     */
    private Integer userId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品分类ID（一级分类）
     */
    private Integer firClassifyId;

    /**
     * 产品分类ID（二级分类）
     */
    private Integer secClassifyId;

    /**
     * 产品封面
     */
    private String productCover;

    /**
     * 产品轮播图
     */
    private String productPics;

    /**
     * 产品价格
     */
    private BigDecimal productPrice;

    /**
     * 产品状态（1：上架 2：下架）
     */
    private Integer productState;

    /**
     * 产品排序
     */
    private Integer productSort;

    /**
     * 产品详情
     */
    private String productDetails;

    /**
     * 删除标记（0：删除；1：未删除）
     */
    private Integer deleteFlag;

    /**
     * 小程序Id
     */
    private Integer minaId;

    //员工展示类别中使用，该照片是否选中
    /**
     * 是否选中(1:选中 0：未选中)
     */
    private Integer isSelect;

    /**
     * 异常信息
     */
    private String msg;

    /**
     * 记录异常数据--产品排序
     */
    private String productSortSt;

    /**
     * 记录异常数据--产品状态
     */
    private String productStateSt;

    /**
     * 记录异常数据--产品价格
     */
    private String productPriceSt;

    /**
     * 员工id
     */
    private Integer employeeId;

}
