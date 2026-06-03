package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.system.domain.SysOperLog;
import com.cms.system.mapper.SysOperLogMapper;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Post;
import org.noear.solon.core.handle.Context;

import java.util.List;

@Controller
@Mapping("/monitor/operlog")
public class SysOperLogController extends BaseController {

    @Inject
    private SysOperLogMapper operLogMapper;

    @Get
    @Mapping
    public void page(Context ctx) {
        SysOperLog search = new SysOperLog();
        String title = ctx.param("title");
        if (title != null && !title.isEmpty()) search.setTitle(title);
        String operName = ctx.param("operName");
        if (operName != null && !operName.isEmpty()) search.setOperName(operName);
        String status = ctx.param("status");
        if (status != null && !status.isEmpty()) search.setStatus(status);
        ctx.attrSet("list", operLogMapper.selectOperLogList(search));
        ctx.render("operlog.html");
    }

    @Post
    @Mapping("/clean")
    public AjaxResult clean() {
        operLogMapper.cleanOperLog();
        return success();
    }
}
