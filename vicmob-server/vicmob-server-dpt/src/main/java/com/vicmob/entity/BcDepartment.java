package com.vicmob.entity;

import lombok.Data;

import java.util.List;

/**
 * 部门实体类
 * @author ricky
 * @date 2019-01-29
 */
@Data
public class BcDepartment {
    /**
     * 部门ID
     */
    private Integer departmentId;

    /**
     * 管理员id
     */
    private Integer userId;

    /**
     * // 父级ID（来源于一级部门ID，一级部门为0）
     */
    private Integer parentId;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 删除标记（0：删除；1：未删除）
     */
    private Integer deleteFlag;

    /**
     * 微信部门ID
     */
    private Integer wxdptId;

    /**
     * 员工数
     */
    private Integer employeeCount;

    /**
     * 二级部门数
     */
    private Integer secDepartmentCount;

    /**
     * 二级分类集合
     */
    private List<BcDepartment> secDepartmentList;

    /**
     * 二级分类集合
     */
    private List<BcEmployee> BcEmployeeList;


    //雷达页面所匹配字段
    /**
     * 部门名
     */
    private String text;
    /**
     * 子集分类
     */
    private List<BcDepartment> nodes;
    /**
     * 父级ID（来源于一级部门ID，一级部门为0）
     */
    private Integer dpParentId;
    /**
     * 部门父类
     */
    private BcDepartment bcDepartmentParent;
    /**
     * 父类ID集合
     */
    private String parentwxdptIds;
    /**
     * 部门状态
     */
    private BcDepartmentState state;
    /**
     * 有意义
     */
    private Integer wxdptIdTemp;
}
