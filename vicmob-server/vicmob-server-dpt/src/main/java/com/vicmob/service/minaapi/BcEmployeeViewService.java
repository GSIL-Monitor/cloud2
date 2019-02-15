package com.vicmob.service.minaapi;

import com.vicmob.entity.BcEmployeeView;

import java.util.List;

/**
 * 员工浏览信息业务逻辑接口
 * @author ricky
 * @date 2019-01-30
 */
public interface BcEmployeeViewService {

    /**
     * 根据员工Id查询所有浏览信息
     * @param employeeId 员工id
     * @return List<BcEmployeeView>浏览信息list集合
     */
    List<BcEmployeeView> selectByEmpId(Integer employeeId);

    /**
     * 根据员工Id和客户Id查询用户浏览信息
     * @param bcEmployeeView 员工Id和客户Id
     * @return  用户浏览信息
     */
    BcEmployeeView selectByEmpAndCus(BcEmployeeView bcEmployeeView);

    /**
     * 插入员工浏览数
     * @param bcEmployeeView 浏览信息
     * @return 是否插入成功（0失败；1成功）
     */
    int insertSelective(BcEmployeeView bcEmployeeView);

    /**
     * 根据员工Id和客户Id更新用户浏览信息
     * @param bcEmployeeView 员工Id和客户Id
     * @return  是否插入成功（0失败；1成功）
     */
    int updateByEmpAndCus(BcEmployeeView bcEmployeeView);


}
