package com.vicmob.service.minaapi;

import com.vicmob.entity.BcEmployeeImpressionlabel;
import java.util.List;

/**
 * 员工印象标签业务逻辑接口
 * @author ricky
 * @date 2019-01-31
 */
public interface BcEmployeeImpressionlabelService {

    /**
     * 根据员工Id查询印象标签
     *  @param employeeId 员工Id
     *  @param  customerId 客户id
     *  @return  印象标签集合
     */
    List<BcEmployeeImpressionlabel> selectByEmployeeIdForMina(Integer customerId, Integer employeeId);

    /**
     * 根据员工Id查询印象标签
     *  @param employeeId 员工Id
     *  @return  印象标签集合
     */
    List<BcEmployeeImpressionlabel> selectByEmployeeId(Integer employeeId);

    /**
     * 插入印象标签
     * @param bcEmployeeImpressionlabel 印象标签
     * @return 1：成功 0：失败
     */
    Integer insertEmployeeImpressionlabel(BcEmployeeImpressionlabel bcEmployeeImpressionlabel);

    /**
     * 根据impressionLabelId主键id删除记录
     * @param impressionLabelId 主键id
     * @return 1：成功 0：失败
     */
    Integer deleteByPrimaryKey(Integer impressionLabelId);

    /**
     * 根据员工Id查询印象标签根据点赞数排序前三个
     *  @param employeeId 员工Id
     *  @return  印象标签集合
     */
    List<BcEmployeeImpressionlabel> selectOrderByPointsNum(Integer employeeId);

    /**
     * 更新印象标签
     *  @param  bcEmployeeImpressionlabel 印象标签
     *  @return  是否更新成功（0失败；1成功）
     */
    int updateEmployeeImpressionlabel(BcEmployeeImpressionlabel bcEmployeeImpressionlabel);

    /**
     * 根据主键查询印象标签
     *  @param impressionLabelId 主键
     *  @return  印象标签
     */
    BcEmployeeImpressionlabel selectById(Integer impressionLabelId);
}
