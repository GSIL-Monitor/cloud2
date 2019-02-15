package com.vicmob.service.web.impl;

import com.vicmob.entity.BcMinaPublish;
import com.vicmob.mapper.web.BcMinaPublishMapper;
import com.vicmob.service.web.BcMinaPublishService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 小程序发布信息业务逻辑实现类
 * @author ziv
 * @date 2019-02-14
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BcMinaPublishServiceImpl implements BcMinaPublishService {

    @Resource
    private BcMinaPublishMapper minaPublishMapper;

    @Override
    public BcMinaPublish findByMinaId(int minaId) {
        return minaPublishMapper.findByMinaId(minaId);
    }
}
