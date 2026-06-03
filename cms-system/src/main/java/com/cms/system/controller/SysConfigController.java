package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.system.domain.SysConfig;
import com.cms.system.service.ISysConfigService;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import org.noear.solon.annotation.Get;
import org.noear.solon.core.handle.Context;

import java.util.List;

@Controller
@Mapping("/system/config")
public class SysConfigController extends BaseController {

    @Inject
    private ISysConfigService configService;

    @Get
    @Mapping
    public void page(Context ctx) {
        SysConfig search = new SysConfig();
        String configName = ctx.param("configName");
        if (configName != null && !configName.isEmpty()) search.setConfigName(configName);
        String configKey = ctx.param("configKey");
        if (configKey != null && !configKey.isEmpty()) search.setConfigKey(configKey);
        ctx.attrSet("list", configService.selectConfigList(search));
        ctx.render("config.html");
    }

    @Get
    @Mapping("/detail")
    public AjaxResult detail(Context ctx) {
        return success(configService.selectConfigById(Long.parseLong(ctx.param("id"))));
    }

    @Post
    @Mapping("/add")
    public AjaxResult add(Context ctx, SysConfig config) {
        if (!configService.checkConfigKeyUnique(config.getConfigKey())) {
            return error("参数键名已存在");
        }
        config.setCreateBy(ctx.session("userName"));
        return configService.insertConfig(config) > 0 ? success() : error();
    }

    @Post
    @Mapping("/edit")
    public AjaxResult edit(Context ctx, SysConfig config) {
        config.setUpdateBy(ctx.session("userName"));
        return configService.updateConfig(config) > 0 ? success() : error();
    }

    @Post
    @Mapping("/delete")
    public AjaxResult remove(Context ctx, Long[] configIds) {
        return configService.deleteConfigByIds(configIds) > 0 ? success() : error();
    }
}
