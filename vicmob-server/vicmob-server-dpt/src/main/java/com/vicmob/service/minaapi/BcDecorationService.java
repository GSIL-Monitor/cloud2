package com.vicmob.service.minaapi;

import com.vicmob.entity.BcDecoration;

/**
 * 官网数据业务逻辑接口
 * @author ricky
 * @date 2019-02-13
 */
public interface BcDecorationService {

    /**
     * 通过minaId查询官网信息
     *  @param minaId 小程序Id
     *  @return 官网信息
     */
    BcDecoration selectByMinaId(Integer minaId);
}
