package com.vicmob.entity;

import lombok.Data;

/**
 * 客户对印象标签点赞记录实体类
 * @author ricky
 * @date 2019-01-31
 */
@Data
public class BcImpressionlabel {

    /**
     * 印象标签Id
     */
    private Integer impressionLabelId;

    /**
     * 客户Id
     */
    private Integer customerId;
}
