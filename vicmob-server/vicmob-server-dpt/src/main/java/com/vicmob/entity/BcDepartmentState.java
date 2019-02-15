package com.vicmob.entity;

import lombok.Data;

/**
 * 部门状态实体类
 * @author ricky
 * @date 2019-01-29
 */
@Data
public class BcDepartmentState {
    private boolean expanded;
    private boolean checked;
    private boolean selected;
}
