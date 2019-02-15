package com.vicmob.mapper.api;

import com.vicmob.entity.BcInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 企业资讯数据持久层接口
 * @author ricky
 * @date 2019-02-13
 */
@Mapper
public interface BcInformationMapper {

    /**
     * 根据主键删除企业资讯
     *  @param infoId 主键
     *  @return   是否删除成功（0失败；1成功）
     */
    int deleteByPrimaryKey(Integer infoId);

    /**
     * 插入企业资讯
     *  @param record 企业资讯
     *  @return 是否插入成功（0失败；1成功）
     */
    int insertSelective(BcInformation record);

    /**
     * 根据主键查询企业资讯
     *  @param infoId 主键
     *  @return 企业资讯
     */
    BcInformation selectByPrimaryKey(Integer infoId);

    /**
     * 更新企业资讯
     *  @param record 企业资讯
     *  @return  是否更新成功（0失败；1成功）
     */
    int updateByPrimaryKeySelective(BcInformation record);

    /**
     * 通过minaId查询资讯信息
     *  @param minaId 小程序Id
     *  @return  资讯信息
     */
    List<BcInformation> selectByMinaId(Integer minaId);
}
