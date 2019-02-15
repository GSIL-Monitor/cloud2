package com.vicmob.service.web;

import com.vicmob.entity.BcMinaPublish;

/**
 * 小程序发布信息业务逻辑接口
 * @author ziv
 * @date 2019-02-14
 */
public interface BcMinaPublishService {
    /**
     * 通过minaId获取小程序发布信息
     * @param minaId 小程序Id
     * @return BcMinaPublish 小程序发布信息
     */
    BcMinaPublish findByMinaId(int minaId);
}
