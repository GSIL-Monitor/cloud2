package com.vicmob.service.minaapi.impl;

import com.vicmob.entity.BcCustomer;
import com.vicmob.mapper.api.BcCustomerMapper;
import com.vicmob.mapper.api.BcEmployeeMapper;
import com.vicmob.service.minaapi.BcCustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * 客户数据逻辑实现类
 * @author ricky
 * @date 2019-02-14
 */
@Service
@Transactional(readOnly = true, rollbackFor = SQLException.class)
public class BcCustomerServiceImpl implements BcCustomerService {


    @Resource
    private BcCustomerMapper bcCustomerMapper;

    @Override
    public BcCustomer getByOpenAndMina(String openId, Integer minaId) {
        return bcCustomerMapper.getByOpenAndMina(openId, minaId);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Integer insert(BcCustomer customer) {
        return bcCustomerMapper.insert(customer);
    }

    @Override
    public BcCustomer selectByPrimaryKey(Integer customerId) {
        return bcCustomerMapper.selectByPrimaryKey(customerId);
    }

    @Override
    public String getAppsecret(Integer minaId) {
        return bcCustomerMapper.getAppsecret(minaId);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public int updateByPrimaryKeySelective(BcCustomer record) {
        return bcCustomerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public void updateCustomer(BcCustomer customer) {
        bcCustomerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Integer update(BcCustomer customer){
        return bcCustomerMapper.update(customer);
    }

}
