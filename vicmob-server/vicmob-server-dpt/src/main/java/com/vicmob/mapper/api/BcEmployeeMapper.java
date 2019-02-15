package com.vicmob.mapper.api;

import com.vicmob.entity.BcEmployee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工信息数据持久层接口
 * @author ricky
 * @date 2019-01-30
 */
@Mapper
public interface BcEmployeeMapper {

    /**
     * 通过客户Id和minaId查询员工列表信息
     * @param bcEmployee
     * @return List员工集合
     */
    List<BcEmployee> selectByCustomerId(BcEmployee bcEmployee);

    /**
     * 根据客户Id和员工Id查询员工信息
     * @param employeeId 员工id
     * @param customerId 客户Id
     * @return BcEmployee员工实体信息
     */
    BcEmployee selectByEmpAndCus(@Param("employeeId")Integer employeeId, @Param("customerId")Integer customerId);

    /**
     * 根据主键查询员工信息
     * @param employeeId 主键
     * @return  员工信息
     */
    BcEmployee selectByPrimaryKey(Integer employeeId);

    /**
     *  更新员工信息
     *  @param record 员工信息
     *  @return  是否更新成功（0失败；1成功）
     */
    int updateByPrimaryKeySelective(BcEmployee record);

    /**
     *  根据员工Id获取小程序名称
     *  @param employeeId 员工Id
     *  @return 小程序名称
     */
    String selectNameByEmpId(@Param("employeeId")Integer employeeId);

}
