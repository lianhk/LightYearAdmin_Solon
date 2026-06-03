package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.system.domain.SysNotice;
import com.cms.system.service.ISysNoticeService;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import org.noear.solon.annotation.Get;
import org.noear.solon.core.handle.Context;

import java.util.List;

@Controller
@Mapping("/system/notice")
public class SysNoticeController extends BaseController {

    @Inject
    private ISysNoticeService noticeService;

    @Get
    @Mapping
    public void page(Context ctx) {
        SysNotice search = new SysNotice();
        String noticeTitle = ctx.param("noticeTitle");
        if (noticeTitle != null && !noticeTitle.isEmpty()) search.setNoticeTitle(noticeTitle);
        String noticeType = ctx.param("noticeType");
        if (noticeType != null && !noticeType.isEmpty()) search.setNoticeType(noticeType);
        ctx.attrSet("list", noticeService.selectNoticeList(search));
        ctx.render("notice.html");
    }

    @Get
    @Mapping("/detail")
    public AjaxResult detail(Context ctx) {
        return success(noticeService.selectNoticeById(Long.parseLong(ctx.param("id"))));
    }

    @Post
    @Mapping("/add")
    public AjaxResult add(Context ctx, SysNotice notice) {
        notice.setCreateBy(ctx.session("userName"));
        return noticeService.insertNotice(notice) > 0 ? success() : error();
    }

    @Post
    @Mapping("/edit")
    public AjaxResult edit(Context ctx, SysNotice notice) {
        notice.setUpdateBy(ctx.session("userName"));
        return noticeService.updateNotice(notice) > 0 ? success() : error();
    }

    @Post
    @Mapping("/delete")
    public AjaxResult remove(Context ctx, Long[] noticeIds) {
        return noticeService.deleteNoticeByIds(noticeIds) > 0 ? success() : error();
    }
}
