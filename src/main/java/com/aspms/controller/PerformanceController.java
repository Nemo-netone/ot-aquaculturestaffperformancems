package com.aspms.controller;

import com.aspms.entity.Live;
import com.aspms.entity.Work;
import com.aspms.service.LiveService;
import com.aspms.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.aspms.utils.ResponseResult;
import com.aspms.entity.Performance;
import com.aspms.service.PerformanceService;
import com.aspms.entity.User;
import com.aspms.service.UserService;

/**
 * 绩效考核信息 前后端交互类
 */
@Controller
@RequestMapping(value = "/api/performance")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;
    @Autowired
    private UserService userService;
    @Autowired
    private WorkService workService;
    @Autowired
    private LiveService liveService;

    /**
     * 	添加绩效考核信息
     * @param performance
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/add.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult addPerformanceInfo(@RequestBody Performance performance, HttpServletRequest request) throws IOException {
        performance.setResult("-");
        performance.setStatus("考核中");
        performance.setAppeal("-");
        performance.setUtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        // 添加绩效考核信息
        if(performanceService.doAddPerformanceInfo(performance)) {
            // 添加绩效考核信息成功
            return ResponseResult.build(true, "添加绩效考核信息成功！");
        }else {
            // 添加绩效考核信息失败
            return ResponseResult.build(false, "添加绩效考核信息失败，可能是当前绩效考核信息已存在！");
        }
    }

    /**
     * 	修改绩效考核信息
     * @param performance
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/modify.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult modifyPerformanceInfo(@RequestBody Performance performance, HttpServletRequest request) throws IOException {
        performance.setUtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        // 修改绩效考核信息
        if(performanceService.doModifyPerformanceInfo(performance)) {
            // 修改绩效考核信息成功
            return ResponseResult.build(true, "修改绩效考核信息成功！");
        }else {
            // 修改绩效考核信息失败
            return ResponseResult.build(false, "修改绩效考核信息失败，可能是当前绩效考核信息不存在！");
        }
    }


    /**
     * 	删除绩效考核信息
     * @param performance
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/delete.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult deletePerformanceInfo(@RequestBody Performance performance, HttpServletRequest request) throws IOException {
        // 删除绩效考核信息
        if(performanceService.doDeletePerformanceInfo(performance)) {
            // 删除绩效考核信息成功
            return ResponseResult.build(true, "删除绩效考核信息成功！");
        }else {
            // 删除绩效考核信息失败
            return ResponseResult.build(false, "删除绩效考核信息失败，可能是当前绩效考核信息不存在！");
        }
    }

    /**
     * 	获取绩效考核信息
     * @param performance
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/get.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getPerformanceInfo(@RequestBody Performance performance, HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取绩效考核信息
        Performance pPerformance = performanceService.doGetPerformanceInfo(performance);
        // 判断绩效考核信息是否存在
        if(pPerformance != null){
            // 填充员工信息
            pPerformance.setUser(userService.doGetUserInfo(new User(pPerformance.getUid())));
        }
        // 返回数据
        return ResponseResult.build(pPerformance!=null, null, pPerformance);
    }

    /**
     * 	获取绩效考核信息
     * @param performance
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/adminPer.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult adminPer(@RequestBody Performance performance, HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
        // 获取绩效考核信息
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Performance pPerformance = performanceService.doGetPerformanceInfo(performance);
        List<Work> workList = workService.doGetWorkInfoListByuid(new Work(null, pPerformance.getUid()));
        Work work = workList.get(0);
        // remarks是动物物种
        String remarks = work.getRemarks();
        List<Live> liveList = liveService.doGetLiveInfoListByremarks(new Live(remarks));
        Live live = liveList.get(0);
        // liveData是指标data
        String liveData = live.getData();
        double allData = 0;
        double count = 0;

        String period = pPerformance.getPeriod();
        String[] periodRange = period.split("至");
        Date startDate = dateFormat.parse(periodRange[0]);
        Date endDate = dateFormat.parse(periodRange[1]);

        for (Work w : workList) {
            Date workDate = dateFormat.parse(w.getTime());
            if (!workDate.before(startDate) && !workDate.after(endDate)) {
                String dataStr = w.getData();
                try {
                    double data = Double.parseDouble(dataStr);
                    allData += data;
                    count++;
                } catch (NumberFormatException e) {
                    // Handle the case where dataStr is not a valid double
                    System.err.println("Error parsing dataStr to double: " + dataStr);
                }
            }
        }

        double averageData = 0;
        if (count > 0) {
            averageData = allData / count;
            averageData = Math.round(averageData * 100) / 100.0; // 四舍五入到两位小数
        }
        String averageDataStr = String.format("%.2f", averageData);
        String[] arr = {remarks, liveData, averageDataStr};

        // 返回数据
        return ResponseResult.build(arr!=null, null, arr);
    }

    /**
     * 	获取绩效考核列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/list.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getPerformanceInfoList(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取绩效考核列表
        List<Performance> list = performanceService.doGetPerformanceInfoList();
        // 填充信息
        list.forEach(each->{
            // 填充员工信息
            each.setUser(userService.doGetUserInfo(new User(each.getUid())));
        });
        // 返回数据
        return ResponseResult.build(list!=null, null, list);
    }

    /**
     * 	获取绩效考核列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/listByuid.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getPerformanceInfoListByuid(@RequestBody Performance performance, HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取绩效考核列表
        List<Performance> list = performanceService.doGetPerformanceInfoListByuid(performance);
        // 填充信息
        list.forEach(each->{
            // 填充员工信息
            each.setUser(userService.doGetUserInfo(new User(each.getUid())));
        });
        // 返回数据
        return ResponseResult.build(list!=null, null, list);
    }

    /**
     * 	查询绩效考核列表
     * @param performance
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/query.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult queryPerformanceInfoList(@RequestBody Performance performance, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 查询绩效考核列表
        List<Performance> list = performanceService.doQueryPerformanceInfoList(performance);
        // 填充信息
        list.forEach(each->{
            // 填充员工信息
            each.setUser(userService.doGetUserInfo(new User(each.getUid())));
        });
        // 返回数据
        return ResponseResult.build(list!=null, null, list);
    }

}
