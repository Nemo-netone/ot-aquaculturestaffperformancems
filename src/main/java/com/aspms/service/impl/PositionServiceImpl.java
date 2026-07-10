package com.aspms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aspms.service.PositionService;
import com.aspms.mapper.PositionMapper;
import com.aspms.entity.Position;

/**
 * 养殖岗位信息 业务逻辑实现类
 */
@Service
@Transactional
public class PositionServiceImpl implements PositionService {

    @Resource
    public PositionMapper positionMapper;

    /**
     * 添加养殖岗位信息
     * @param position
     * @return
     */
    @Override
    public boolean doAddPositionInfo(Position position) {

        return positionMapper.doAddPositionInfo(position)!=0;

    }

    /**
     * 修改养殖岗位信息
     * @param position
     * @return
     */
    @Override
    public boolean doModifyPositionInfo(Position position) {
        return positionMapper.doModifyPositionInfo(position)!=0;
    }

    /**
     * 删除养殖岗位信息
     * @param position
     * @return
     */
    @Override
    public boolean doDeletePositionInfo(Position position) {
        return positionMapper.doDeletePositionInfo(position)!=0;
    }

    /**
     * 获取养殖岗位信息
     * @param position
     * @return
     */
    @Override
    public Position doGetPositionInfo(Position position) {
        return positionMapper.doGetPositionInfo(position);
    }

    /**
     * 获取养殖岗位列表
     * @return
     */
    @Override
    public List<Position> doGetPositionInfoList() {
        return positionMapper.doGetPositionInfoList();
    }

    /**
     * 获取养殖岗位列表
     * @return
     */
    @Override
    public List<Position> doGetPositionInfoListBydid(Position position) {
        return positionMapper.doGetPositionInfoListBydid(position);
    }


    /**
     * 查询养殖岗位列表
     * @param position
     * @return
     */
    @Override
    public List<Position> doQueryPositionInfoList(Position position) {
        return positionMapper.doQueryPositionInfoList(position);
    }

}
