package com.vicmob.mapper.minathird;

import com.vicmob.entity.MinaTemplate;
import org.apache.ibatis.annotations.Mapper;

/**
 * 小程序模板数据持久层接口
 * @author ziv
 * @date 2019-02-13
 */
@Mapper
public interface MinaTemplateMapper {

    /**
     * 获取小程序模板
     * @return MinaTemplate 小程序模板信息
     */
    MinaTemplate getTemplate();
}
