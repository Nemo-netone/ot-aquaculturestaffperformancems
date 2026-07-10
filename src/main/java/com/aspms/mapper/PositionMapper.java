package com.aspms.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.aspms.entity.Position;

/**
 * 养殖岗位信息 Mapper接口
 */
@Mapper
public interface PositionMapper {

    /**
     * 添加养殖岗位信息
     * @param position
     * @return
     */
    int doAddPositionInfo(Position position);

    /**
     * 修改养殖岗位信息
     * @param position
     * @return
     */
    int doModifyPositionInfo(Position position);

    /**
     * 删除养殖岗位信息
     * @param position
     * @return
     */
    int doDeletePositionInfo(Position position);

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
