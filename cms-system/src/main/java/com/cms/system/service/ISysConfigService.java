package com.cms.system.service;

import com.cms.system.domain.SysConfig;
import java.util.List;

public interface ISysConfigService {

    List<SysConfig> selectConfigList(SysConfig config);

    SysConfig selectConfigById(Long configId);

    String selectConfigByKey(String configKey);

    int insertConfig(SysConfig config);

    int updateConfig(SysConfig config);

    int deleteConfigByIds(Long[] configIds);

    boolean checkConfigKeyUnique(String configKey);
}
