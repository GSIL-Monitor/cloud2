package com.vicmob.service.minaapi.impl;

import com.vicmob.entity.BcDecoration;
import com.vicmob.mapper.api.BcDecorationMapper;
import com.vicmob.service.minaapi.BcDecorationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * 官网信息数据逻辑实现类
 * @author ricky
 * @date 2019-02-13
 */
@Service
@Transactional(readOnly = true, rollbackFor = SQLException.class)
public class BcDecorationServiceImpl implements BcDecorationService {

    @Resource
    private BcDecorationMapper bcDecorationMapper;

    @Override
    public BcDecoration selectByMinaId(Integer minaId) {
        return bcDecorationMapper.selectByMinaId(minaId);
    }
}
