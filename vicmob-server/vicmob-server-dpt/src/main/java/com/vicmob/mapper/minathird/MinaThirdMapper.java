package com.vicmob.mapper.minathird;

import com.vicmob.entity.MinaThird;
import org.apache.ibatis.annotations.Mapper;

/**
 * 小程序第三方平台信息数据持久层接口
 * @author ziv
 * @date 2019-01-17
 */
@Mapper
public interface MinaThirdMapper {

    /**
     * 通过appId获取小程序第三方平台信息
     * @param appId 第三方平台appId
     * @return MinaThird 小程序第三方平台信息
     */
    MinaThird getByAppId(String appId);

    /**
     * 新增小程序第三方平台信息
     * @param minaThird 第三方平台信息
     */
    void insert(MinaThird minaThird);

    /**
     * 更新ticket
     * @param minaThird 第三方平台信息
     */
    void updateTicket(MinaThird minaThird);

    /**
     * 更新accessToken
     * @param minaThird 第三方平台信息
     */
    void updateToken(MinaThird minaThird);
}
