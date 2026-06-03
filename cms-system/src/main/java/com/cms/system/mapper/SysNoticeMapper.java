package com.cms.system.mapper;

import com.cms.system.domain.SysNotice;
import org.beetl.sql.mapper.BaseMapper;
import org.beetl.sql.mapper.annotation.SqlResource;

import java.util.List;

@SqlResource("sysNotice")
public interface SysNoticeMapper extends BaseMapper<SysNotice> {

    List<SysNotice> selectNoticeList(SysNotice notice);
}
