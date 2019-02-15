package com.vicmob.mapper.api;

import com.vicmob.entity.BcCustomer;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户信息数据持久层接口
 * @author ricky
 * @date 2019-02-14
 */
@Mapper
public interface BcCustomerMapper {

    /**
     * 根据主键删除客户信息
     *  @param customerId 主键
     *  @return  是否删除成功（0失败；1成功）
     */
    int deleteByPrimaryKey(Integer customerId);

    /**
     * 插入客户信息
     *  @param record 客户信息
     *  @return   是否插入成功（0失败；1成功）
     */
    int insertSelective(BcCustomer record);

    /**
     * 根据主键查询客户信息
     *  @param customerId 主键
     *  @return  客户信息
     */
    BcCustomer selectByPrimaryKey(Integer customerId);

    /**
     * 更新客户信息
     *  @param record 客户信息
     *  @return  是否更新成功（0失败；1成功）
     */
    int updateByPrimaryKeySelective(BcCustomer record);

    /**
     * 通过minaId和openId获取客户信息
     * @param openId openId
     * @param minaId minaId
     * @return BcCustomer 客户信息
     */
    BcCustomer getByOpenAndMina(String openId, Integer minaId);
    /**
     * 获取Appsecret
     * @param minaId 小程序Id
     * @return Appsecret
     */
    String getAppsecret(Integer minaId);

    /**
     * 插入客户信息
     * @param record 客户信息
     * @return 1：成功 0：失败
     */
    Integer insert(BcCustomer record);

    /**
     * 更新粉丝信息
     * @param record 客户信息
     * @return 1：成功 0：失败
     */
    Integer update(BcCustomer record);
}
