package com.vicmob.service.minaapi;

import com.vicmob.entity.BcProductClassify;

import java.util.List;

/**
 * 产品分类相关数据业务逻辑接口
 * @author ricky
 * @date 2019-02-11
 */
public interface BcProductClassifyService {

    /**
     *  查询所有的一级分类标签
     *  @param bcProductClassify 小程序Id
     *  @return      一级分类标签集合
     */
    List<BcProductClassify> selectFirstClsaaifyByMinaId(BcProductClassify bcProductClassify);

    /**
     *  通过一级分类Id查询二级分类
     *  @param bcProductClassify 一级分类Id
     *  @return  二级分类集合
     */
    List<BcProductClassify> selectByClassifyId(BcProductClassify bcProductClassify);
}
