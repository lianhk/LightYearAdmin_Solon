package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.system.domain.CmsCategory;
import com.cms.system.service.ICmsCategoryService;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.Context;
import java.util.*;

@Controller
@Mapping("/cms/category")
public class CmsCategoryController extends BaseController {

    @Inject
    private ICmsCategoryService categoryService;

    @Get
    @Mapping
    public void page(Context ctx) {
        CmsCategory search = new CmsCategory();
        String categoryName = ctx.param("categoryName");
        if (categoryName != null && !categoryName.isEmpty()) search.setCategoryName(categoryName);
        ctx.attrSet("list", categoryService.selectCategoryTree(search));
        ctx.attrSet("categoryName", categoryName);
        ctx.render("category.html");
    }

    @Get
    @Mapping("/detail")
    public AjaxResult detail(Context ctx) {
        return success(categoryService.selectById(Long.parseLong(ctx.param("id"))));
    }

    @Get
    @Mapping("/tree")
    public AjaxResult tree(Context ctx) {
        return success(categoryService.selectCategoryTree(new CmsCategory()));
    }

    @Post
    @Mapping("/add")
    public AjaxResult add(Context ctx, CmsCategory category) {
        category.setCreateBy(ctx.session("userName"));
        return categoryService.insert(category) > 0 ? success() : error();
    }

    @Post
    @Mapping("/edit")
    public AjaxResult edit(Context ctx, CmsCategory category) {
        category.setUpdateBy(ctx.session("userName"));
        return categoryService.update(category) > 0 ? success() : error();
    }

    @Post
    @Mapping("/delete")
    public AjaxResult remove(Context ctx, Long id) {
        return categoryService.deleteById(id) > 0 ? success() : error();
    }
}
