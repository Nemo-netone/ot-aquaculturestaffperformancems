package com.aspms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aspms.service.WorkService;
import com.aspms.mapper.WorkMapper;
import com.aspms.entity.Work;

/**
 * 工作信息 业务逻辑实现类
 */
@Service
@Transactional
public class WorkServiceImpl implements WorkService {

    @Resource
    public WorkMapper workMapper;

    /**
     * 添加工作信息
     * @param work
     * @return
     */
    @Override
    public boolean doAddWorkInfo(Work work) {

        return workMapper.doAddWorkInfo(work)!=0;
    }

    /**
     * 修改工作信息
     * @param work
     * @return
     */
    @Override
    public boolean doModifyWorkInfo(Work work) {
        return workMapper.doModifyWorkInfo(work)!=0;
    }

    /**
     * 删除工作信息
     * @param work
     * @return
     */
    @Override
    public boolean doDeleteWorkInfo(Work work) {
        return workMapper.doDeleteWorkInfo(work)!=0;
    }

    /**
     * 获取工作信息
     * @param work
     * @return
     */
    @Override
    public Work doGetWorkInfo(Work work) {
        return workMapper.doGetWorkInfo(work);
    }

    /**
     * 获取工作列表
     * @return
     */
    @Override
    public List<Work> doGetWorkInfoList() {
        return workMapper.doGetWorkInfoList();
    }

    /**
     * 获取工作列表
     * @return
     */
    @Override
    public List<Work> doGetWorkInfoListByuid(Work work) {
        return workMapper.doGetWorkInfoListByuid(work);
    }


    /**
     * 查询工作列表
     * @param work
     * @return
     */
    @Override
    public List<Work> doQueryWorkInfoList(Work work) {
        return workMapper.doQueryWorkInfoList(work);
    }

}
