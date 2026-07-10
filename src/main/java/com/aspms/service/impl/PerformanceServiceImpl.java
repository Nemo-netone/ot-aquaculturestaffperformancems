package com.aspms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aspms.service.PerformanceService;
import com.aspms.mapper.PerformanceMapper;
import com.aspms.entity.Performance;

/**
 * 绩效考核信息 业务逻辑实现类
 */
@Service
@Transactional
public class PerformanceServiceImpl implements PerformanceService {

    @Resource
    public PerformanceMapper performanceMapper;

    /**
     * 添加绩效考核信息
     * @param performance
     * @return
     */
    @Override
    public boolean doAddPerformanceInfo(Performance performance) {

        return performanceMapper.doAddPerformanceInfo(performance)!=0;

    }

    /**
     * 修改绩效考核信息
     * @param performance
     * @return
     */
    @Override
    public boolean doModifyPerformanceInfo(Performance performance) {
        return performanceMapper.doModifyPerformanceInfo(performance)!=0;
    }

    /**
     * 删除绩效考核信息
     * @param performance
     * @return
     */
    @Override
    public boolean doDeletePerformanceInfo(Performance performance) {
        return performanceMapper.doDeletePerformanceInfo(performance)!=0;
    }

    /**
     * 获取绩效考核信息
     * @param performance
     * @return
     */
    @Override
    public Performance doGetPerformanceInfo(Performance performance) {
        return performanceMapper.doGetPerformanceInfo(performance);
    }

    /**
     * 获取绩效考核列表
     * @return
     */
    @Override
    public List<Performance> doGetPerformanceInfoList() {
        return performanceMapper.doGetPerformanceInfoList();
    }

    /**
     * 获取绩效考核列表
     * @return
     */
    @Override
    public List<Performance> doGetPerformanceInfoListByuid(Performance performance) {
        return performanceMapper.doGetPerformanceInfoListByuid(performance);
    }


    /**
     * 查询绩效考核列表
     * @param performance
     * @return
     */
    @Override
    public List<Performance> doQueryPerformanceInfoList(Performance performance) {
        return performanceMapper.doQueryPerformanceInfoList(performance);
    }

}
