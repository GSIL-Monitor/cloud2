package com.vicmob.service.minaapi;

import com.vicmob.entity.BcProduct;

import java.util.List;

/**
 * 产品相关数据业务逻辑接口
 * @author ricky
 * @date 2019-02-11
 */
public interface BcProductService {
    /**
     *  根据员工Id查询员工所推荐的商品
     *  @param employeeId 员工Id
     *  @return  推荐产品集合
     */
    List<BcProduct> selectByEmployeeId(Integer employeeId);

    /**
     *  通过一级分类Id查询所有产品
     *  @param bcProduct 一级分类Id
     *  @return  产品集合
     */
    List<BcProduct> selectByFirClassifyId(BcProduct bcProduct);

    /**
     *  通过二级分类查询商品信息
     *  @param bcProduct 二级分类Id
     *  @return 产品集合
     */
    List<BcProduct> selectBySecClassifyId(BcProduct bcProduct);

    /**
     *  根据主键查询产品信息
     *  @param productId 主键
     *  @return      产品信息
     */
    BcProduct selectById(Integer productId);
}
