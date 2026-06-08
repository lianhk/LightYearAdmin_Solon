package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.system.domain.CmsArticle;
import com.cms.system.domain.CmsCategory;
import com.cms.system.domain.CmsTemplate;
import com.cms.system.service.ICmsArticleService;
import com.cms.system.service.ICmsCategoryService;
import com.cms.system.service.ICmsTemplateService;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@Mapping("/cms/generate")
public class CmsGenerateController extends BaseController {

    @Inject
    private ICmsArticleService articleService;

    @Inject
    private ICmsCategoryService categoryService;

    @Inject
    private ICmsTemplateService templateService;

    private static final String STATIC_HTML_DIR = System.getProperty("user.dir") + "/static/html/";

    private volatile boolean generating = false;
    private final AtomicInteger generatedCount = new AtomicInteger(0);
    private final AtomicInteger totalCount = new AtomicInteger(0);

    @Get
    @Mapping
    public void page(Context ctx) {
        CmsArticle search = new CmsArticle();
        String categoryId = ctx.param("categoryId");
        if (categoryId != null && !categoryId.isEmpty()) search.setCategoryId(Long.parseLong(categoryId));
        search.setStatus("1");

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
        ctx.attrSet("categoryTree", categoryService.selectCategoryTree(new CmsCategory()));
        ctx.attrSet("generating", generating);
        ctx.render("generate.html");
    }

    @Get
    @Mapping("/status")
    public AjaxResult status(Context ctx) {
        Map<String, Object> data = new HashMap<>();
        data.put("generating", generating);
        data.put("generatedCount", generatedCount.get());
        data.put("totalCount", totalCount.get());
        return success(data);
    }

    @Post
    @Mapping("/all")
    public AjaxResult generateAll(Context ctx) {
        if (generating) {
            return error("正在生成中，请稍后再试");
        }
        try {
            generating = true;
            generatedCount.set(0);

            CmsArticle search = new CmsArticle();
            search.setStatus("1");
            List<CmsArticle> articles = articleService.selectArticleList(search);
            totalCount.set(articles.size());

            for (CmsArticle article : articles) {
                try {
                    generateStaticHtml(article);
                    generatedCount.incrementAndGet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return success("全量生成完成，成功 " + generatedCount.get() + "/" + totalCount.get() + " 篇");
        } finally {
            generating = false;
        }
    }

    @Post
    @Mapping("/article/{id}")
    public AjaxResult generateArticle(Context ctx, Long id) {
        CmsArticle article = articleService.selectById(id);
        if (article == null) {
            return error("文章不存在");
        }
        try {
            generateStaticHtml(article);
            return success("生成成功");
        } catch (Exception e) {
            e.printStackTrace();
            return error("生成失败: " + e.getMessage());
        }
    }

    @Post
    @Mapping("/category/{id}")
    public AjaxResult generateCategory(Context ctx, Long id) {
        if (generating) {
            return error("正在生成中，请稍后再试");
        }
        try {
            generating = true;
            generatedCount.set(0);

            CmsArticle search = new CmsArticle();
            search.setCategoryId(id);
            search.setStatus("1");
            List<CmsArticle> articles = articleService.selectArticleList(search);
            totalCount.set(articles.size());

            for (CmsArticle article : articles) {
                try {
                    generateStaticHtml(article);
                    generatedCount.incrementAndGet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return success("栏目生成完成，成功 " + generatedCount.get() + "/" + totalCount.get() + " 篇");
        } finally {
            generating = false;
        }
    }

    /**
     * 核心生成逻辑：使用Beetl引擎将模板+文章数据渲染为静态HTML
     */
    private void generateStaticHtml(CmsArticle article) throws IOException {
        // 1. 查找关联的栏目
        CmsCategory category = categoryService.selectById(article.getCategoryId());

        // 2. 查找栏目关联的模板
        CmsTemplate template = null;
        if (category != null && category.getTemplateId() != null) {
            template = templateService.selectById(category.getTemplateId());
        }
        if (template == null) {
            // 回退：使用默认模板
            List<CmsTemplate> templates = templateService.selectTemplateList(new CmsTemplate());
            if (templates != null && !templates.isEmpty()) {
                template = templates.get(0);
            }
        }
        if (template == null || template.getTemplateContent() == null) {
            throw new IOException("未找到可用模板");
        }

        // 3. 用Beetl引擎渲染模板+文章数据
        StringTemplateResourceLoader loader = new StringTemplateResourceLoader();
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(loader, cfg);
        Template t = gt.getTemplate(template.getTemplateContent());
        t.binding("article", article);
        t.binding("category", category);
        t.binding("categoryService", categoryService);
        String html = t.render();

        // 4. 确定生成路径：/static/html/{栏目路径}/{文章ID}.html
        String categoryPath = (category != null && category.getCategoryPath() != null)
                ? category.getCategoryPath() : "default";
        String dirPath = STATIC_HTML_DIR + categoryPath;
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String filePath = dirPath + "/" + article.getId() + ".html";

        // 5. 写入文件
        try (FileWriter writer = new FileWriter(filePath, StandardCharsets.UTF_8)) {
            writer.write(html);
            writer.flush();
        }

        // 6. 更新文章的静态路径
        String staticPath = "/static/html/" + categoryPath + "/" + article.getId() + ".html";
        article.setStaticPath(staticPath);
        articleService.update(article);
    }
}
