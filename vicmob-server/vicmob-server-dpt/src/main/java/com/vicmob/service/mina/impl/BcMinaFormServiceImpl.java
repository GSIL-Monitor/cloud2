package com.vicmob.service.mina.impl;

import com.vicmob.entity.BcMinaForm;
import com.vicmob.mapper.mina.BcMinaFormDao;
import com.vicmob.service.mina.BcMinaFormService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * 小程序formId业务逻辑实现类
 * @author ricky
 * @date 2019-2-13
 */
@Service
@Transactional(readOnly = true, rollbackFor = SQLException.class)
public class BcMinaFormServiceImpl implements BcMinaFormService {

    @Resource
    private BcMinaFormDao bcMinaFormDao;

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public void add(BcMinaForm minaForm) {
        bcMinaFormDao.insert(minaForm);
    }
}
