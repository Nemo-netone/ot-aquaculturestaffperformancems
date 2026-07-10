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
import com.aspms.entity.Position;
import com.aspms.service.PositionService;
import com.aspms.entity.Department;
import com.aspms.service.DepartmentService;

/**
 * 养殖岗位信息 前后端交互类
 */
@Controller
@RequestMapping(value = "/api/position")
public class PositionController {

    @Autowired
    private PositionService positionService;
    @Autowired
    private DepartmentService departmentService;

    /**
     * 	添加养殖岗位信息
     * @param position
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/add.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult addPositionInfo(@RequestBody Position position, HttpServletRequest request) throws IOException {
        // 添加养殖岗位信息
        if(positionService.doAddPositionInfo(position)) {
            // 添加养殖岗位信息成功
            return ResponseResult.build(true, "添加养殖岗位信息成功！");
        }else {
            // 添加养殖岗位信息失败
            return ResponseResult.build(false, "添加养殖岗位信息失败，可能是当前养殖岗位信息已存在！");
        }
    }

    /**
     * 	修改养殖岗位信息
     * @param position
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/modify.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult modifyPositionInfo(@RequestBody Position position, HttpServletRequest request) throws IOException {
        // 修改养殖岗位信息
        if(positionService.doModifyPositionInfo(position)) {
            // 修改养殖岗位信息成功
            return ResponseResult.build(true, "修改养殖岗位信息成功！");
        }else {
            // 修改养殖岗位信息失败
            return ResponseResult.build(false, "修改养殖岗位信息失败，可能是当前养殖岗位信息不存在！");
        }
    }

    /**
     * 	删除养殖岗位信息
     * @param position
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/delete.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult deletePositionInfo(@RequestBody Position position, HttpServletRequest request) throws IOException {
        // 删除养殖岗位信息
        if(positionService.doDeletePositionInfo(position)) {
            // 删除养殖岗位信息成功
            return ResponseResult.build(true, "删除养殖岗位信息成功！");
        }else {
            // 删除养殖岗位信息失败
            return ResponseResult.build(false, "删除养殖岗位信息失败，可能是当前养殖岗位信息不存在！");
        }
    }

    /**
     * 	获取养殖岗位信息
     * @param position
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/get.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getPositionInfo(@RequestBody Position position, HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取养殖岗位信息
        Position pPosition = positionService.doGetPositionInfo(position);
        // 判断养殖岗位信息是否存在
        if(pPosition != null){
            // 填充养殖部门信息
            pPosition.setDepartment(departmentService.doGetDepartmentInfo(new Department(pPosition.getDid())));
        }
        // 返回数据
        return ResponseResult.build(pPosition!=null, null, pPosition);
    }

    /**
     * 	获取养殖岗位列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/list.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getPositionInfoList(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取养殖岗位列表
        List<Position> list = positionService.doGetPositionInfoList();
        // 填充信息
        list.forEach(each->{
            // 填充养殖部门信息
            each.setDepartment(departmentService.doGetDepartmentInfo(new Department(each.getDid())));
        });
        // 返回数据
        return ResponseResult.build(list!=null, null, list);
    }

    @RequestMapping(value = "/positionNum.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult positionNum(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取员工列表
        List<Position> list = positionService.doGetPositionInfoList();
        int positionCount = list.size();
        // 返回数据
        return ResponseResult.build(positionCount!=0, null, positionCount);
    }

    /**
     * 	获取养殖岗位列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/listBydid.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getPositionInfoListBydid(@RequestBody Position position,HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取养殖岗位列表
        List<Position> list = positionService.doGetPositionInfoListBydid(position);
        // 填充信息
        list.forEach(each->{
            // 填充养殖部门信息
            each.setDepartment(departmentService.doGetDepartmentInfo(new Department(each.getDid())));
        });
        // 返回数据
        return ResponseResult.build(list!=null, null, list);
    }

    /**
     * 	查询养殖岗位列表
     * @param position
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/query.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult queryPositionInfoList(@RequestBody Position position, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 查询养殖岗位列表
        List<Position> list = positionService.doQueryPositionInfoList(position);
        // 填充信息
        list.forEach(each->{
            // 填充养殖部门信息
            each.setDepartment(departmentService.doGetDepartmentInfo(new Department(each.getDid())));
        });
        // 返回数据
        return ResponseResult.build(list!=null, null, list);
    }

}
