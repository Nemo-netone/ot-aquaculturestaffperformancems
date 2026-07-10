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
import com.aspms.entity.Work;
import com.aspms.service.WorkService;
import com.aspms.entity.User;
import com.aspms.service.UserService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 工作信息 前后端交互类
 */
@Controller
@RequestMapping(value = "/api/work")
public class WorkController {

    @Autowired
    private WorkService workService;
    @Autowired
    private UserService userService;

    /**
     * 	添加工作信息
     * @param work
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/add.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult addWorkInfo(@RequestBody Work work, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("loginUser");
        work.setUid(user.getUid());
        work.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        // 添加工作信息
        if(workService.doAddWorkInfo(work)) {
            // 添加工作信息成功
            return ResponseResult.build(true, "添加工作信息成功！");
        }else {
            // 添加工作信息失败
            return ResponseResult.build(false, "添加工作信息失败，可能是当前工作信息已存在！");
        }
    }

    /**
     * 	修改工作信息
     * @param work
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/modify.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult modifyWorkInfo(@RequestBody Work work, HttpServletRequest request) throws IOException {
        // 修改工作信息
        if(workService.doModifyWorkInfo(work)) {
            // 修改工作信息成功
            return ResponseResult.build(true, "修改工作信息成功！");
        }else {
            // 修改工作信息失败
            return ResponseResult.build(false, "修改工作信息失败，可能是当前工作信息不存在！");
        }
    }

    /**
     * 	删除工作信息
     * @param work
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/delete.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult deleteWorkInfo(@RequestBody Work work, HttpServletRequest request) throws IOException {
        // 删除工作信息
        if(workService.doDeleteWorkInfo(work)) {
            // 删除工作信息成功
            return ResponseResult.build(true, "删除工作信息成功！");
        }else {
            // 删除工作信息失败
            return ResponseResult.build(false, "删除工作信息失败，可能是当前工作信息不存在！");
        }
    }

    /**
     * 	获取工作信息
     * @param work
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/get.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getWorkInfo(@RequestBody Work work, HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取工作信息
        Work pWork = workService.doGetWorkInfo(work);
        // 判断工作信息是否存在
        if(pWork != null){
            // 填充员工信息
            pWork.setUser(userService.doGetUserInfo(new User(pWork.getUid())));
        }
        // 返回数据
        return ResponseResult.build(pWork!=null, null, pWork);
    }

    /**
     * 	获取工作列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/list.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getWorkInfoList(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取工作列表
        List<Work> list = workService.doGetWorkInfoList();
        // 填充信息
        list.forEach(each->{
            // 填充员工信息
            each.setUser(userService.doGetUserInfo(new User(each.getUid())));
        });
        // 返回数据
        return ResponseResult.build(list!=null, null, list);
    }

    /**
     * 	获取工作列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/listByuid.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getWorkInfoListByuid(@RequestBody Work work,HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取工作列表
        List<Work> list = workService.doGetWorkInfoListByuid(work);
        // 填充信息
        list.forEach(each->{
            // 填充员工信息
            each.setUser(userService.doGetUserInfo(new User(each.getUid())));
        });
        // 返回数据
        return ResponseResult.build(list!=null, null, list);
    }

    /**
     * 	查询工作列表
     * @param work
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/query.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult queryWorkInfoList(@RequestBody Work work, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 查询工作列表
        List<Work> list = workService.doQueryWorkInfoList(work);
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
