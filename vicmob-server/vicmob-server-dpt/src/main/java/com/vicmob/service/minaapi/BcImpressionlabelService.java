package com.vicmob.service.minaapi;

import com.vicmob.entity.BcImpressionlabel;

/**
 * 客户对印象标签点赞记录业务逻辑接口
 * @author ricky
 * @date 2019-01-31
 */
public interface BcImpressionlabelService {

    /**
     * 查询客户点赞信息
     *  @param impressionLabelId 印象标签Id
     *  @param customerId 客户Id
     *  @return   点赞信息
     */
    BcImpressionlabel selectInfo(Integer impressionLabelId, Integer customerId);

    /**
     *  插入客户点赞信息
     *  @param impressionLabelId 印象标签Id
     *  @param customerId 客户Id
     *  @return   0失败；1成功
     */
    int insertInfo(Integer impressionLabelId,Integer customerId);
}
