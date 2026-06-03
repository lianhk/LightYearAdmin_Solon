package com.cms.system.service;

import com.cms.system.domain.SysNotice;
import org.beetl.sql.core.query.PageQuery;

import java.util.List;

public interface ISysNoticeService {

    List<SysNotice> selectNoticeList(SysNotice notice);

    List<SysNotice> selectNoticePage(SysNotice notice, PageQuery pageQuery);

    SysNotice selectNoticeById(Long noticeId);

    int insertNotice(SysNotice notice);

    int updateNotice(SysNotice notice);

    int deleteNoticeByIds(Long[] noticeIds);
}
