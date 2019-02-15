package com.vicmob.service.minaapi.impl;

import com.vicmob.entity.BcAutomaticRecovery;
import com.vicmob.mapper.api.BcAutomaticRecoveryMapper;
import com.vicmob.service.minaapi.BcAutomaticRecoveryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * 自动回复数据逻辑实现类
 * @author ricky
 * @date 2019-02-13
 */
@Service
@Transactional(readOnly = true, rollbackFor = SQLException.class)
public class BcAutomaticRecoveryServiceImpl implements BcAutomaticRecoveryService {

    @Resource
    private BcAutomaticRecoveryMapper bcAutomaticRecoveryMapper;

    @Override
    public BcAutomaticRecovery get(Integer replyId){
        return bcAutomaticRecoveryMapper.get(replyId);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Integer insertAutomaticRecovery(BcAutomaticRecovery bcAutomaticRecovery) {
        return bcAutomaticRecoveryMapper.insert(bcAutomaticRecovery);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Integer updateAutomaticRecovery(BcAutomaticRecovery bcAutomaticRecovery) {
        return bcAutomaticRecoveryMapper.update(bcAutomaticRecovery);
    }

    @Override
    public Integer selectCount(Integer userId) {
        return bcAutomaticRecoveryMapper.selectCount(userId);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Integer updateDeleteFlag(String replyIds) {
        return bcAutomaticRecoveryMapper.updateDeleteFlag(replyIds);
    }

    @Override
    public List<BcAutomaticRecovery> getByMinaId(Integer minaId){
        return bcAutomaticRecoveryMapper.getByMinaId(minaId);
    }
}
