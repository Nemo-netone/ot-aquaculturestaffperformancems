package com.aspms.service;

import java.util.List;
import com.aspms.entity.Position;

/**
 * 养殖岗位信息 业务逻辑接口
 */
public interface PositionService {

    /**
     * 添加养殖岗位信息
     * @param position
     * @return
     */
    boolean doAddPositionInfo(Position position);

    /**
     * 修改养殖岗位信息
     * @param position
     * @return
     */
    boolean doModifyPositionInfo(Position position);

    /**
     * 删除养殖岗位信息
     * @param position
     * @return
     */
    boolean doDeletePositionInfo(Position position);

    /**
     * 获取养殖岗位信息
     * @param position
     * @return
     */
    Position doGetPositionInfo(Position position);

    /**
     * 获取养殖岗位列表
     * @return
     */
    List<Position> doGetPositionInfoList();
    /**
     * 获取养殖岗位列表
     * @return
     */
    List<Position> doGetPositionInfoListBydid(Position position);


    /**
     * 查询养殖岗位列表
     * @param position
     * @return
     */
    List<Position> doQueryPositionInfoList(Position position);

}
