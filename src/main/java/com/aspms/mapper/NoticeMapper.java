package com.aspms.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.aspms.entity.Notice;

/**
 * 公告信息 Mapper接口
 */
@Mapper
public interface NoticeMapper {

    /**
     * 添加公告信息
     * @param notice
     * @return
     */
    int doAddNoticeInfo(Notice notice);

    /**
     * 修改公告信息
     * @param notice
     * @return
     */
    int doModifyNoticeInfo(Notice notice);

    /**
     * 删除公告信息
     * @param notice
     * @return
     */
    int doDeleteNoticeInfo(Notice notice);

    /**
     * 获取公告信息
     * @param notice
     * @return
     */
    Notice doGetNoticeInfo(Notice notice);

    /**
     * 获取公告列表
     * @return
     */
    List<Notice> doGetNoticeInfoList();

    /**
     * 查询公告列表
     * @param notice
     * @return
     */
    List<Notice> doQueryNoticeInfoList(Notice notice);

}
