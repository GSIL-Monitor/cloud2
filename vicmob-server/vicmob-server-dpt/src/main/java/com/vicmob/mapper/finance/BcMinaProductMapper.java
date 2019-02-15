package com.vicmob.mapper.finance;

import org.apache.ibatis.annotations.Mapper;

/**
 * 炫销宝小程序产品数据持久层接口
 * @author ziv
 * @date 2019-02-14
 */
@Mapper
public interface BcMinaProductMapper {
    /**
     * 通过用户ID获取appId列表
     * @param userId 用户主键
     * @return String appId列表
     */
    String getAppIdList(int userId);
}
