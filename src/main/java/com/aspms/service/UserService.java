package com.aspms.service;

import java.util.List;
import com.aspms.entity.User;

/**
 * 员工信息 业务逻辑接口
 */
public interface UserService {

    /**
     * 登录员工信息
     * @param user
     * @return
     */
    User doLoginUserInfo(User user);

    /**
     * 注册员工信息
     * @param user
     * @return
     */
    boolean doRegisterUserInfo(User user);

    /**
     * 修改员工密码
     * @param user
     * @return
     */
    boolean doModifyPasswordInfo(User user);

    /**
     * 添加员工信息
     * @param user
     * @return
     */
    boolean doAddUserInfo(User user);

    /**
     * 修改员工信息
     * @param user
     * @return
     */
    boolean doModifyUserInfo(User user);

    /**
     * 删除员工信息
     * @param user
     * @return
     */
    boolean doDeleteUserInfo(User user);

    /**
     * 获取员工信息
     * @param user
     * @return
     */
    User doGetUserInfo(User user);

    /**
     * 获取员工列表
     * @return
     */
    List<User> doGetUserInfoList();

    /**
     * 查询员工列表
     * @param user
     * @return
     */
    List<User> doQueryUserInfoList(User user);

}
