package com.aspms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 工作信息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Work {

    // 工作编号
    private Integer wid;
    // 员工编号
    private Integer uid;
    // 平均日增重(kg/只)
    private String data;
    // 动物物种
    private String remarks;
    private String worklist;
    // 记录日期
    private String time;
    // 照片
    private String photo;
    // 员工信息
    public User user;

    /**
     * 带参构造函数
     */
    public Work(Integer wid) {
        this.wid = wid;
    }

    public Work(Integer wid,Integer uid) {
        this.uid = uid;
    }

}
