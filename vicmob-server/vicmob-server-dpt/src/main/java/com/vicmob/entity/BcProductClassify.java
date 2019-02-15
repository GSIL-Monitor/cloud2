package com.vicmob.entity;

import lombok.Data;

import java.util.List;

/**
 * 产品分类实体类
 * @author ricky
 * @date 2019-01-31
 */
@Data
public class BcProductClassify {

    /**
     * 产品分类ID
     */
    private Integer classifyId;

    /**
     * 小程序ID
     */
    private Integer minaId;

    /**
     * 父级ID（来源于一级分类ID，一级分类为0）
     */
    private Integer parentId;

    /**
     * 产品分类名称
     */
    private String classifyName;

    /**
     * 产品分类图片
     */
    private String classifyPic;

    /**
     * 产品分类排序
     */
    private Integer classifySort;

    /**
     * 删除标记（0：删除；1：未删除）
     */
    private Integer deleteFlag;

    /**
     * 商品数量
     */
    private Integer serviceCount;

    /**
     * 二级分类数量
     */
    private Integer classifyCount;

    /**
     * 二级分类集合
     */
    private List<BcProductClassify> BcProductClassifyList;

    /**
     * 商品数量集合
     */
    private List<BcProduct> FirProduct;

    /**
     * 一级分类商品数量
     */
    private Integer serviceCountForOne;
}
