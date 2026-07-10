package com.aspms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aspms.service.NoticeService;
import com.aspms.mapper.NoticeMapper;
import com.aspms.entity.Notice;

/**
 * 公告信息 业务逻辑实现类
 */
@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

    @Resource
    public NoticeMapper noticeMapper;

    /**
     * 添加公告信息
     * @param notice
     * @return
     */
    @Override
    public boolean doAddNoticeInfo(Notice notice) {

        return noticeMapper.doAddNoticeInfo(notice)!=0;

    }

    /**
     * 修改公告信息
     * @param notice
     * @return
     */
    @Override
    public boolean doModifyNoticeInfo(Notice notice) {
        return noticeMapper.doModifyNoticeInfo(notice)!=0;
    }

    /**
     * 删除公告信息
     * @param notice
     * @return
     */
    @Override
    public boolean doDeleteNoticeInfo(Notice notice) {
        return noticeMapper.doDeleteNoticeInfo(notice)!=0;
    }

    /**
     * 获取公告信息
     * @param notice
     * @return
     */
    @Override
    public Notice doGetNoticeInfo(Notice notice) {
        return noticeMapper.doGetNoticeInfo(notice);
    }

    /**
     * 获取公告列表
     * @return
     */
    @Override
    public List<Notice> doGetNoticeInfoList() {
        return noticeMapper.doGetNoticeInfoList();
    }

    /**
     * 查询公告列表
     * @param notice
     * @return
     */
    @Override
    public List<Notice> doQueryNoticeInfoList(Notice notice) {
        return noticeMapper.doQueryNoticeInfoList(notice);
    }

}
