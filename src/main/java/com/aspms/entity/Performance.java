package com.aspms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 绩效考核信息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Performance {

    // 绩效编号
    private Integer pfmcid;
    // 员工编号
    private Integer uid;
    // 考核周期
    private String period;
    // 考核结果
    private String result;
    // 考核状态
    private String status;
    // 原因
    private String appeal;
    // 更新时间
    private String utime;

    // 员工信息
    public User user;

    /**
     * 带参构造函数
     */
    public Performance(Integer pfmcid) {
        this.pfmcid = pfmcid;
    }

}
