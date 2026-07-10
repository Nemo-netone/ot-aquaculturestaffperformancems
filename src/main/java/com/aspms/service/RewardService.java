package com.aspms.service;

import java.util.List;
import com.aspms.entity.Reward;

/**
 * 奖惩信息 业务逻辑接口
 */
public interface RewardService {

    /**
     * 添加奖惩信息
     * @param reward
     * @return
     */
    boolean doAddRewardInfo(Reward reward);

    /**
     * 修改奖惩信息
     * @param reward
     * @return
     */
    boolean doModifyRewardInfo(Reward reward);

    /**
     * 删除奖惩信息
     * @param reward
     * @return
     */
    boolean doDeleteRewardInfo(Reward reward);

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
