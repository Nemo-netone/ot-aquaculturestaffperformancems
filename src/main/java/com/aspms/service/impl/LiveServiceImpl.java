package com.aspms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aspms.service.LiveService;
import com.aspms.mapper.LiveMapper;
import com.aspms.entity.Live;

/**
 * 生长指标信息 业务逻辑实现类
 */
@Service
@Transactional
public class LiveServiceImpl implements LiveService {

    @Resource
    public LiveMapper liveMapper;

    /**
     * 添加生长指标信息
     * @param live
     * @return
     */
    @Override
    public boolean doAddLiveInfo(Live live) {

        return liveMapper.doAddLiveInfo(live)!=0;

    }

    /**
     * 修改生长指标信息
     * @param live
     * @return
     */
    @Override
    public boolean doModifyLiveInfo(Live live) {
        return liveMapper.doModifyLiveInfo(live)!=0;
    }

    /**
     * 删除生长指标信息
     * @param live
     * @return
     */
    @Override
    public boolean doDeleteLiveInfo(Live live) {
        return liveMapper.doDeleteLiveInfo(live)!=0;
    }

    /**
     * 获取生长指标信息
     * @param live
     * @return
     */
    @Override
    public Live doGetLiveInfo(Live live) {
        return liveMapper.doGetLiveInfo(live);
    }

    /**
     * 获取生长指标列表
     * @return
     */
    @Override
    public List<Live> doGetLiveInfoList() {
        return liveMapper.doGetLiveInfoList();
    }

    /**
     * 获取生长指标列表
     * @return
     */
    @Override
    public List<Live> doGetLiveInfoListByremarks(Live live) {
        return liveMapper.doGetLiveInfoListByremarks(live);
    }


    /**
     * 查询生长指标列表
     * @param live
     * @return
     */
    @Override
    public List<Live> doQueryLiveInfoList(Live live) {
        return liveMapper.doQueryLiveInfoList(live);
    }

}
