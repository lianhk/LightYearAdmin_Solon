package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.system.domain.SysConfig;
import com.cms.system.mapper.SysConfigMapper;
import com.cms.system.service.ISysConfigService;
import org.beetl.sql.core.query.PageQuery;
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

    @Inject
    private SysConfigMapper configMapper;

    @Get
    @Mapping
    public void page(Context ctx) {
        SysConfig search = new SysConfig();
        String configName = ctx.param("configName");
        if (configName != null && !configName.isEmpty()) search.setConfigName(configName);
        String configKey = ctx.param("configKey");
        if (configKey != null && !configKey.isEmpty()) search.setConfigKey(configKey);

        int pageNum = 1;
        int pageSize = 10;
        try { pageNum = Integer.parseInt(ctx.param("pageNum", "1")); } catch(Exception e){}
        try { pageSize = Integer.parseInt(ctx.param("pageSize", "10")); } catch(Exception e){}

        PageQuery query = new PageQuery(pageNum, pageSize);
        List<SysConfig> list = configService.selectConfigPage(search, query);

        ctx.attrSet("list", list);
        ctx.attrSet("total", query.getTotalCount());
        ctx.attrSet("pageNum", pageNum);
        ctx.attrSet("pageSize", pageSize);
        ctx.attrSet("totalPages", (int)Math.ceil(query.getTotalCount() * 1.0 / pageSize));
        ctx.attrSet("configName", configName);
        ctx.attrSet("configKey", configKey);
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

    /** 批量保存配置（网站设置用） */
    @Post
    @Mapping("/batchSave")
    public AjaxResult batchSave(Context ctx) throws Exception {
        String body = ctx.body();
        cn.hutool.json.JSONArray arr = cn.hutool.json.JSONUtil.parseArray(body);
        for (int i = 0; i < arr.size(); i++) {
            cn.hutool.json.JSONObject obj = arr.getJSONObject(i);
            String key = obj.getStr("configKey");
            String val = obj.getStr("configValue");
            com.cms.system.domain.SysConfig old = configMapper.selectConfigByKey(key);
            if (old != null) {
                old.setConfigValue(val);
                configService.updateConfig(old);
            } else {
                com.cms.system.domain.SysConfig cfg = new com.cms.system.domain.SysConfig();
                cfg.setConfigKey(key); cfg.setConfigValue(val);
                cfg.setConfigName(key); cfg.setConfigType("Y");
                cfg.setCreateBy(ctx.session("userName"));
                configService.insertConfig(cfg);
            }
        }
        return success();
    }

    /** 网站设置页面 */
    @Get
    @Mapping("/site")
    public void site(Context ctx) {
        ctx.render("site.html");
    }
}
