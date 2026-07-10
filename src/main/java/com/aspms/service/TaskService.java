package com.aspms.service;

import java.util.List;
import com.aspms.entity.Task;

/**
 * 任务信息 业务逻辑接口
 */
public interface TaskService {

    /**
     * 添加任务信息
     * @param task
     * @return
     */
    boolean doAddTaskInfo(Task task);

    /**
     * 修改任务信息
     * @param task
     * @return
     */
    boolean doModifyTaskInfo(Task task);

    /**
     * 删除任务信息
     * @param task
     * @return
     */
    boolean doDeleteTaskInfo(Task task);

    /**
     * 获取任务信息
     * @param task
     * @return
     */
    Task doGetTaskInfo(Task task);

    /**
     * 获取任务列表
     * @return
     */
    List<Task> doGetTaskInfoList();

    /**
     * 获取任务列表
     * @return
     */
    List<Task> doGetTaskInfoListByuid(Task task);


    /**
     * 查询任务列表
     * @param task
     * @return
     */
    List<Task> doQueryTaskInfoList(Task task);

}
