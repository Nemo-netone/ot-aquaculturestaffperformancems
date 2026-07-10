package com.aspms.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.aspms.entity.Work;

/**
 * 工作信息 Mapper接口
 */
@Mapper
public interface WorkMapper {

    /**
     * 添加工作信息
     * @param work
     * @return
     */
    int doAddWorkInfo(Work work);

    /**
     * 修改工作信息
     * @param work
     * @return
     */
    int doModifyWorkInfo(Work work);

    /**
     * 删除工作信息
     * @param work
     * @return
     */
    int doDeleteWorkInfo(Work work);

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
