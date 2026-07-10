package com.aspms.service;

import java.util.List;
import com.aspms.entity.Live;

/**
 * 生长指标信息 业务逻辑接口
 */
public interface LiveService {

    /**
     * 添加生长指标信息
     * @param live
     * @return
     */
    boolean doAddLiveInfo(Live live);

    /**
     * 修改生长指标信息
     * @param live
     * @return
     */
    boolean doModifyLiveInfo(Live live);

    /**
     * 删除生长指标信息
     * @param live
     * @return
     */
    boolean doDeleteLiveInfo(Live live);

    /**
     * 获取生长指标信息
     * @param live
     * @return
     */
    Live doGetLiveInfo(Live live);

    /**
     * 获取生长指标列表
     * @return
     */
    List<Live> doGetLiveInfoList();
    /**
     * 获取生长指标列表
     * @return
     */
    List<Live> doGetLiveInfoListByremarks(Live live);


    /**
     * 查询生长指标列表
     * @param live
     * @return
     */
    List<Live> doQueryLiveInfoList(Live live);

}
