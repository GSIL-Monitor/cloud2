package com.vicmob.service.minaapi.impl;

import com.vicmob.entity.BcInformation;
import com.vicmob.mapper.api.BcInformationMapper;
import com.vicmob.service.minaapi.BcInformationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * 企业资讯数据逻辑实现类
 * @author ricky
 * @date 2019-02-13
 */
@Service
@Transactional(readOnly = true, rollbackFor = SQLException.class)
public class BcInformationServiceImpl implements BcInformationService {

    @Resource
    private BcInformationMapper bcInformationMapper;

    @Override
    public BcInformation selectByPrimaryKey(Integer infoId) {
        return bcInformationMapper.selectByPrimaryKey(infoId);
    }

    @Override
    public List<BcInformation> selectByMinaId(Integer minaId){
        return bcInformationMapper.selectByMinaId(minaId);
    }
}
