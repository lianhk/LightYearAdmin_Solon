package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.system.domain.SysLogininfor;
import com.cms.system.mapper.SysLogininforMapper;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Post;
import org.noear.solon.core.handle.Context;

import java.util.List;

@Controller
@Mapping("/monitor/logininfor")
public class SysLogininforController extends BaseController {

    @Inject
    private SysLogininforMapper logininforMapper;

    @Get
    @Mapping
    public void page(Context ctx) {
        SysLogininfor search = new SysLogininfor();
        String userName = ctx.param("userName");
        if (userName != null && !userName.isEmpty()) search.setUserName(userName);
        String status = ctx.param("status");
        if (status != null && !status.isEmpty()) search.setStatus(status);
        String ipaddr = ctx.param("ipaddr");
        if (ipaddr != null && !ipaddr.isEmpty()) search.setIpaddr(ipaddr);
        ctx.attrSet("list", logininforMapper.selectLogininforList(search));
        ctx.render("logininfor.html");
    }

    @Post
    @Mapping("/clean")
    public AjaxResult clean() {
        logininforMapper.cleanLogininfor();
        return success();
    }
}
