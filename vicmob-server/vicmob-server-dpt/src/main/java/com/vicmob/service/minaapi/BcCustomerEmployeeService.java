package com.vicmob.service.minaapi;

import com.vicmob.entity.BcCustomerEmployee;
import java.util.List;

/**
 * 员工客户关系信息数据业务逻辑接口
 * @author ricky
 * @date 2019-01-30
 */
public interface BcCustomerEmployeeService {
    /**
     * 更新客户对员工是否屏蔽
     *  @param bcCustomerEmployee 客户员工关系信息
     *  @return   是否更新成功（0失败；1成功）
     */
    int updateCustIsShield(BcCustomerEmployee bcCustomerEmployee);

    /**
     * 跟据员工Id和客户Id查关系信息
     *  @param employeeId 员工Id
     *  @param customerId 客户Id
     *  @return    客户员工关系信息
     */
    BcCustomerEmployee selectByEmpIdAndCusId(Integer employeeId,Integer customerId);

    /**
     * 更新客户员工关系信息
     *  @param bcCustomerEmployee 客户员工关系信息
     *  @return   是否更新成功（0失败；1成功）
     */
    int updateCustomerEmployeeInfo(BcCustomerEmployee bcCustomerEmployee);

    /**
     *获取所有员工客户关系
     * @return 员工的所有客户list集合
     */
    List<BcCustomerEmployee> getAllCustAndEmp();

    /**
     * 插入客户员工关系信息
     *  @param record 客户员工关系信息
     *  @return  是否插入成功（0失败；1成功）
     */
    int insertSelective(BcCustomerEmployee record);

    /**
     * 查看客户昵称或者备注姓名
     *  @param employeeId 员工Id
     *  @param customerId 客户Id
     *  @return  昵称或者备注姓名
     */
    String selectCustNickname(Integer employeeId,Integer customerId);


    /**
     * 查询所有客户员工关系数量
     * @param userId 用户id
     * @return int 数量
     */
    int getcount(Integer userId);

    /**
     * 根据主键查询客户员工关系信息
     *  @param relationshipId 主键
     *  @return      客户员工关系信息
     */
    BcCustomerEmployee selectByPrimaryKey(Integer relationshipId);

    /**
     * update授权手机号
     * @param bcCustomerEmployee 员工客户实体
     * @return 是否更新成功（0失败；1成功）
     */
    int updateByEmpAndCust(BcCustomerEmployee bcCustomerEmployee);
}
