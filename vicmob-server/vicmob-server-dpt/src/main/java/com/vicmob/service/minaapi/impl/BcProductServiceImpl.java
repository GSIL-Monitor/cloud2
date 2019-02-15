package com.vicmob.service.minaapi.impl;

import com.vicmob.entity.BcProduct;
import com.vicmob.mapper.api.BcProductMapper;
import com.vicmob.service.minaapi.BcProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * 产品数据逻辑实现类
 * @author ricky
 * @date 2019-02-11
 */
@Service
@Transactional(readOnly = true, rollbackFor = SQLException.class)
public class BcProductServiceImpl implements BcProductService {

    @Resource
    private BcProductMapper bcProductMapper;

    @Override
    public List<BcProduct> selectByEmployeeId(Integer employeeId){
        return bcProductMapper.selectByEmployeeId(employeeId);
    }

    @Override
    public List<BcProduct> selectByFirClassifyId(BcProduct bcProduct) {
        return bcProductMapper.selectByFirClassifyId(bcProduct);
    }

    @Override
    public List<BcProduct> selectBySecClassifyId( BcProduct bcProduct){
        return bcProductMapper.selectBySecClassifyId(bcProduct);
    }

    @Override
    public BcProduct selectById(Integer productId) {
        return bcProductMapper.selectByPrimaryKey(productId);
    }
}
