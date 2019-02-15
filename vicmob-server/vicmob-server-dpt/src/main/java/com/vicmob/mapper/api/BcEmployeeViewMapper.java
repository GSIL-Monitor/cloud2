package com.vicmob.mapper.api;

import com.vicmob.entity.BcEmployeeView;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 员工名片浏览信息持久层接口
 * @author ricky
 * @date 2019-01-30
 */
@Mapper
public interface BcEmployeeViewMapper {

    /**
     * 根据员工Id查询所有点赞信息
     * @param employeeId 员工id
     * @return List<BcEmployeeView>所有点赞的集合
     */
    List<BcEmployeeView> selectByEmpId(Integer employeeId);

    /**
     * 根据员工Id和客户Id查询用户浏览信息
     * @param bcEmployeeView 员工Id和客户Id
     * @return 用户浏览信息
     */
    BcEmployeeView selectByEmpAndCus(BcEmployeeView bcEmployeeView);

    /**
     * 插入员工浏览数
     * @param record  浏览信息
     * @return 是否插入成功（0失败；1成功）
     */
    int insertSelective(BcEmployeeView record);

    /**
     * 根据员工Id和客户Id更新用户浏览信息
     * @param bcEmployeeView 员工Id和客户Id
     * @return  是否插入成功（0失败；1成功）
     */
    int updateByEmpAndCus(BcEmployeeView bcEmployeeView);
}
