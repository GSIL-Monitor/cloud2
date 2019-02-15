package com.vicmob.mapper.mina;

import com.vicmob.entity.BcMinaInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 炫销宝用户小程序数据持久层接口
 * @author ziv
 * @date 2019-02-11
 */
@Mapper
public interface BcMinaInfoMapper {

    /**
     * 通过userId获取用户小程序信息
     * @param userId 用户主键
     * @return BcMinaInfo 用户小程序信息
     */
    BcMinaInfo getByUserId(int userId);

    /**
     * 通过appId获取授权小程序数量
     * @param appId 查询的appId
     * @return int 授权小程序数量
     */
    int getAuthorNumByAppId(String appId);

    /**
     * 新增用户小程序信息
     * @param minaInfo 小程序信息
     */
    void insert(BcMinaInfo minaInfo);

    /**
     * 替换用户小程序信息
     * @param minaInfo 小程序信息
     */
    void renew(BcMinaInfo minaInfo);

    /**
     * 通过minaId获取小程序信息
     * @param minaId 小程序主键
     * @return BcMinaInfo 小程序信息
     */
    BcMinaInfo getByMinaId(int minaId);

    /**
     * 更新授权小程序调用凭证
     * @param minaInfo 授权小程序信息
     */
    void updateAccessToken(BcMinaInfo minaInfo);

    /**
     * 根据minaId获取小程序信息
     * @param minaId 小程序id
     * @return 用户小程序信息
     */
    BcMinaInfo get(@Param("minaId")String minaId);
}
