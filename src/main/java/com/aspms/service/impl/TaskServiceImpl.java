package com.aspms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aspms.service.TaskService;
import com.aspms.mapper.TaskMapper;
import com.aspms.entity.Task;

/**
 * 任务信息 业务逻辑实现类
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Resource
    public TaskMapper taskMapper;

    /**
     * 添加任务信息
     * @param task
     * @return
     */
    @Override
    public boolean doAddTaskInfo(Task task) {

        return taskMapper.doAddTaskInfo(task)!=0;

    }

    /**
     * 修改任务信息
     * @param task
     * @return
     */
    @Override
    public boolean doModifyTaskInfo(Task task) {
        return taskMapper.doModifyTaskInfo(task)!=0;
    }

    /**
     * 删除任务信息
     * @param task
     * @return
     */
    @Override
    public boolean doDeleteTaskInfo(Task task) {
        return taskMapper.doDeleteTaskInfo(task)!=0;
    }

    /**
     * 获取任务信息
     * @param task
     * @return
     */
    @Override
    public Task doGetTaskInfo(Task task) {
        return taskMapper.doGetTaskInfo(task);
    }

    /**
     * 获取任务列表
     * @return
     */
    @Override
    public List<Task> doGetTaskInfoList() {
        return taskMapper.doGetTaskInfoList();
    }

    /**
     * 获取任务列表
     * @return
     */
    @Override
    public List<Task> doGetTaskInfoListByuid(Task task) {
        return taskMapper.doGetTaskInfoListByuid(task);
    }


    /**
     * 查询任务列表
     * @param task
     * @return
     */
    @Override
    public List<Task> doQueryTaskInfoList(Task task) {
        return taskMapper.doQueryTaskInfoList(task);
    }

}
