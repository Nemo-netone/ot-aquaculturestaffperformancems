package com.aspms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 养殖岗位信息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    // 岗位编号
    private Integer pid;
    // 部门编号
    private Integer did;
    // 岗位名称
    private String pname;
    // 职责描述
    private String description;
    // 岗位要求
    private String requirements;
    // 养殖部门信息
    public Department department;

    /**
     * 带参构造函数
     */
    public Position(Integer pid) {
        this.pid = pid;
    }

}
