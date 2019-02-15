package com.vicmob.mapper.mina;

import com.vicmob.entity.BcMinaForm;
import org.apache.ibatis.annotations.Mapper;

/**
 * 小程序formId数据持久层接口
 * @author ricky
 * @date 2019-02-13
 */
@Mapper
public interface BcMinaFormDao {

    /**
     * 根据客户ID获取formId
     * @param customerId 客户ID
     * @return formId 信息
     */
    BcMinaForm getByCustomerId (Integer customerId);

    /**
     * 插入formId
     * @param bcMinaForm formId信息
     * @return 1：成功 0：失败
     */
    Integer insert(BcMinaForm bcMinaForm);

    /**
     * 根据主键id删除
     * @param bcMinaForm 主键id
     * @return 1：成功 0：失败
     */
    Integer delete(BcMinaForm bcMinaForm);
}
