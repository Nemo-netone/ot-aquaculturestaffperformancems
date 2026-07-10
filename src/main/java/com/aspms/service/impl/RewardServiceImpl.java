package com.aspms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aspms.service.RewardService;
import com.aspms.mapper.RewardMapper;
import com.aspms.entity.Reward;

/**
 * 奖惩信息 业务逻辑实现类
 */
@Service
@Transactional
public class RewardServiceImpl implements RewardService {

    @Resource
    public RewardMapper rewardMapper;

    /**
     * 添加奖惩信息
     * @param reward
     * @return
     */
    @Override
    public boolean doAddRewardInfo(Reward reward) {

        return rewardMapper.doAddRewardInfo(reward)!=0;

    }

    /**
     * 修改奖惩信息
     * @param reward
     * @return
     */
    @Override
    public boolean doModifyRewardInfo(Reward reward) {
        return rewardMapper.doModifyRewardInfo(reward)!=0;
    }

    /**
     * 删除奖惩信息
     * @param reward
     * @return
     */
    @Override
    public boolean doDeleteRewardInfo(Reward reward) {
        return rewardMapper.doDeleteRewardInfo(reward)!=0;
    }

    /**
     * 获取奖惩信息
     * @param reward
     * @return
     */
    @Override
    public Reward doGetRewardInfo(Reward reward) {
        return rewardMapper.doGetRewardInfo(reward);
    }

    /**
     * 获取奖惩列表
     * @return
     */
    @Override
    public List<Reward> doGetRewardInfoList() {
        return rewardMapper.doGetRewardInfoList();
    }

    /**
     * 获取奖惩列表
     * @return
     */
    @Override
    public List<Reward> doGetRewardInfoListByuid(Reward reward) {
        return rewardMapper.doGetRewardInfoListByuid(reward);
    }


    /**
     * 查询奖惩列表
     * @param reward
     * @return
     */
    @Override
    public List<Reward> doQueryRewardInfoList(Reward reward) {
        return rewardMapper.doQueryRewardInfoList(reward);
    }

}
