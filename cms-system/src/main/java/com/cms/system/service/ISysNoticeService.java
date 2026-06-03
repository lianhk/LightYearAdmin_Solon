package com.cms.system.service;

import com.cms.system.domain.SysNotice;
import java.util.List;

public interface ISysNoticeService {

    List<SysNotice> selectNoticeList(SysNotice notice);

    SysNotice selectNoticeById(Long noticeId);

    int insertNotice(SysNotice notice);

    int updateNotice(SysNotice notice);

    int deleteNoticeByIds(Long[] noticeIds);
}
