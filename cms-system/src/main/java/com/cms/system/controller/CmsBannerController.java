package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.system.domain.CmsBanner;
import com.cms.system.mapper.CmsBannerMapper;
import org.beetl.sql.core.query.PageQuery;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.Context;
import java.util.List;

@Controller
@Mapping("/cms/banner")
public class CmsBannerController extends BaseController {

    @Inject
    private CmsBannerMapper bannerMapper;

    @Get
    @Mapping
    public void page(Context ctx) {
        CmsBanner search = new CmsBanner();
        String title = ctx.param("title");
        if (title != null && !title.isEmpty()) search.setTitle(title);

        int pageNum = 1, pageSize = 10;
        try { pageNum = Integer.parseInt(ctx.param("pageNum","1")); } catch(Exception e){}
        try { pageSize = Integer.parseInt(ctx.param("pageSize","10")); } catch(Exception e){}

        PageQuery query = new PageQuery(pageNum, pageSize);
        List<CmsBanner> list = bannerMapper.selectBannerList(search, query);

        ctx.attrSet("list", list); ctx.attrSet("total", query.getTotalCount());
        ctx.attrSet("pageNum", pageNum); ctx.attrSet("pageSize", pageSize);
        ctx.attrSet("totalPages", (int)Math.ceil(query.getTotalCount()*1.0/pageSize));
        ctx.attrSet("title", title); ctx.render("banner.html");
    }

    @Get
    @Mapping("/detail")
    public AjaxResult detail(Context ctx) {
        return success(bannerMapper.selectById(Long.parseLong(ctx.param("id"))));
    }

    @Post
    @Mapping("/add")
    public AjaxResult add(Context ctx, CmsBanner banner) {
        banner.setCreateBy(ctx.session("userName"));
        return bannerMapper.insert(banner) > 0 ? success() : error();
    }

    @Post
    @Mapping("/edit")
    public AjaxResult edit(Context ctx, CmsBanner banner) {
        banner.setUpdateBy(ctx.session("userName"));
        return bannerMapper.update(banner) > 0 ? success() : error();
    }

    @Post
    @Mapping("/delete")
    public AjaxResult remove(Context ctx, Long[] ids) {
        for (Long id : ids) bannerMapper.deleteById(id);
        return success();
    }
}
