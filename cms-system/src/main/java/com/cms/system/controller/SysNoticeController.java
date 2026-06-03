package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.common.core.TableDataInfo;
import com.cms.system.domain.SysNotice;
import com.cms.system.service.ISysNoticeService;
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
@Mapping("/system/notice")
public class SysNoticeController extends BaseController {

    @Inject
    private ISysNoticeService noticeService;

    @Get
    @Mapping("/list")
    public TableDataInfo list(SysNotice notice) {
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list, list.size());
    }

    @Get
    @Mapping("/{noticeId}")
    public AjaxResult getInfo(Long noticeId) {
        return success(noticeService.selectNoticeById(noticeId));
    }

    @Post
    @Mapping
    public AjaxResult add(SysNotice notice) {
        notice.setCreateBy(getLoginUsername());
        return noticeService.insertNotice(notice) > 0 ? success() : error();
    }

    @Put
    @Mapping
    public AjaxResult edit(SysNotice notice) {
        notice.setUpdateBy(getLoginUsername());
        return noticeService.updateNotice(notice) > 0 ? success() : error();
    }

    @Delete
    @Mapping("/{noticeIds}")
    public AjaxResult remove(Long[] noticeIds) {
        return noticeService.deleteNoticeByIds(noticeIds) > 0 ? success() : error();
    }

    private String getLoginUsername() {
        return Context.current().attr("username");
    }
}
