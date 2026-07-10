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
import com.aspms.entity.Task;
import com.aspms.service.TaskService;
import com.aspms.entity.User;
import com.aspms.service.UserService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 任务信息 前后端交互类
 */
@Controller
@RequestMapping(value = "/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    /**
     * 	添加任务信息
     * @param task
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/add.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult addTaskInfo(@RequestBody Task task, HttpServletRequest request) throws IOException {
        User user = (User)request.getSession().getAttribute("loginUser");
        task.setStatus("未完成");
        task.setPhoto("-");
        task.setCompletion("-");
        task.setOperator(user.getUsername());
        // 添加任务信息
        if(taskService.doAddTaskInfo(task)) {
            // 添加任务信息成功
            return ResponseResult.build(true, "添加任务信息成功！");
        }else {
            // 添加任务信息失败
            return ResponseResult.build(false, "添加任务信息失败，可能是当前任务信息已存在！");
        }
    }

    /**
     * 	修改任务信息
     * @param task
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/modify.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult modifyTaskInfo(@RequestBody Task task, HttpServletRequest request) throws IOException {
        // 修改任务信息
        if(taskService.doModifyTaskInfo(task)) {
            // 修改任务信息成功
            return ResponseResult.build(true, "修改任务信息成功！");
        }else {
            // 修改任务信息失败
            return ResponseResult.build(false, "修改任务信息失败，可能是当前任务信息不存在！");
        }
    }

    /**
     * 	修改任务信息
     * @param task
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/task.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult modifyTaskInfotask(@RequestBody Task task, HttpServletRequest request) throws IOException {
        Task t=taskService.doGetTaskInfo(task);
        t.setStatus("已完成");
        // 修改任务信息
        if(taskService.doModifyTaskInfo(t)) {
            // 修改任务信息成功
            return ResponseResult.build(true, "修改任务信息成功！");
        }else {
            // 修改任务信息失败
            return ResponseResult.build(false, "修改任务信息失败，可能是当前任务信息不存在！");
        }
    }

    /**
     * 	删除任务信息
     * @param task
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/delete.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult deleteTaskInfo(@RequestBody Task task, HttpServletRequest request) throws IOException {
        // 删除任务信息
        if(taskService.doDeleteTaskInfo(task)) {
            // 删除任务信息成功
            return ResponseResult.build(true, "删除任务信息成功！");
        }else {
            // 删除任务信息失败
            return ResponseResult.build(false, "删除任务信息失败，可能是当前任务信息不存在！");
        }
    }

    /**
     * 	获取任务信息
     * @param task
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/get.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getTaskInfo(@RequestBody Task task, HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取任务信息
        Task pTask = taskService.doGetTaskInfo(task);
        // 判断任务信息是否存在
        if(pTask != null){
            // 填充员工信息
            pTask.setUser(userService.doGetUserInfo(new User(pTask.getUid())));
        }
        // 返回数据
        return ResponseResult.build(pTask!=null, null, pTask);
    }

    /**
     * 	获取任务列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/list.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getTaskInfoList(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取任务列表
        List<Task> list = taskService.doGetTaskInfoList();
        // 填充信息
        list.forEach(each->{
            // 填充员工信息
            each.setUser(userService.doGetUserInfo(new User(each.getUid())));
        });
        // 返回数据
        return ResponseResult.build(list!=null, null, list);
    }

    /**
     * 	获取任务列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/listByuid.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getTaskInfoListByuid(@RequestBody Task task, HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取任务列表
        List<Task> list = taskService.doGetTaskInfoListByuid(task);
        // 填充信息
        list.forEach(each->{
            // 填充员工信息
            each.setUser(userService.doGetUserInfo(new User(each.getUid())));
        });
        // 返回数据
        return ResponseResult.build(list!=null, null, list);
    }

    /**
     * 	查询任务列表
     * @param task
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/query.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult queryTaskInfoList(@RequestBody Task task, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 查询任务列表
        List<Task> list = taskService.doQueryTaskInfoList(task);
        // 填充信息
        list.forEach(each->{
            // 填充员工信息
            each.setUser(userService.doGetUserInfo(new User(each.getUid())));
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
