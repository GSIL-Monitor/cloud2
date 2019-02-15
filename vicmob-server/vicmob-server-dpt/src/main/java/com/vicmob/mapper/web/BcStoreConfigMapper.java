package com.vicmob.mapper.web;

import com.vicmob.entity.BcStoreConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商城配置数据持久层接口
 * @author ziv
 * @date 2019-02-14
 */
@Mapper
public interface BcStoreConfigMapper {
    /**
     * 通过用户Id获取商城配置
     * @param userId 用户主键
     * @return BcStoreConfig 商城配置信息
     */
    BcStoreConfig findByUserId(int userId);
}
