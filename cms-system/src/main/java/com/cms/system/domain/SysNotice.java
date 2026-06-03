package com.cms.system.domain;

import com.cms.common.core.BaseEntity;

/**
 * 通知公告
 */
public class SysNotice extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long noticeId;
    private String noticeTitle;
    private String noticeType;
    private String noticeContent;
    private String status;

    public Long getNoticeId() { return noticeId; }
    public void setNoticeId(Long noticeId) { this.noticeId = noticeId; }

    public String getNoticeTitle() { return noticeTitle; }
    public void setNoticeTitle(String noticeTitle) { this.noticeTitle = noticeTitle; }

    public String getNoticeType() { return noticeType; }
    public void setNoticeType(String noticeType) { this.noticeType = noticeType; }

    public String getNoticeContent() { return noticeContent; }
    public void setNoticeContent(String noticeContent) { this.noticeContent = noticeContent; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
