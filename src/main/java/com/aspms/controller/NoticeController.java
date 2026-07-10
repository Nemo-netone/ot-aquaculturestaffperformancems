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
import com.aspms.entity.Notice;
import com.aspms.service.NoticeService;

/**
 * 公告信息 前后端交互类
 */
@Controller
@RequestMapping(value = "/api/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 	添加公告信息
     * @param notice
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/add.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult addNoticeInfo(@RequestBody Notice notice, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("loginUser");
        notice.setCtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        notice.setOperator(user.getUsername());
        // 添加公告信息
        if(noticeService.doAddNoticeInfo(notice)) {
            // 添加公告信息成功
            return ResponseResult.build(true, "添加公告信息成功！");
        }else {
            // 添加公告信息失败
            return ResponseResult.build(false, "添加公告信息失败，可能是当前公告信息已存在！");
        }
    }

    /**
     * 	修改公告信息
     * @param notice
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/modify.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult modifyNoticeInfo(@RequestBody Notice notice, HttpServletRequest request) throws IOException {
        // 修改公告信息
        if(noticeService.doModifyNoticeInfo(notice)) {
            // 修改公告信息成功
            return ResponseResult.build(true, "修改公告信息成功！");
        }else {
            // 修改公告信息失败
            return ResponseResult.build(false, "修改公告信息失败，可能是当前公告信息不存在！");
        }
    }

    /**
     * 	删除公告信息
     * @param notice
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/delete.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult deleteNoticeInfo(@RequestBody Notice notice, HttpServletRequest request) throws IOException {
        // 删除公告信息
        if(noticeService.doDeleteNoticeInfo(notice)) {
            // 删除公告信息成功
            return ResponseResult.build(true, "删除公告信息成功！");
        }else {
            // 删除公告信息失败
            return ResponseResult.build(false, "删除公告信息失败，可能是当前公告信息不存在！");
        }
    }

    /**
     * 	获取公告信息
     * @param notice
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/get.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getNoticeInfo(@RequestBody Notice notice, HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取公告信息
        Notice pNotice = noticeService.doGetNoticeInfo(notice);
        // 返回数据
        return ResponseResult.build(pNotice!=null, null, pNotice);
    }

    /**
     * 	获取公告列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/list.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getNoticeInfoList(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取公告列表
        List<Notice> list = noticeService.doGetNoticeInfoList();
        // 返回数据
        return ResponseResult.build(list!=null, null, list);
    }

    /**
     * 	查询公告列表
     * @param notice
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/query.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult queryNoticeInfoList(@RequestBody Notice notice, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 查询公告列表
        List<Notice> list = noticeService.doQueryNoticeInfoList(notice);
        // 返回数据
        return ResponseResult.build(list!=null, null, list);
    }

}
