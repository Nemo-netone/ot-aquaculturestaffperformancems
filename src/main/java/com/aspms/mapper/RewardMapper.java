package com.aspms.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.aspms.entity.Reward;

/**
 * 奖惩信息 Mapper接口
 */
@Mapper
public interface RewardMapper {

    /**
     * 添加奖惩信息
     * @param reward
     * @return
     */
    int doAddRewardInfo(Reward reward);

    /**
     * 修改奖惩信息
     * @param reward
     * @return
     */
    int doModifyRewardInfo(Reward reward);

    /**
     * 删除奖惩信息
     * @param reward
     * @return
     */
    int doDeleteRewardInfo(Reward reward);

    /**
     * 获取奖惩信息
     * @param reward
     * @return
     */
    Reward doGetRewardInfo(Reward reward);

    /**
     * 获取奖惩列表
     * @return
     */
    List<Reward> doGetRewardInfoList();

    /**
     * 获取奖惩列表
     * @return
     */
    List<Reward> doGetRewardInfoListByuid(Reward reward);


    /**
     * 查询奖惩列表
     * @param reward
     * @return
     */
    List<Reward> doQueryRewardInfoList(Reward reward);

}
