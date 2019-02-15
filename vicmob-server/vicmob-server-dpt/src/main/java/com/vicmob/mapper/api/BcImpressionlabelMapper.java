package com.vicmob.mapper.api;

import com.vicmob.entity.BcImpressionlabel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 客户对印象标签点赞记录持久层接口
 * @author ricky
 * @date 2019-01-31
 */
@Mapper
public interface BcImpressionlabelMapper {
    /**
     *  查询客户点赞信息
     *  @param impressionLabelId 印象标签Id
     *  @param customerId 客户Id
     *  @return   点赞信息
     */
    BcImpressionlabel selectInfo(@Param("impressionLabelId")Integer impressionLabelId, @Param("customerId")Integer customerId);

    /**
     *  插入客户点赞信息
     *  @param impressionLabelId 印象标签Id
     *  @param customerId 客户Id
     *  @return   0失败；1成功
     */
    int insertInfo(@Param("impressionLabelId")Integer impressionLabelId,@Param("customerId")Integer customerId);
}
