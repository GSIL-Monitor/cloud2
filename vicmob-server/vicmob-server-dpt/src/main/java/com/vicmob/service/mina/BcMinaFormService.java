package com.vicmob.service.mina;

import com.vicmob.entity.BcMinaForm;

/**
 * 小程序formId业务逻辑接口
 * @author ricky
 * @date 2019-02-13
 */
public interface BcMinaFormService {

    /**
     * 保存formId
     * @param minaForm formId实体
     */
    void add(BcMinaForm minaForm);
}
