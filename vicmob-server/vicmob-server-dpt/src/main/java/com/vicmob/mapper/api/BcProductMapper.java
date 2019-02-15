package com.vicmob.mapper.api;

import com.vicmob.entity.BcProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 产品相关持久层接口
 * @author ricky
 * @date 2019-02-11
 */
@Mapper
public interface BcProductMapper {
    /**
     *  根据主键删除产品信息
     *  @param productId 主键
     *  @return 是否删除成功（0失败；1成功）
     */
    int deleteByPrimaryKey(Integer productId);

    /**
     *  插入产品信息
     *  @param record 产品信息
     *  @return 是否插入成功（0失败；1成功）
     */
    int insertSelective(BcProduct record);

    /**
     *  根据主键查询产品信息
     *  @param productId 主键
     *  @return      产品信息
     */
    BcProduct selectByPrimaryKey(Integer productId);

    /**
     * 根据主键更新产品信息
     *  @param record 产品信息
     *  @return  是否更新成功（0失败；1成功）
     */
    int updateByPrimaryKeySelective(BcProduct record);

    /**
     *  获取该员工公司所有商品
     *  @param bcProduct 员工id
     *  @return 产品信息
     */
    List<BcProduct> selectAllByEmployeeId(BcProduct bcProduct);


    /**
     * 根据员工Id查询员工所推荐的商品
     * @param employeeId  员工Id
     * @return 推荐产品集合
     */
    List<BcProduct> selectByEmployeeId(Integer employeeId);

    /**
     *  通过一级分类Id查询所有产品
     *  @param bcProduct 一级分类Id
     *  @return  产品集合
     */
    List<BcProduct> selectByFirClassifyId(BcProduct bcProduct);

    /**
     * 通过二级分类查询商品信息
     *  @param bcProduct 二级分类Id
     *  @return 产品集合
     */
    List<BcProduct> selectBySecClassifyId(BcProduct bcProduct);

}
