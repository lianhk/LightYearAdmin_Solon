package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.common.core.TableDataInfo;
import com.cms.system.domain.SysConfig;
import com.cms.system.service.ISysConfigService;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Delete;
import org.noear.solon.annotation.Put;
import org.noear.solon.core.handle.Context;

import java.util.List;

@Controller
@Mapping("/system/config")
public class SysConfigController extends BaseController {

    @Inject
    private ISysConfigService configService;

    @Get
    @Mapping("/list")
    public TableDataInfo list(SysConfig config) {
        List<SysConfig> list = configService.selectConfigList(config);
        return getDataTable(list, list.size());
    }

    @Get
    @Mapping("/configKey/{configKey}")
    public AjaxResult getConfigKey(String configKey) {
        String value = configService.selectConfigByKey(configKey);
        return success(value);
    }

    @Get
    @Mapping("/{configId}")
    public AjaxResult getInfo(Long configId) {
        return success(configService.selectConfigById(configId));
    }

    @Post
    @Mapping
    public AjaxResult add(SysConfig config) {
        if (!configService.checkConfigKeyUnique(config.getConfigKey())) {
            return error("参数键名已存在");
        }
        config.setCreateBy(getLoginUsername());
        return configService.insertConfig(config) > 0 ? success() : error();
    }

    @Put
    @Mapping
    public AjaxResult edit(SysConfig config) {
        config.setUpdateBy(getLoginUsername());
        return configService.updateConfig(config) > 0 ? success() : error();
    }

    @Delete
    @Mapping("/{configIds}")
    public AjaxResult remove(Long[] configIds) {
        return configService.deleteConfigByIds(configIds) > 0 ? success() : error();
    }

    private String getLoginUsername() {
        return Context.current().attr("username");
    }
}
