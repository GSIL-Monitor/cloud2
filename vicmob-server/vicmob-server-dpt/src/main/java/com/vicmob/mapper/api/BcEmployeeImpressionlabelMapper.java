package com.vicmob.mapper.api;

import com.vicmob.entity.BcEmployeeImpressionlabel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工印象标签数据持久层接口
 * @author ricky
 * @date 2019-01-31
 */
@Mapper
public interface BcEmployeeImpressionlabelMapper {
    /**
     * 根据主键删除印象标签
     * @param impressionLabelId 主键
     * @return   是否删除成功（0失败；1成功）
     */
    int deleteByPrimaryKey(Integer impressionLabelId);

    /**
     * 插入印象标签
     * @param record 印象标签
     * @return  是否插入成功（0失败；1成功）
     */
    int insertSelective(BcEmployeeImpressionlabel record);

    /**
     * 根据主键查询印象标签
     *  @param impressionLabelId 主键
     *  @return  印象标签
     */
    BcEmployeeImpressionlabel selectByPrimaryKey(Integer impressionLabelId);

    /**
     * 更新印象标签
     * @param record 印象标签
     * @return  是否更新成功（0失败；1成功）
     */
    int updateByPrimaryKeySelective(BcEmployeeImpressionlabel record);

    /**
     * 根据员工Id查询印象标签（供小程序使用，查询该客户是否点赞）
     * @param employeeId 员工Id
     * @param customerId 客户id
     * @return  印象标签集合
     */
    List<BcEmployeeImpressionlabel> selectByEmployeeIdForMina(@Param("customerId")Integer customerId, @Param("employeeId")Integer employeeId);

    /**
     * 根据员工Id查询印象标签
     * @param employeeId 员工Id
     * @return  印象标签集合
     */
    List<BcEmployeeImpressionlabel> selectByEmployeeId(Integer employeeId);

    /**
     * 根据员工Id查询印象标签根据点赞数排序前三个
     * @param employeeId 员工Id
     * @return  印象标签集合
     */
    List<BcEmployeeImpressionlabel> selectOrderByPointsNum(Integer employeeId);
}
