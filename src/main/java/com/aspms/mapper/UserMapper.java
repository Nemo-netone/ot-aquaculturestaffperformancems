package com.aspms.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.aspms.entity.User;

/**
 * 员工信息 Mapper接口
 */
@Mapper
public interface UserMapper {

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
    int doRegisterUserInfo(User user);

    /**
     * 修改员工密码
     * @param user
     * @return
     */
    int doModifyPasswordInfo(User user);

    /**
     * 添加员工信息
     * @param user
     * @return
     */
    int doAddUserInfo(User user);

    /**
     * 修改员工信息
     * @param user
     * @return
     */
    int doModifyUserInfo(User user);

    /**
     * 删除员工信息
     * @param user
     * @return
     */
    int doDeleteUserInfo(User user);

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
