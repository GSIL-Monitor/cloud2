package com.vicmob.service.minaapi.impl;

import com.vicmob.entity.BcEmployee;
import com.vicmob.mapper.api.BcEmployeeMapper;
import com.vicmob.service.minaapi.BcEmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * 员工信息数据逻辑实现类
 * @author ricky
 * @date 2019-01-30
 */
@Service
@Transactional(readOnly = true, rollbackFor = SQLException.class)
public class BcEmployeeServiceImpl implements BcEmployeeService {
    @Resource
    private BcEmployeeMapper bcEmployeeMapper;

    @Override
    public List<BcEmployee> selectByCustomerId(BcEmployee bcEmployee) { return bcEmployeeMapper.selectByCustomerId(bcEmployee);}

    @Override
    public BcEmployee selectByEmpAndCus(Integer employeeId,Integer customerId) {return bcEmployeeMapper.selectByEmpAndCus(employeeId,customerId);}

    @Override
    public BcEmployee selectById(Integer employeeId) {return bcEmployeeMapper.selectByPrimaryKey(employeeId);}

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public int updateEmployeeInfo(BcEmployee bcEmployee) {return bcEmployeeMapper.updateByPrimaryKeySelective(bcEmployee);}

    @Override
    public String selectNameByEmpId(Integer employeeId) {return bcEmployeeMapper.selectNameByEmpId(employeeId);}
}
