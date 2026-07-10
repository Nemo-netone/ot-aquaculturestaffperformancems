package com.aspms.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.aspms.entity.Live;

/**
 * 生长指标信息 Mapper接口
 */
@Mapper
public interface LiveMapper {

    /**
     * 添加生长指标信息
     * @param live
     * @return
     */
    int doAddLiveInfo(Live live);

    /**
     * 修改生长指标信息
     * @param live
     * @return
     */
    int doModifyLiveInfo(Live live);

    /**
     * 删除生长指标信息
     * @param live
     * @return
     */
    int doDeleteLiveInfo(Live live);

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
