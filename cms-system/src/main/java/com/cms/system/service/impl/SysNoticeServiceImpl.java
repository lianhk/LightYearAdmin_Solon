package com.cms.system.service.impl;

import com.cms.system.domain.SysNotice;
import com.cms.system.mapper.SysNoticeMapper;
import com.cms.system.service.ISysNoticeService;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.List;

@Component
public class SysNoticeServiceImpl implements ISysNoticeService {

    @Inject
    private SysNoticeMapper noticeMapper;

    @Override
    public List<SysNotice> selectNoticeList(SysNotice notice) {
        return noticeMapper.selectNoticeList(notice);
    }

    @Override
    public SysNotice selectNoticeById(Long noticeId) {
        return noticeMapper.selectById(noticeId);
    }

    @Override
    public int insertNotice(SysNotice notice) {
        return noticeMapper.insert(notice);
    }

    @Override
    public int updateNotice(SysNotice notice) {
        return noticeMapper.update(notice);
    }

    @Override
    public int deleteNoticeByIds(Long[] noticeIds) {
        int count = 0;
        for (Long noticeId : noticeIds) {
            count += noticeMapper.deleteById(noticeId);
        }
        return count;
    }
}
