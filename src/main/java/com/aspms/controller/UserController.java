package com.aspms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.aspms.utils.ResponseResult;
import com.aspms.entity.User;
import com.aspms.service.UserService;
import com.aspms.entity.Department;
import com.aspms.service.DepartmentService;
import com.aspms.entity.Position;
import com.aspms.service.PositionService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 员工信息 前后端交互类
 */
@Controller
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;

    /**
     * 	登录员工信息
     * @param user
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/login.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult loginUserInfo(@RequestBody User user, HttpServletRequest request) throws IOException {
        // 登录员工信息
        User lgUser = userService.doLoginUserInfo(user);
        // 判断员工登录是否成功
        if(lgUser!=null){
            // 登录员工成功
            // 设置Session
            request.getSession().setAttribute("loginUser", lgUser);
            // 修改员工登录时间
            userService.doModifyUserInfo(lgUser);
            return ResponseResult.build(true, null);
        }else{
            // 登录失败
            return ResponseResult.build(false, "登录失败，请检查用户名或登录密码是否正确！");
        }
    }

    /**
     * 	注册员工信息
     * @param user
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/register.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult registerUserInfo(@RequestBody User user, HttpServletRequest request) throws IOException {
        // 赋值默认信息
        user.setName("-");
        user.setSex(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        // 注册员工信息
        if(userService.doRegisterUserInfo(user)) {
            // 注册员工信息成功
            return ResponseResult.build(true, "注册员工信息成功！");
        }else {
            // 注册员工信息失败
            return ResponseResult.build(false, "注册员工信息失败，当前员工信息已存在！");
        }
    }

    /**
     * 	注销员工信息
     * @param user
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/logout.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult logoutUserInfo(User user, HttpServletRequest request) throws IOException {
        // 设置Session
        request.getSession().setAttribute("loginUser", null);
        return ResponseResult.build(true, null);
    }

    /**
     * 	修改密码信息
     * @param user
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/modifyPassword.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult modifyPasswordInfo(@RequestBody User user, HttpServletRequest request) throws IOException {
        // 获取当前登录的员工信息
        User lgUser = (User)request.getSession().getAttribute("loginUser");
        // 重新获取当前员工信息
        lgUser = userService.doGetUserInfo(lgUser);
        // 赋值员工原密码信息
        lgUser.setPassword(user.getPassword());
        // 赋值员工新密码信息
        lgUser.setIdentify(user.getIdentify());
        // 修改密码信息
        if(userService.doModifyPasswordInfo(lgUser)) {
            // 修改密码信息成功
            return ResponseResult.build(true, "修改密码信息成功！");
        }else {
            // 修改密码信息失败
            return ResponseResult.build(false, "修改密码信息失败，请检查原登录密码是否正确！");
        }
    }

    /**
     * 	添加员工信息
     * @param user
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/add.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult addUserInfo(@RequestBody User user, HttpServletRequest request) throws IOException {
        // 添加员工信息
        if(userService.doAddUserInfo(user)) {
            // 添加员工信息成功
            return ResponseResult.build(true, "添加员工信息成功！");
        }else {
            // 添加员工信息失败
            return ResponseResult.build(false, "添加员工信息失败，可能是当前员工信息已存在！");
        }
    }

    /**
     * 	修改员工信息
     * @param user
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/modify.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult modifyUserInfo(@RequestBody User user, HttpServletRequest request) throws IOException {
        // 修改员工信息
        if(userService.doModifyUserInfo(user)) {
            // 修改员工信息成功
            return ResponseResult.build(true, "修改员工信息成功！");
        }else {
            // 修改员工信息失败
            return ResponseResult.build(false, "修改员工信息失败，可能是当前员工信息不存在！");
        }
    }

    /**
     * 	删除员工信息
     * @param user
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/delete.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult deleteUserInfo(@RequestBody User user, HttpServletRequest request) throws IOException {
        // 删除员工信息
        if(userService.doDeleteUserInfo(user)) {
            // 删除员工信息成功
            return ResponseResult.build(true, "删除员工信息成功！");
        }else {
            // 删除员工信息失败
            return ResponseResult.build(false, "删除员工信息失败，可能是当前员工信息不存在！");
        }
    }

    /**
     * 	获取员工信息
     * @param user
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/get.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getUserInfo(@RequestBody User user, HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取员工信息
        User pUser = userService.doGetUserInfo(user);
        // 判断员工信息是否存在
        if(pUser != null){
            // 填充养殖部门信息
            pUser.setDepartment(departmentService.doGetDepartmentInfo(new Department(pUser.getDid())));
            // 填充养殖岗位信息
            pUser.setPosition(positionService.doGetPositionInfo(new Position(pUser.getPid())));
        }
        // 返回数据
        return ResponseResult.build(pUser!=null, null, pUser);
    }

    /**
     * 	获取员工列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/list.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getUserInfoList(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取员工列表
        List<User> list = userService.doGetUserInfoList();
        // 填充信息
        list.forEach(each->{
            // 填充养殖部门信息
            each.setDepartment(departmentService.doGetDepartmentInfo(new Department(each.getDid())));
            // 填充养殖岗位信息
            each.setPosition(positionService.doGetPositionInfo(new Position(each.getPid())));
        });
        // 返回数据
        return ResponseResult.build(list!=null, null, list);
    }// 计算用户数量

    /**
     * 	获取员工列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/userNum.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult userNum(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取员工列表
        List<User> list = userService.doGetUserInfoList();
        int userCount = list.size();
        // 返回数据
        return ResponseResult.build(userCount!=0, null, userCount);
    }

    /**
     * 	查询员工列表
     * @param user
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/query.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult queryUserInfoList(@RequestBody User user,HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 查询员工列表
        List<User> list = userService.doQueryUserInfoList(user);
        // 填充信息
        list.forEach(each->{
            // 填充养殖部门信息
            each.setDepartment(departmentService.doGetDepartmentInfo(new Department(each.getDid())));
            // 填充养殖岗位信息
            each.setPosition(positionService.doGetPositionInfo(new Position(each.getPid())));
        });
        // 返回数据
        return ResponseResult.build(list!=null, null, list);
    }
    /**
     * 上传文件
     * @param uploadfile
     * @param request
     * @return
     */
    @RequestMapping("/upload.do")
    @ResponseBody
    public String upload(@RequestParam(value="file") MultipartFile uploadfile, HttpServletRequest request) {
        //准备上传
        try {
            //获取原文件名
            String oriFilename=uploadfile.getOriginalFilename();
            //获取原文件扩展名
            String extFilename=oriFilename.substring(oriFilename.lastIndexOf("."));
            //生成新文件名,使用UUID
            String stoFilename= UUID.randomUUID()+extFilename;
            //生成文件存储路径
            File filePath=new File(request.getServletContext().getRealPath("") + "img\\"+stoFilename);
            //开始上传
            uploadfile.transferTo(filePath);
            //返回结果
            return stoFilename;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //上传失败
        return "false";
    }
}
