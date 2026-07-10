package com.aspms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aspms.service.UserService;
import com.aspms.mapper.UserMapper;
import com.aspms.entity.User;

/**
 * 员工信息 业务逻辑实现类
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    public UserMapper userMapper;

    /**
     * 登录员工信息
     * @param user
     * @return
     */
    @Override
    public User doLoginUserInfo(User user) {
        return userMapper.doLoginUserInfo(user);
    }

    /**
     * 注册员工信息
     * @param user
     * @return
     */
    @Override
    public boolean doRegisterUserInfo(User user) {

        return userMapper.doRegisterUserInfo(user)!=0;

    }

    /**
     * 修改密码信息
     * @param user
     * @return
     */
    @Override
    public boolean doModifyPasswordInfo(User user) {
        return userMapper.doModifyPasswordInfo(user)!=0;
    }

    /**
     * 添加员工信息
     * @param user
     * @return
     */
    @Override
    public boolean doAddUserInfo(User user) {

        return userMapper.doAddUserInfo(user)!=0;

    }

    /**
     * 修改员工信息
     * @param user
     * @return
     */
    @Override
    public boolean doModifyUserInfo(User user) {
        return userMapper.doModifyUserInfo(user)!=0;
    }

    /**
     * 删除员工信息
     * @param user
     * @return
     */
    @Override
    public boolean doDeleteUserInfo(User user) {
        return userMapper.doDeleteUserInfo(user)!=0;
    }

    /**
     * 获取员工信息
     * @param user
     * @return
     */
    @Override
    public User doGetUserInfo(User user) {
        return userMapper.doGetUserInfo(user);
    }

    /**
     * 获取员工列表
     * @return
     */
    @Override
    public List<User> doGetUserInfoList() {
        return userMapper.doGetUserInfoList();
    }

    /**
     * 查询员工列表
     * @param user
     * @return
     */
    @Override
    public List<User> doQueryUserInfoList(User user) {
        return userMapper.doQueryUserInfoList(user);
    }

}
