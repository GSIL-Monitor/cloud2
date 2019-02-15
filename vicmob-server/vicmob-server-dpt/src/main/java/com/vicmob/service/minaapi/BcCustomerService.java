package com.vicmob.service.minaapi;

import com.vicmob.entity.BcCustomer;

/**
 * 客户数据业务逻辑接口
 * @author ricky
 * @date 2019-02-13
 */
public interface BcCustomerService {
    /**
     * 通过minaId和openId获取客户信息
     * @param openId openId
     * @param minaId minaId
     * @return BcCustomer 客户信息
     */
    BcCustomer getByOpenAndMina(String openId, Integer minaId);

    /**
     * 添加客户信息
     * @param customer 客户实体
     * @return Integer 客户主键
     */
    Integer insert(BcCustomer customer);

    /**
     * 根据主键查询客户信息
     * @param customerId 主键
     * @return  客户信息
     */
    BcCustomer selectByPrimaryKey(Integer customerId);

    /**
     * 获取Appsecret
     * @param minaId 小程序Id
     * @return Appsecret
     */
    String getAppsecret(Integer minaId);

    /**
     * 更新客户信息
     *  @param record 客户信息
     *  @return  是否更新成功（0失败；1成功）
     */
    int updateByPrimaryKeySelective(BcCustomer record);

    /**
     * 更新客户信息
     * @param customer
     */
    void updateCustomer(BcCustomer customer);

    /**
     * 更新客户信息
     * @param customer 客户信息
     * @return 是否更新成功（0失败；1成功）
     */
    Integer update(BcCustomer customer);

}
