package com.aspms.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.aspms.entity.Performance;

/**
 * 绩效考核信息 Mapper接口
 */
@Mapper
public interface PerformanceMapper {

    /**
     * 添加绩效考核信息
     * @param performance
     * @return
     */
    int doAddPerformanceInfo(Performance performance);

    /**
     * 修改绩效考核信息
     * @param performance
     * @return
     */
    int doModifyPerformanceInfo(Performance performance);

    /**
     * 删除绩效考核信息
     * @param performance
     * @return
     */
    int doDeletePerformanceInfo(Performance performance);

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
