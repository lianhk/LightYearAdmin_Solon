package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.system.domain.CmsTemplate;
import com.cms.system.service.ICmsTemplateService;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.Context;

import java.util.List;

@Controller
@Mapping("/cms/template")
public class CmsTemplateController extends BaseController {

    @Inject
    private ICmsTemplateService templateService;

    @Get
    @Mapping
    public void page(Context ctx) {
        CmsTemplate search = new CmsTemplate();
        String templateName = ctx.param("templateName");
        if (templateName != null && !templateName.isEmpty()) search.setTemplateName(templateName);

        int pageNum = 1;
        int pageSize = 10;
        try { pageNum = Integer.parseInt(ctx.param("pageNum", "1")); } catch (Exception e) {}
        try { pageSize = Integer.parseInt(ctx.param("pageSize", "10")); } catch (Exception e) {}

        List<CmsTemplate> list = templateService.selectTemplatePage(search, pageNum, pageSize);
        long total = templateService.selectTemplateCount(search);

        ctx.attrSet("list", list);
        ctx.attrSet("total", total);
        ctx.attrSet("pageNum", pageNum);
        ctx.attrSet("pageSize", pageSize);
        ctx.attrSet("totalPages", (int) Math.ceil(total * 1.0 / pageSize));
        ctx.attrSet("templateName", templateName);
        ctx.render("template.html");
    }

    @Get
    @Mapping("/detail")
    public AjaxResult detail(Context ctx) {
        Long id = Long.parseLong(ctx.param("id"));
        CmsTemplate template = templateService.selectById(id);
        if (template == null) {
            return error("模板不存在");
        }
        return success(template);
    }

    @Post
    @Mapping("/add")
    public AjaxResult add(Context ctx, CmsTemplate template) {
        template.setCreateBy(ctx.session("userName"));
        return templateService.insert(template) > 0 ? success() : error();
    }

    @Post
    @Mapping("/edit")
    public AjaxResult edit(Context ctx, CmsTemplate template) {
        template.setUpdateBy(ctx.session("userName"));
        return templateService.update(template) > 0 ? success() : error();
    }

    @Post
    @Mapping("/delete")
    public AjaxResult remove(Context ctx, Long id) {
        return templateService.deleteById(id) > 0 ? success() : error();
    }
}
