package com.vicmob.mapper.api;

import com.vicmob.entity.BcDecoration;
import org.apache.ibatis.annotations.Mapper;

/**
 * 官网数据持久层接口
 * @author ricky
 * @date 2019-02-13
 */
@Mapper
public interface BcDecorationMapper {

    /**
     * 通过minaId查询官网信息
     *  @param minaId 小程序Id
     *  @return 官网信息
     */
    BcDecoration selectByMinaId(Integer minaId);
}
