package com.vicmob.service.minaapi;

import com.vicmob.entity.BcEmployee;

import java.util.List;

/**
 * 员工信息数据业务逻辑接口
 * @author ricky
 * @date 2019-01-30
 */
public interface BcEmployeeService {

    /**
     * 通过客户Id和minaId查询员工列表信息
     * @param bcEmployee 员工实体
     * @return List<BcEmployee> 员工list集合
     */
    List<BcEmployee> selectByCustomerId(BcEmployee bcEmployee);

    /**
     * 根据客户Id和员工Id查询员工信息
     * @param employeeId 员工Id
     * @param customerId 客户Id
     * @return BcEmployee员工实体信息
     */
    BcEmployee selectByEmpAndCus(Integer employeeId,Integer customerId);

    /**
     * 通过主键查询员工信息
     * @param employeeId 主键
     * @return BcEmployee员工信息
     */
    BcEmployee selectById(Integer employeeId);

    /**
     * 更新员工信息
     * @param bcEmployee 员工信息
     * @return  是否更新成功（0失败；1成功）
     */
    int updateEmployeeInfo(BcEmployee bcEmployee);

    /**
     * 根据员工Id获取小程序名称
     * @param employeeId 员工Id
     * @return 小程序名称
     */
    String selectNameByEmpId(Integer employeeId);
}
