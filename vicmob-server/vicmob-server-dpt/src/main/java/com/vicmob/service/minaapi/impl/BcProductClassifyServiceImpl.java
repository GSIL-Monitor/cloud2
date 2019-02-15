package com.vicmob.service.minaapi.impl;

import com.vicmob.entity.BcProductClassify;
import com.vicmob.mapper.api.BcProductClassifyMapper;
import com.vicmob.service.minaapi.BcProductClassifyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * 产品分类数据逻辑实现类
 * @author ricky
 * @date 2019-02-11
 */
@Service
@Transactional(readOnly = true, rollbackFor = SQLException.class)
public class BcProductClassifyServiceImpl implements BcProductClassifyService {
    @Resource
    private BcProductClassifyMapper bcProductClassifyMapper;

    @Override
    public List<BcProductClassify> selectFirstClsaaifyByMinaId(BcProductClassify bcProductClassify) {
        return bcProductClassifyMapper.selectFirstClsaaifyByMinaId(bcProductClassify);
    }

    @Override
    public List<BcProductClassify> selectByClassifyId(BcProductClassify bcProductClassify){
        return bcProductClassifyMapper.selectByClassifyId(bcProductClassify);
    }

}
