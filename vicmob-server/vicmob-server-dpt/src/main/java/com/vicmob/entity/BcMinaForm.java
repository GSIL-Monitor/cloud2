package com.vicmob.entity;

import lombok.Data;

import java.util.Date;

/**
 * 小程序formId实体类
 * @author  ricky
 * @date 2019-02-13
 */
@Data
public class BcMinaForm {
    /**
     * 主键
     */
    private Integer minaFormId;

    /**
     * 小程序formId
     */
    private String formId;

    /**
     * 客户主键
     */
    private Integer customerId;

    /**
     * formId有效期截止时间
     */
    private Date endDate;
}
