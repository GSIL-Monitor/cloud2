package com.vicmob.service.minaapi;

import com.vicmob.entity.BcAutomaticRecovery;

import java.util.List;

/**
 * 自动回复数据业务逻辑接口
 * @author ricky
 * @date 2019-02-13
 */
public interface BcAutomaticRecoveryService {

    /**
     * 根据主键id获取自动回复内容
     * @param replyId 主键id
     * @return 自动回复内容
     */
    BcAutomaticRecovery get(Integer replyId);

    /**
     * 插入自动回复
     * @param bcAutomaticRecovery 自动回复实体
     * @return result 0：失败 1：成功
     */
    Integer insertAutomaticRecovery(BcAutomaticRecovery bcAutomaticRecovery);
    /**
     * 更新自动回复
     * @param bcAutomaticRecovery 自动回复实体
     * @return result 0：失败 1：成功
     */
    Integer updateAutomaticRecovery(BcAutomaticRecovery bcAutomaticRecovery);

    /**
     * 获取当前userId下以显示的数量
     * @param userId 用户id
     * @return 显示个数
     */
    Integer selectCount(Integer userId);

    /**
     * 批量删除
     * @param replyIds id逗号分隔
     * @return 1：成功0：失败
     */
    Integer updateDeleteFlag(String replyIds);

    /**
     * 根据minaId获取未删除且显示的自动回复
     * @param minaId 小程序id
     * @return 自动回复list集合
     */
    List<BcAutomaticRecovery> getByMinaId(Integer minaId);

}
