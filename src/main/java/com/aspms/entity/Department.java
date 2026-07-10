package com.aspms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 养殖部门信息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    // 部门编号
    private Integer did;
    // 部门名称
    private String dname;
    // 部门描述
    private String description;
    // 创建时间
    private String ctime;
    // 创建人
    private String operator;

    /**
     * 带参构造函数
     */
    public Department(Integer did) {
        this.did = did;
    }

}
