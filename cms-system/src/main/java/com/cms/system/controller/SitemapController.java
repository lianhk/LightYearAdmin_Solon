package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.system.domain.CmsArticle;
import com.cms.system.domain.CmsCategory;
import com.cms.system.service.ICmsArticleService;
import com.cms.system.service.ICmsCategoryService;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.Context;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@Mapping
public class SitemapController extends BaseController {

    @Inject
    private ICmsArticleService articleService;

    @Inject
    private ICmsCategoryService categoryService;

    private static final String SITE_URL = "http://www.example.com";

    @Get
    @Mapping("/sitemap.xml")
    public void sitemap(Context ctx) {
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");

        // 添加首页
        xml.append("  <url>\n");
        xml.append("    <loc>").append(SITE_URL).append("/</loc>\n");
        xml.append("    <changefreq>daily</changefreq>\n");
        xml.append("    <priority>1.0</priority>\n");
        xml.append("  </url>\n");

        // 添加栏目
        List<CmsCategory> categories = categoryService.selectCategoryList(new CmsCategory());
        if (categories != null) {
            for (CmsCategory category : categories) {
                if (category.getStatus() == null || !"0".equals(category.getStatus())) {
                    xml.append("  <url>\n");
                    xml.append("    <loc>").append(SITE_URL);
                    if (category.getCategoryPath() != null && !category.getCategoryPath().isEmpty()) {
                        xml.append("/").append(category.getCategoryPath());
                    }
                    xml.append("</loc>\n");
                    xml.append("    <changefreq>daily</changefreq>\n");
                    xml.append("    <priority>0.8</priority>\n");
                    xml.append("  </url>\n");
                }
            }
        }

        // 添加已发布文章
        CmsArticle search = new CmsArticle();
        search.setStatus("1");
        List<CmsArticle> articles = articleService.selectArticleList(search);
        if (articles != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (CmsArticle article : articles) {
                // 获取文章所属栏目路径
                String categoryPath = "";
                if (article.getCategoryId() != null) {
                    CmsCategory category = categoryService.selectById(article.getCategoryId());
                    if (category != null && category.getCategoryPath() != null) {
                        categoryPath = category.getCategoryPath();
                    }
                }

                xml.append("  <url>\n");
                if (categoryPath.isEmpty()) {
                    xml.append("    <loc>").append(SITE_URL).append("/article/").append(article.getId()).append("</loc>\n");
                } else {
                    xml.append("    <loc>").append(SITE_URL).append("/").append(categoryPath).append("/").append(article.getId()).append("</loc>\n");
                }
                if (article.getUpdateTime() != null) {
                    xml.append("    <lastmod>").append(sdf.format(article.getUpdateTime())).append("</lastmod>\n");
                } else if (article.getCreateTime() != null) {
                    xml.append("    <lastmod>").append(sdf.format(article.getCreateTime())).append("</lastmod>\n");
                }
                xml.append("    <changefreq>weekly</changefreq>\n");
                xml.append("    <priority>0.6</priority>\n");
                xml.append("  </url>\n");
            }
        }

        xml.append("</urlset>");

        ctx.contentType("application/xml; charset=utf-8");
        ctx.output(xml.toString());
    }
}
