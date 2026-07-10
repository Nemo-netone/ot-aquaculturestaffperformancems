package com.aspms.service;

import java.util.List;
import com.aspms.entity.Notice;

/**
 * 公告信息 业务逻辑接口
 */
public interface NoticeService {

    /**
     * 添加公告信息
     * @param notice
     * @return
     */
    boolean doAddNoticeInfo(Notice notice);

    /**
     * 修改公告信息
     * @param notice
     * @return
     */
    boolean doModifyNoticeInfo(Notice notice);

    /**
     * 删除公告信息
     * @param notice
     * @return
     */
    boolean doDeleteNoticeInfo(Notice notice);

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
