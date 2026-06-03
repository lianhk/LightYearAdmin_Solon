package com.cms.system.service.impl;

import com.cms.system.domain.SysConfig;
import com.cms.system.mapper.SysConfigMapper;
import com.cms.system.service.ISysConfigService;
import org.beetl.sql.core.query.PageQuery;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.List;

@Component
public class SysConfigServiceImpl implements ISysConfigService {

    @Inject
    private SysConfigMapper configMapper;

    @Override
    public List<SysConfig> selectConfigList(SysConfig config) {
        return configMapper.selectConfigList(config);
    }

    @Override
    public List<SysConfig> selectConfigPage(SysConfig config, PageQuery pageQuery) {
        return configMapper.selectConfigList(config, pageQuery);
    }

    @Override
    public SysConfig selectConfigById(Long configId) {
        return configMapper.selectById(configId);
    }

    @Override
    public String selectConfigByKey(String configKey) {
        SysConfig config = configMapper.selectConfigByKey(configKey);
        return config != null ? config.getConfigValue() : null;
    }

    @Override
    public int insertConfig(SysConfig config) {
        return configMapper.insert(config);
    }

    @Override
    public int updateConfig(SysConfig config) {
        return configMapper.update(config);
    }

    @Override
    public int deleteConfigByIds(Long[] configIds) {
        int count = 0;
        for (Long configId : configIds) {
            count += configMapper.deleteById(configId);
        }
        return count;
    }

    @Override
    public boolean checkConfigKeyUnique(String configKey) {
        return configMapper.checkConfigKeyUnique(configKey) == null;
    }
}
