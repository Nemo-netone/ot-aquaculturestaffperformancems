package com.aspms.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.aspms.entity.Task;

/**
 * 任务信息 Mapper接口
 */
@Mapper
public interface TaskMapper {

    /**
     * 添加任务信息
     * @param task
     * @return
     */
    int doAddTaskInfo(Task task);

    /**
     * 修改任务信息
     * @param task
     * @return
     */
    int doModifyTaskInfo(Task task);

    /**
     * 删除任务信息
     * @param task
     * @return
     */
    int doDeleteTaskInfo(Task task);

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
