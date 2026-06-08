package com.cms.system.service;

import com.cms.system.domain.CmsArticle;
import org.beetl.sql.core.query.PageQuery;

import java.util.List;

public interface ICmsArticleService {

    List<CmsArticle> selectArticleList(CmsArticle article);

    List<CmsArticle> selectArticlePage(CmsArticle article, PageQuery pageQuery);

    CmsArticle selectArticleById(Long articleId);

    int insertArticle(CmsArticle article);

    int updateArticle(CmsArticle article);

    int deleteArticleByIds(Long[] articleIds);

    int updateViewCount(Long articleId);
}
