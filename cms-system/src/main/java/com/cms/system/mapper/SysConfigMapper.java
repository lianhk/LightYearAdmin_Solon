package com.cms.system.mapper;

import com.cms.system.domain.SysConfig;
import org.beetl.sql.mapper.BaseMapper;
import org.beetl.sql.mapper.annotation.SqlResource;

import java.util.List;

@SqlResource("sysConfig")
public interface SysConfigMapper extends BaseMapper<SysConfig> {

    List<SysConfig> selectConfigList(SysConfig config);

    SysConfig checkConfigKeyUnique(String configKey);

    SysConfig selectConfigByKey(String configKey);
}
