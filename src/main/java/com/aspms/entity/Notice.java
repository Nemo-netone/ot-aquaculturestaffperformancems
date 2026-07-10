package com.aspms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 公告信息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {

    // 公告编号
    private Integer nid;
    // 公告标题
    private String title;
    // 公告内容
    private String notice;
    // 发布日期
    private String ctime;
    // 创建人
    private String operator;

    /**
     * 带参构造函数
     */
    public Notice(Integer nid) {
        this.nid = nid;
    }

}
