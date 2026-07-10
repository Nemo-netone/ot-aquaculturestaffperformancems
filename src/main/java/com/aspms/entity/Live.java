package com.aspms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 生长指标信息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Live {

    // 指标编号
    private Integer lid;
    // 日增重(kg/只)
    private String data;
    // 动物物种
    private String remarks;
    // 创建时间
    private String time;
    // 创建人
    private String operator;

    // 养殖部门信息
    public Department department;

    /**
     * 带参构造函数
     */
    public Live(Integer lid) {
        this.lid = lid;
    }

    public Live(String remarks) {
        this.remarks = remarks;
    }
}
