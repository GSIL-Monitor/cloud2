package com.vicmob.service.minaapi.impl;

import com.vicmob.entity.BcImpressionlabel;
import com.vicmob.mapper.api.BcImpressionlabelMapper;
import com.vicmob.service.minaapi.BcImpressionlabelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * 客户对印象标签点赞记录逻辑实现类
 * @author ricky
 * @date 2019-01-31
 */
@Service
@Transactional(readOnly = true, rollbackFor = SQLException.class)
public class BcImpressionlabelServiceImpl implements BcImpressionlabelService {

    @Resource
    private BcImpressionlabelMapper bcImpressionlabelMapper;

    @Override
    public BcImpressionlabel selectInfo(Integer impressionLabelId, Integer customerId) {
        return bcImpressionlabelMapper.selectInfo(impressionLabelId, customerId);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public int insertInfo(Integer impressionLabelId,Integer customerId) {
        return bcImpressionlabelMapper.insertInfo(impressionLabelId, customerId);
    }
}
