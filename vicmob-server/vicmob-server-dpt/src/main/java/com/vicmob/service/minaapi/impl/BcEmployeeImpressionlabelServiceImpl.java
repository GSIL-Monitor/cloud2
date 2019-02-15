package com.vicmob.service.minaapi.impl;

import com.vicmob.entity.BcEmployeeImpressionlabel;
import com.vicmob.mapper.api.BcEmployeeImpressionlabelMapper;
import com.vicmob.service.minaapi.BcEmployeeImpressionlabelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * 员工印象标签逻辑实现类
 * @author ricky
 * @date 2019-01-31
 */
@Service
@Transactional(readOnly = true, rollbackFor = SQLException.class)
public class BcEmployeeImpressionlabelServiceImpl implements BcEmployeeImpressionlabelService {
    @Resource
    private BcEmployeeImpressionlabelMapper bcEmployeeImpressionlabelMapper;

    @Override
    public List<BcEmployeeImpressionlabel> selectByEmployeeIdForMina(Integer customerId, Integer employeeId){
        return bcEmployeeImpressionlabelMapper.selectByEmployeeIdForMina(customerId,employeeId);
    }

    @Override
    public List<BcEmployeeImpressionlabel> selectByEmployeeId(Integer employeeId){
        return bcEmployeeImpressionlabelMapper.selectByEmployeeId(employeeId);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Integer insertEmployeeImpressionlabel(BcEmployeeImpressionlabel bcEmployeeImpressionlabel) {
        return bcEmployeeImpressionlabelMapper.insertSelective(bcEmployeeImpressionlabel);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Integer deleteByPrimaryKey(Integer impressionLabelId) {
        return bcEmployeeImpressionlabelMapper.deleteByPrimaryKey(impressionLabelId);
    }

    @Override
    public List<BcEmployeeImpressionlabel> selectOrderByPointsNum(Integer employeeId){
        return bcEmployeeImpressionlabelMapper.selectOrderByPointsNum(employeeId);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public int updateEmployeeImpressionlabel(BcEmployeeImpressionlabel bcEmployeeImpressionlabel) {
        return bcEmployeeImpressionlabelMapper.updateByPrimaryKeySelective(bcEmployeeImpressionlabel);
    }

    @Override
    public BcEmployeeImpressionlabel selectById(Integer impressionLabelId) {
        return bcEmployeeImpressionlabelMapper.selectByPrimaryKey(impressionLabelId);
    }
}
