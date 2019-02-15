package com.vicmob.service.minaapi.impl;

import com.vicmob.entity.BcEmployeeView;
import com.vicmob.mapper.api.BcEmployeeViewMapper;
import com.vicmob.service.minaapi.BcEmployeeViewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * 员工浏览信息逻辑实现类
 * @author ricky
 * @date 2019-01-30
 */
@Service
@Transactional(readOnly = true, rollbackFor = SQLException.class)
public class BcEmployeeViewServiceImpl implements BcEmployeeViewService {
    @Resource
    private BcEmployeeViewMapper bcEmployeeViewMapper;

    @Override
    public List<BcEmployeeView> selectByEmpId(Integer employeeId){
        return bcEmployeeViewMapper.selectByEmpId(employeeId);
    }

    @Override
    public BcEmployeeView selectByEmpAndCus(BcEmployeeView bcEmployeeView) {
        return bcEmployeeViewMapper.selectByEmpAndCus(bcEmployeeView);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public int insertSelective(BcEmployeeView bcEmployeeView) {
        return bcEmployeeViewMapper.insertSelective(bcEmployeeView);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public int updateByEmpAndCus(BcEmployeeView bcEmployeeView) {
        return bcEmployeeViewMapper.updateByEmpAndCus(bcEmployeeView);
    }


}
