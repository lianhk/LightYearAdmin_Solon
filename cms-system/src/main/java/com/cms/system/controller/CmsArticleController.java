package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.system.domain.CmsArticle;
import com.cms.system.domain.CmsCategory;
import com.cms.system.service.ICmsArticleService;
import com.cms.system.service.ICmsCategoryService;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Mapping("/cms/article")
public class CmsArticleController extends BaseController {

    @Inject
    private ICmsArticleService articleService;

    @Inject
    private ICmsCategoryService categoryService;

    @Get
    @Mapping
    public void page(Context ctx) {
        CmsArticle search = new CmsArticle();
        String categoryId = ctx.param("categoryId");
        String title = ctx.param("title");
        String status = ctx.param("status");

        if (categoryId != null && !categoryId.isEmpty()) search.setCategoryId(Long.parseLong(categoryId));
        if (title != null && !title.isEmpty()) search.setTitle(title);
        if (status != null && !status.isEmpty()) search.setStatus(status);

        int pageNum = 1;
        int pageSize = 10;
        try { pageNum = Integer.parseInt(ctx.param("pageNum", "1")); } catch (Exception e) {}
        try { pageSize = Integer.parseInt(ctx.param("pageSize", "10")); } catch (Exception e) {}

        List<CmsArticle> list = articleService.selectArticlePage(search, pageNum, pageSize);
        long total = articleService.selectArticleCount(search);

        ctx.attrSet("list", list);
        ctx.attrSet("total", total);
        ctx.attrSet("pageNum", pageNum);
        ctx.attrSet("pageSize", pageSize);
        ctx.attrSet("totalPages", (int) Math.ceil(total * 1.0 / pageSize));
        ctx.attrSet("categoryId", categoryId);
        ctx.attrSet("title", title);
        ctx.attrSet("status", status);
        ctx.attrSet("categoryTree", categoryService.selectCategoryTree(new CmsCategory()));
        ctx.render("article.html");
    }

    @Get
    @Mapping("/detail")
    public AjaxResult detail(Context ctx) {
        Long id = Long.parseLong(ctx.param("id"));
        CmsArticle article = articleService.selectById(id);
        if (article != null && article.getSeoKeywords() != null) {
            Map<String, Object> data = new HashMap<>();
            data.put("article", article);
            data.put("seoKeywords", article.getSeoKeywords().split(","));
            return success(data);
        }
        return success(article);
    }

    @Post
    @Mapping("/add")
    public AjaxResult add(Context ctx, CmsArticle article) {
        article.setCreateBy(ctx.session("userName"));
        parseSeoFields(ctx, article);
        return articleService.insert(article) > 0 ? success() : error();
    }

    @Post
    @Mapping("/edit")
    public AjaxResult edit(Context ctx, CmsArticle article) {
        article.setUpdateBy(ctx.session("userName"));
        parseSeoFields(ctx, article);
        return articleService.update(article) > 0 ? success() : error();
    }

    @Post
    @Mapping("/delete")
    public AjaxResult remove(Context ctx, Long id) {
        return articleService.deleteById(id) > 0 ? success() : error();
    }

    @Post
    @Mapping("/publish")
    public AjaxResult publish(Context ctx, Long id) {
        CmsArticle article = articleService.selectById(id);
        if (article == null) {
            return error("文章不存在");
        }
        article.setStatus("1");
        article.setUpdateBy(ctx.session("userName"));
        return articleService.update(article) > 0 ? success() : error();
    }

    @Post
    @Mapping("/audit")
    public AjaxResult audit(Context ctx, Long id) {
        CmsArticle article = articleService.selectById(id);
        if (article == null) {
            return error("文章不存在");
        }
        article.setStatus("2");
        article.setUpdateBy(ctx.session("userName"));
        return articleService.update(article) > 0 ? success() : error();
    }

    /**
     * 解析SEO字段，将逗号分隔的关键字转为数组
     */
    private void parseSeoFields(Context ctx, CmsArticle article) {
        String seoKeywords = ctx.param("seoKeywords");
        if (seoKeywords != null && !seoKeywords.isEmpty()) {
            article.setSeoKeywords(seoKeywords);
        }
        String seoDescription = ctx.param("seoDescription");
        if (seoDescription != null && !seoDescription.isEmpty()) {
            article.setSeoDescription(seoDescription);
        }
    }
}
