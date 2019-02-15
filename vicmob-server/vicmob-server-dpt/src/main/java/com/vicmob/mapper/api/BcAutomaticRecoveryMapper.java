package com.vicmob.mapper.api;

import com.vicmob.entity.BcAutomaticRecovery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自动回复信息数据持久层接口
 * @author ricky
 * @date 2019-02-13
 */
@Mapper
public interface BcAutomaticRecoveryMapper {

    /**
     * 获取自动回复列表
     * @param bcAutomaticRecovery 实体
     * @return 自动回复list集合
     */
    List<BcAutomaticRecovery> findList(BcAutomaticRecovery bcAutomaticRecovery);

    /**
     * 插入自动回复
     * @param bcAutomaticRecovery 实体
     * @return 1：成功0：失败
     */
    Integer insert(BcAutomaticRecovery bcAutomaticRecovery);

    /**
     * 更新自动回复
     * @param bcAutomaticRecovery 实体
     * @return 1：成功0：失败
     */
    Integer update(BcAutomaticRecovery bcAutomaticRecovery);

    /**
     * 根据主键id获取自动回复内容
     * @param replyId 主键id
     * @return 自动回复内容
     */
    BcAutomaticRecovery get(Integer replyId);

    /**
     * 获取当前userId下以显示的数量
     * @param userId 用户id
     * @return 显示个数
     */
    Integer selectCount(@Param("userId")Integer userId);

    /**
     * 批量删除
     * @param replyIds id逗号分隔
     * @return 1：成功0：失败
     */
    Integer updateDeleteFlag(@Param("replyIds")String replyIds);

    /**
     * 根据minaId获取未删除且显示的自动回复
     * @param minaId 小程序id
     * @return 自动回复list集合
     */
    List<BcAutomaticRecovery> getByMinaId(@Param("minaId")Integer minaId);

}
