package com.vicmob.mapper.api;

import com.vicmob.entity.BcCustomerEmployee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工客户关系信息数据持久层接口
 * @author ricky
 * @date 2019-01-31
 */
@Mapper
public interface BcCustomerEmployeeMapper {
    /**
     * 根据主键删除客户员工关系信息
     *  @param relationshipId 主键
     *  @return  是否删除成功（0失败；1成功）
     */
    int deleteByPrimaryKey(Integer relationshipId);

    /**
     * 插入客户员工关系信息
     *  @param record 客户员工关系信息
     *  @return  是否插入成功（0失败；1成功）
     */
    int insertSelective(BcCustomerEmployee record);

    /**
     * 根据主键查询客户员工关系信息
     *  @param relationshipId 主键
     *  @return      客户员工关系信息
     */
    BcCustomerEmployee selectByPrimaryKey(Integer relationshipId);

    /**
     * 更新客户员工关系信息
     *  @param record 客户员工关系信息
     *  @return   是否更新成功（0失败；1成功）
     */
    int updateByPrimaryKeySelective(BcCustomerEmployee record);

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
    BcCustomerEmployee selectByEmpIdAndCusId(@Param("employeeId")Integer employeeId, @Param("customerId")Integer customerId);

    /**
     * 根据客户id和员工id获取客户信息
     * @param bcCustomerEmployee 客户id、员工id
     * @return 客户员工关系信息
     */
    BcCustomerEmployee selectByCustomerId(BcCustomerEmployee bcCustomerEmployee);

    /**
     * 根据员工id获取所有客户信息
     * @param employeeId 员工id
     * @return 客户信息集合
     */
    List<BcCustomerEmployee> selectListByEmpId(Integer employeeId);

    /**
     * 根据员工id获取所有客户信息按活动时间或新增来分类
     * @param bcCustomerEmployee 客户员工实体
     * @return 员工的所有客户list集合
     */
    List<BcCustomerEmployee> selectBySearchType(BcCustomerEmployee bcCustomerEmployee);

    /**
     * 根据选择的标签获取客户信息
     * @param custLabels 标签集合（逗号隔开）
     * @param employeeId 员工id
     * @return 客户list集合
     */
    List<BcCustomerEmployee> selectByCustLabel(String custLabels,Integer employeeId);

    /**
     * 获取客户列表boss端展示按活动时间排序
     * @param bcCustomerEmployee 员工id
     * @return 员工的所有客户list集合
     */
    List<BcCustomerEmployee> selectCustbyEmpFollow(BcCustomerEmployee bcCustomerEmployee);

    /**
     *获取所有员工客户关系
     * @return 员工的所有客户list集合
     */
    List<BcCustomerEmployee> getAllCustAndEmp();

    /**
     * 获取近7/15/30天的新增客户数
     * @param employeeId 员工id
     * @param timeType 时间7/15/30
     * @return list <时间（年月日格式），个数>
     */
    List<BcCustomerEmployee> getNewCust(@Param("employeeId")Integer employeeId,@Param("timeType")Integer timeType);

    /**
     * 查看客户昵称或者备注姓名
     *  @param employeeId 员工Id
     *  @param customerId 客户Id
     *  @return  昵称或者备注姓名
     */
    String selectCustNickname(@Param("employeeId")Integer employeeId,@Param("customerId")Integer customerId);


    /**
     * 查询所有客户员工关系数量
     * @param userId 用户id
     * @return int 数量
     */
    int getcount(Integer userId);

    /**
     * update授权手机号
     * @param bcCustomerEmployee 员工客户实体
     * @return 是否更新成功（0失败；1成功）
     */
    int updateByEmpAndCust(BcCustomerEmployee bcCustomerEmployee);

}
