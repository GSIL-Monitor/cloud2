package com.vicmob.service.minaapi;

import com.vicmob.entity.BcInformation;

import java.util.List;

/**
 * 企业资讯业务逻辑接口
 * @author ricky
 * @date 2019-02-13
 */
public interface BcInformationService {

    /**
     * 根据主键查询企业资讯
     *  @param infoId 主键
     *  @return 企业资讯
     */
    BcInformation selectByPrimaryKey(Integer infoId);

    /**
     * 通过minaId查询资讯信息
     *  @param minaId 小程序Id
     *  @return  资讯信息
     */
    List<BcInformation> selectByMinaId(Integer minaId);
}
