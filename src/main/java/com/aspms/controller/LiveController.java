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
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

import com.aspms.utils.ResponseResult;
import com.aspms.entity.Live;
import com.aspms.service.LiveService;
import com.aspms.entity.Department;
import com.aspms.service.DepartmentService;

/**
 * 生长指标信息 前后端交互类
 */
@Controller
@RequestMapping(value = "/api/live")
public class LiveController {

    @Autowired
    private LiveService liveService;
    @Autowired
    private DepartmentService departmentService;

    /**
     * 	添加生长指标信息
     * @param live
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/add.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult addLiveInfo(@RequestBody Live live, HttpServletRequest request) throws IOException {
        User user=(User)request.getSession().getAttribute("loginUser");
        live.setOperator(user.getUsername());
        live.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        // 添加生长指标信息
        if(liveService.doAddLiveInfo(live)) {
            // 添加生长指标信息成功
            return ResponseResult.build(true, "添加生长指标信息成功！");
        }else {
            // 添加生长指标信息失败
            return ResponseResult.build(false, "添加生长指标信息失败，可能是当前生长指标信息已存在！");
        }
    }

    /**
     * 	修改生长指标信息
     * @param live
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/modify.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult modifyLiveInfo(@RequestBody Live live, HttpServletRequest request) throws IOException {
        // 修改生长指标信息
        if(liveService.doModifyLiveInfo(live)) {
            // 修改生长指标信息成功
            return ResponseResult.build(true, "修改生长指标信息成功！");
        }else {
            // 修改生长指标信息失败
            return ResponseResult.build(false, "修改生长指标信息失败，可能是当前生长指标信息不存在！");
        }
    }

    /**
     * 	删除生长指标信息
     * @param live
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/delete.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult deleteLiveInfo(@RequestBody Live live, HttpServletRequest request) throws IOException {
        // 删除生长指标信息
        if(liveService.doDeleteLiveInfo(live)) {
            // 删除生长指标信息成功
            return ResponseResult.build(true, "删除生长指标信息成功！");
        }else {
            // 删除生长指标信息失败
            return ResponseResult.build(false, "删除生长指标信息失败，可能是当前生长指标信息不存在！");
        }
    }

    /**
     * 	获取生长指标信息
     * @param live
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/get.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getLiveInfo(@RequestBody Live live, HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取生长指标信息
        Live pLive = liveService.doGetLiveInfo(live);
        // 返回数据
        return ResponseResult.build(pLive!=null, null, pLive);
    }

    /**
     * 	获取生长指标列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/list.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getLiveInfoList(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取生长指标列表
        List<Live> list = liveService.doGetLiveInfoList();

        // 返回数据
        return ResponseResult.build(list!=null, null, list);
    }

    /**
     * 	获取生长指标列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/data.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getLiveDataList(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取生长指标列表
        List<Live> list = liveService.doGetLiveInfoList();

        // 提取 xdata 和 ydata
        List<String> xdata = list.stream().map(Live::getData).collect(Collectors.toList());
        List<String> ydata = list.stream().map(Live::getRemarks).collect(Collectors.toList());

        // 构建返回的数据结构
        Map<String, Object> result = new HashMap<>();
        result.put("xdata", xdata);
        result.put("ydata", ydata);

        // 返回数据
        return ResponseResult.build(true, null, result);
    }

    /**
     * 	查询生长指标列表
     * @param live
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/query.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult queryLiveInfoList(@RequestBody Live live, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 查询生长指标列表
        List<Live> list = liveService.doQueryLiveInfoList(live);
        // 返回数据
        return ResponseResult.build(list!=null, null, list);
    }

}
