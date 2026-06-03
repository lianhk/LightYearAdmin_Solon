package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.system.domain.SysOperLog;
import com.cms.system.mapper.SysOperLogMapper;
import org.beetl.sql.core.query.PageQuery;
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

        int pageNum = 1;
        int pageSize = 10;
        try { pageNum = Integer.parseInt(ctx.param("pageNum", "1")); } catch(Exception e){}
        try { pageSize = Integer.parseInt(ctx.param("pageSize", "10")); } catch(Exception e){}

        PageQuery query = new PageQuery(pageNum, pageSize);
        List<SysOperLog> list = operLogMapper.selectOperLogList(search, query);

        ctx.attrSet("list", list);
        ctx.attrSet("total", query.getTotalCount());
        ctx.attrSet("pageNum", pageNum);
        ctx.attrSet("pageSize", pageSize);
        ctx.attrSet("totalPages", (int)Math.ceil(query.getTotalCount() * 1.0 / pageSize));
        ctx.attrSet("title", title);
        ctx.attrSet("operName", operName);
        ctx.attrSet("status", status);
        ctx.render("operlog.html");
    }

    @Post
    @Mapping("/clean")
    public AjaxResult clean() {
        operLogMapper.cleanOperLog();
        return success();
    }
}
