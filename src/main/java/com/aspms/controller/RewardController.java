package com.aspms.controller;

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
import com.aspms.entity.Reward;
import com.aspms.service.RewardService;
import com.aspms.entity.User;
import com.aspms.service.UserService;

/**
 * 奖惩信息 前后端交互类
 */
@Controller
@RequestMapping(value = "/api/reward")
public class RewardController {

    @Autowired
    private RewardService rewardService;
    @Autowired
    private UserService userService;

    /**
     * 	添加奖惩信息
     * @param reward
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/add.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult addRewardInfo(@RequestBody Reward reward, HttpServletRequest request) throws IOException {
        reward.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        // 添加奖惩信息
        if(rewardService.doAddRewardInfo(reward)) {
            // 添加奖惩信息成功
            return ResponseResult.build(true, "添加奖惩信息成功！");
        }else {
            // 添加奖惩信息失败
            return ResponseResult.build(false, "添加奖惩信息失败，可能是当前奖惩信息已存在！");
        }
    }

    /**
     * 	修改奖惩信息
     * @param reward
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/modify.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult modifyRewardInfo(@RequestBody Reward reward, HttpServletRequest request) throws IOException {
        // 修改奖惩信息
        if(rewardService.doModifyRewardInfo(reward)) {
            // 修改奖惩信息成功
            return ResponseResult.build(true, "修改奖惩信息成功！");
        }else {
            // 修改奖惩信息失败
            return ResponseResult.build(false, "修改奖惩信息失败，可能是当前奖惩信息不存在！");
        }
    }

    /**
     * 	删除奖惩信息
     * @param reward
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/delete.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult deleteRewardInfo(@RequestBody Reward reward, HttpServletRequest request) throws IOException {
        // 删除奖惩信息
        if(rewardService.doDeleteRewardInfo(reward)) {
            // 删除奖惩信息成功
            return ResponseResult.build(true, "删除奖惩信息成功！");
        }else {
            // 删除奖惩信息失败
            return ResponseResult.build(false, "删除奖惩信息失败，可能是当前奖惩信息不存在！");
        }
    }

    /**
     * 	获取奖惩信息
     * @param reward
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/get.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getRewardInfo(@RequestBody Reward reward, HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取奖惩信息
        Reward pReward = rewardService.doGetRewardInfo(reward);
        // 判断奖惩信息是否存在
        if(pReward != null){
            // 填充员工信息
            pReward.setUser(userService.doGetUserInfo(new User(pReward.getUid())));
        }
        // 返回数据
        return ResponseResult.build(pReward!=null, null, pReward);
    }

    /**
     * 	获取奖惩列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/list.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getRewardInfoList(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取奖惩列表
        List<Reward> list = rewardService.doGetRewardInfoList();
        // 填充信息
        list.forEach(each->{
            // 填充员工信息
            each.setUser(userService.doGetUserInfo(new User(each.getUid())));
        });
        // 返回数据
        return ResponseResult.build(list!=null, null, list);
    }

    /**
     * 	获取奖惩列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/listByuid.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult getRewardInfoListByuid(@RequestBody Reward reward ,HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取奖惩列表
        List<Reward> list = rewardService.doGetRewardInfoListByuid(reward);
        // 填充信息
        list.forEach(each->{
            // 填充员工信息
            each.setUser(userService.doGetUserInfo(new User(each.getUid())));
        });
        // 返回数据
        return ResponseResult.build(list!=null, null, list);
    }

    /**
     * 	查询奖惩列表
     * @param reward
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/query.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseResult queryRewardInfoList(@RequestBody Reward reward, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 查询奖惩列表
        List<Reward> list = rewardService.doQueryRewardInfoList(reward);
        // 填充信息
        list.forEach(each->{
            // 填充员工信息
            each.setUser(userService.doGetUserInfo(new User(each.getUid())));
        });
        // 返回数据
        return ResponseResult.build(list!=null, null, list);
    }

}
