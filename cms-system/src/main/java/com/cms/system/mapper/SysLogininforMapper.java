package com.cms.system.mapper;

import com.cms.system.domain.SysLogininfor;
import org.beetl.sql.mapper.BaseMapper;
import org.beetl.sql.mapper.annotation.SqlResource;

import java.util.List;

@SqlResource("sysLogininfor")
public interface SysLogininforMapper extends BaseMapper<SysLogininfor> {

    List<SysLogininfor> selectLogininforList(SysLogininfor logininfor);

    int cleanLogininfor();
}
