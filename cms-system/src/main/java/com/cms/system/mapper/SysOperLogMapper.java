package com.cms.system.mapper;

import com.cms.system.domain.SysOperLog;
import org.beetl.sql.core.query.PageQuery;
import org.beetl.sql.mapper.BaseMapper;
import org.beetl.sql.mapper.annotation.SqlResource;

import java.util.List;

@SqlResource("sysOperLog")
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {

    List<SysOperLog> selectOperLogList(SysOperLog operLog);

    List<SysOperLog> selectOperLogList(SysOperLog operLog, PageQuery pageQuery);

    int cleanOperLog();
}
