package com.vicmob.mapper.api;

import com.vicmob.entity.BcProductClassify;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 产品分类相关持久层接口
 * @author ricky
 * @date 2019-02-11
 */
@Mapper
public interface BcProductClassifyMapper {

    /**
     * 根据主键删除产品分类
     * @param classifyId 主键
     * @return  是否删除成功（0失败；1成功）
     */
    int deleteByPrimaryKey(Integer classifyId);

    /**
     * 插入产品分类信息
     * @param record 产品分类信息
     * @return  是否插入成功（0失败；1成功）
     */
    int insertSelective(BcProductClassify record);

    /**
     * 根据主键查询产品分类
     * @param classifyId 主键
     * @return BcProductClassify 产品分类信息
     */
    BcProductClassify selectByPrimaryKey(Integer classifyId);

    /**
     * 更新产品分类
     * @param record 产品分类信息
     * @return 是否更新成功（0失败；1成功）
     */
    int updateByPrimaryKeySelective(BcProductClassify record);

    /**
     * 查询所有的一级分类标签
     * @param bcProductClassify  小程序Id
     *  @return   一级分类标签集合
     */
    List<BcProductClassify> selectFirstClsaaifyByMinaId(BcProductClassify bcProductClassify);

    /**
     * 通过一级分类Id查询二级分类
     *  @param bcProductClassify 一级分类Id
     *  @return  二级分类集合
     */
    List<BcProductClassify> selectByClassifyId(BcProductClassify bcProductClassify);
}
