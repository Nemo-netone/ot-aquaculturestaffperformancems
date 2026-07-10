package com.aspms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 任务信息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    // 任务编号
    private Integer tid;
    // 员工编号
    private Integer uid;
    // 任务描述
    private String description;
    // 开始日期
    private String stime;
    // 结束日期
    private String etime;
    // 完成状态
    private String status;
    // 完成情况
    private String completion;
    // 任务图片
    private String photo;
    // 创建人
    private String operator;
    // 员工信息
    public User user;

    /**
     * 带参构造函数
     */
    public Task(Integer tid) {
        this.tid = tid;
    }

}
