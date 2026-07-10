package com.aspms.controller;

import com.aspms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.aspms.utils.ResponseResult;
import com.aspms.entity.Department;
import com.aspms.service.DepartmentService;

/**
 * 养殖部门信息 前后端交互类
 */
@Controller
@RequestMapping(value = "/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 	添加养殖部门信息
     * @param department
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/add.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult addDepartmentInfo(@RequestBody Department department, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("loginUser");
        department.setOperator(user.getUsername());
        department.setCtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        // 添加养殖部门信息
        if(departmentService.doAddDepartmentInfo(department)) {
            // 添加养殖部门信息成功
            return ResponseResult.build(true, "添加养殖部门信息成功！");
        }else {
            // 添加养殖部门信息失败
            return ResponseResult.build(false, "添加养殖部门信息失败，可能是当前养殖部门信息已存在！");
        }
    }

    /**
     * 	修改养殖部门信息
     * @param department
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/modify.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult modifyDepartmentInfo(@RequestBody Department department, HttpServletRequest request) throws IOException {
        // 修改养殖部门信息
        if(departmentService.doModifyDepartmentInfo(department)) {
            // 修改养殖部门信息成功
            return ResponseResult.build(true, "修改养殖部门信息成功！");
        }else {
            // 修改养殖部门信息失败
            return ResponseResult.build(false, "修改养殖部门信息失败，可能是当前养殖部门信息不存在！");
        }
    }

    /**
     * 	删除养殖部门信息
     * @param department
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/delete.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult deleteDepartmentInfo(@RequestBody Department department, HttpServletRequest request) throws IOException {
        // 删除养殖部门信息
        if(departmentService.doDeleteDepartmentInfo(department)) {
            // 删除养殖部门信息成功
            return ResponseResult.build(true, "删除养殖部门信息成功！");
        }else {
            // 删除养殖部门信息失败
            return ResponseResult.build(false, "删除养殖部门信息失败，可能是当前养殖部门信息不存在！");
        }
    }

    /**
     * 	获取养殖部门信息
     * @param department
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/get.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getDepartmentInfo(@RequestBody Department department, HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取养殖部门信息
        Department pDepartment = departmentService.doGetDepartmentInfo(department);
        // 返回数据
        return ResponseResult.build(pDepartment!=null, null, pDepartment);
    }

    /**
     * 	获取养殖部门列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/list.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getDepartmentInfoList(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取养殖部门列表
        List<Department> list = departmentService.doGetDepartmentInfoList();
        // 返回数据
        return ResponseResult.build(list!=null, null, list);
    }

    /**
     * 	查询养殖部门列表
     * @param department
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/query.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult queryDepartmentInfoList(@RequestBody Department department, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 查询养殖部门列表
        List<Department> list = departmentService.doQueryDepartmentInfoList(department);
        // 返回数据
        return ResponseResult.build(list!=null, null, list);
    }

}
