package com.aspms.service;

import java.util.List;
import com.aspms.entity.Performance;

/**
 * 绩效考核信息 业务逻辑接口
 */
public interface PerformanceService {

    /**
     * 添加绩效考核信息
     * @param performance
     * @return
     */
    boolean doAddPerformanceInfo(Performance performance);

    /**
     * 修改绩效考核信息
     * @param performance
     * @return
     */
    boolean doModifyPerformanceInfo(Performance performance);

    /**
     * 删除绩效考核信息
     * @param performance
     * @return
     */
    boolean doDeletePerformanceInfo(Performance performance);

    /**
     * 获取绩效考核信息
     * @param performance
     * @return
     */
    Performance doGetPerformanceInfo(Performance performance);

    /**
     * 获取绩效考核列表
     * @return
     */
    List<Performance> doGetPerformanceInfoList();

    /**
     * 获取绩效考核列表
     * @return
     */
    List<Performance> doGetPerformanceInfoListByuid(Performance performance);


    /**
     * 查询绩效考核列表
     * @param performance
     * @return
     */
    List<Performance> doQueryPerformanceInfoList(Performance performance);

}
