package com.aspms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 奖惩信息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reward {

    // 奖惩编号
    private Integer rid;
    // 员工编号
    private Integer uid;
    // 奖惩原因
    private String reason;
    // 奖惩类型
    private String type;
    // 奖惩日期
    private String time;
    // 员工信息
    public User user;

    /**
     * 带参构造函数
     */
    public Reward(Integer rid) {
        this.rid = rid;
    }

}
