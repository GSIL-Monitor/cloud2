package com.vicmob.mapper.web;

import com.vicmob.entity.BcMinaPublish;
import org.apache.ibatis.annotations.Mapper;

/**
 * 小程序发布信息数据持久层接口
 * @author ziv
 * @date 2019-02-14
 */
@Mapper
public interface BcMinaPublishMapper {

    /**
     * 通过minaId获取小程序发布信息
     * @param minaId 小程序Id
     * @return BcMinaPublish 小程序发布信息
     */
    BcMinaPublish findByMinaId(int minaId);
}
