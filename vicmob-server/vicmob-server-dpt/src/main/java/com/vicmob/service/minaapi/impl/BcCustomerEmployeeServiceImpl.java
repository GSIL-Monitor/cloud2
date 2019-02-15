package com.vicmob.service.minaapi.impl;

import com.vicmob.entity.BcCustomerEmployee;
import com.vicmob.mapper.api.BcCustomerEmployeeMapper;
import com.vicmob.service.minaapi.BcCustomerEmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * 员工客户关系信息数据逻辑实现类
 * @author ricky
 * @date 2019-01-31
 */
@Service
@Transactional(readOnly = true, rollbackFor = SQLException.class)
public class BcCustomerEmployeeServiceImpl implements BcCustomerEmployeeService {
    @Resource
    private BcCustomerEmployeeMapper bcCustomerEmployeeMapper;

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public int updateCustIsShield(BcCustomerEmployee bcCustomerEmployee) {
        return bcCustomerEmployeeMapper.updateCustIsShield(bcCustomerEmployee);
    }

    @Override
    public BcCustomerEmployee selectByEmpIdAndCusId(Integer employeeId,Integer customerId) {
        return bcCustomerEmployeeMapper.selectByEmpIdAndCusId(employeeId, customerId);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public int updateCustomerEmployeeInfo(BcCustomerEmployee bcCustomerEmployee) {
        return bcCustomerEmployeeMapper.updateByPrimaryKeySelective(bcCustomerEmployee);
    }

    @Override
    public List<BcCustomerEmployee> getAllCustAndEmp(){
        return bcCustomerEmployeeMapper.getAllCustAndEmp();
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public int insertSelective(BcCustomerEmployee record) {
        return bcCustomerEmployeeMapper.insertSelective(record);
    }

    @Override
    public String selectCustNickname(Integer employeeId,Integer customerId) {
        return bcCustomerEmployeeMapper.selectCustNickname(employeeId, customerId);
    }


    @Override
    public int getcount(Integer userId) {
        return bcCustomerEmployeeMapper.getcount(userId);
    }

    @Override
    public BcCustomerEmployee selectByPrimaryKey(Integer relationshipId) {
        return bcCustomerEmployeeMapper.selectByPrimaryKey(relationshipId);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public int updateByEmpAndCust(BcCustomerEmployee bcCustomerEmployee) {
        return bcCustomerEmployeeMapper.updateByEmpAndCust(bcCustomerEmployee);
    }
}
