package com.aspms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面重定向控制器类
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String root() {
        return "index";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("/userLogin")
    public String userLogin(){
        return "userLogin";
    }

    @RequestMapping("/userRegister")
    public String userRegister(){
        return "userRegister";
    }

    @RequestMapping("/modifyPassword")
    public String modifyPassword(){
        return "modifyPassword";
    }

    @RequestMapping("/userAdd")
    public String userAdd(){
        return "userAdd";
    }

    @RequestMapping("/userManage")
    public String userManage(){
        return "userManage";
    }

    @RequestMapping("/userModify")
    public String userModify(){
        return "userModify";
    }

    @RequestMapping("/departmentAdd")
    public String departmentAdd(){
        return "departmentAdd";
    }

    @RequestMapping("/departmentManage")
    public String departmentManage(){
        return "departmentManage";
    }

    @RequestMapping("/departmentModify")
    public String departmentModify(){
        return "departmentModify";
    }

    @RequestMapping("/positionAdd")
    public String positionAdd(){
        return "positionAdd";
    }

    @RequestMapping("/positionManage")
    public String positionManage(){
        return "positionManage";
    }

    @RequestMapping("/positionModify")
    public String positionModify(){
        return "positionModify";
    }

    @RequestMapping("/noticeAdd")
    public String noticeAdd(){
        return "noticeAdd";
    }

    @RequestMapping("/noticeManage")
    public String noticeManage(){
        return "noticeManage";
    }

    @RequestMapping("/noticeModify")
    public String noticeModify(){
        return "noticeModify";
    }

    @RequestMapping("/taskAdd")
    public String taskAdd(){
        return "taskAdd";
    }

    @RequestMapping("/taskManage")
    public String taskManage(){
        return "taskManage";
    }

    @RequestMapping("/taskModify")
    public String taskModify(){
        return "taskModify";
    }

    @RequestMapping("/liveAdd")
    public String liveAdd(){
        return "liveAdd";
    }

    @RequestMapping("/liveManage")
    public String liveManage(){
        return "liveManage";
    }

    @RequestMapping("/liveModify")
    public String liveModify(){
        return "liveModify";
    }

    @RequestMapping("/workAdd")
    public String workAdd(){
        return "workAdd";
    }

    @RequestMapping("/workManage")
    public String workManage(){
        return "workManage";
    }

    @RequestMapping("/workModify")
    public String workModify(){
        return "workModify";
    }

    @RequestMapping("/performanceAdd")
    public String performanceAdd(){
        return "performanceAdd";
    }

    @RequestMapping("/performanceManage")
    public String performanceManage(){
        return "performanceManage";
    }

    @RequestMapping("/performanceModify")
    public String performanceModify(){
        return "performanceModify";
    }

    @RequestMapping("/rewardAdd")
    public String rewardAdd(){
        return "rewardAdd";
    }

    @RequestMapping("/rewardManage")
    public String rewardManage(){
        return "rewardManage";
    }

    @RequestMapping("/rewardModify")
    public String rewardModify(){
        return "rewardModify";
    }

    @RequestMapping("/userUser")
    public String userUser(){
        return "userUser";
    }
    @RequestMapping("/userWork")
    public String userWork(){
        return "userWork";
    }
    @RequestMapping("/userAddWork")
    public String userAddWork(){
        return "userAddWork";
    }
    @RequestMapping("/userTask")
    public String userTask(){
        return "userTask";
    }
    @RequestMapping("/userLive")
    public String userLive(){
        return "userLive";
    }
    @RequestMapping("/userPerformance")
    public String userPerformance(){
        return "userPerformance";
    }
    @RequestMapping("/userReward")
    public String userReward(){
        return "userReward";
    }

    @RequestMapping("/dataStatis")
    public String dataStatis(){
        return "dataStatis";
    }

    @RequestMapping("/performanceAppeal")
    public String performanceAppeal(){
        return "performanceAppeal";
    }

    @RequestMapping("/appeal")
    public String appeal(){
        return "appeal";
    }
    @RequestMapping("/adminPerformance")
    public String adminPerformance(){
        return "adminPerformance";
    }

    @RequestMapping("/userTaskMod")
    public String userTaskMod(){
        return "userTaskMod";
    }

}
