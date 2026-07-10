package com.aspms.service;

import java.util.List;
import com.aspms.entity.Work;

/**
 * 工作信息 业务逻辑接口
 */
public interface WorkService {

    /**
     * 添加工作信息
     * @param work
     * @return
     */
    boolean doAddWorkInfo(Work work);

    /**
     * 修改工作信息
     * @param work
     * @return
     */
    boolean doModifyWorkInfo(Work work);

    /**
     * 删除工作信息
     * @param work
     * @return
     */
    boolean doDeleteWorkInfo(Work work);

    /**
     * 获取工作信息
     * @param work
     * @return
     */
    Work doGetWorkInfo(Work work);

    /**
     * 获取工作列表
     * @return
     */
    List<Work> doGetWorkInfoList();

    /**
     * 获取工作列表
     * @return
     */
    List<Work> doGetWorkInfoListByuid(Work work);


    /**
     * 查询工作列表
     * @param work
     * @return
     */
    List<Work> doQueryWorkInfoList(Work work);

}
