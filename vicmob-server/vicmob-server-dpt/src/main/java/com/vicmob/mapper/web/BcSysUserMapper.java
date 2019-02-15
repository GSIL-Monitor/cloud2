package com.vicmob.mapper.web;

import com.vicmob.entity.BcSysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 炫销宝用户实体类
 * @author ziv
 * @date 2019-02-14
 */
@Mapper
public interface BcSysUserMapper {

    /**
     * 通过用户主键查询用户信息
     * @param userId 用户主键
     * @return BcSysUser 用户信息
     */
    BcSysUser findByUserId(int userId);

    /**
     * 通过用户名获取用户炫店小程序的小程序appId
     * @param loginName 用户名
     * @return String 小程序appId
     */
    String getAppIdByName(String loginName);
}
