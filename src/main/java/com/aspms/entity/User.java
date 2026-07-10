package com.aspms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 员工信息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    // 员工编号
    private Integer uid;
    // 部门编号
    private Integer did;
    // 岗位编号
    private Integer pid;
    // 用户名
    private String username;
    // 登录密码
    private String password;
    // 员工身份
    private String identify;
    // 姓名
    private String name;
    // 性别
    private String sex;
    // 年龄
    private String age;
    // 出生日期
    private String birthdate;
    // 身份证号
    private String idcard;
    // 联系方式
    private String phone;
    // 头像
    private String avatar;
    // 养殖部门信息
    public Department department;
    // 养殖岗位信息
    public Position position;

    /**
     * 带参构造函数
     */
    public User(Integer uid) {
        this.uid = uid;
    }

}
