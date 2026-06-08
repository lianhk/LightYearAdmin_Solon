package com.cms.system.service.impl;

import com.cms.system.domain.CmsArticle;
import com.cms.system.mapper.CmsArticleMapper;
import com.cms.system.service.ICmsArticleService;
import org.beetl.sql.core.query.PageQuery;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.List;

@Component
public class CmsArticleServiceImpl implements ICmsArticleService {

    @Inject
    private CmsArticleMapper articleMapper;

    @Override
    public List<CmsArticle> selectArticleList(CmsArticle article) {
        return articleMapper.selectArticleList(article);
    }

    @Override
    public List<CmsArticle> selectArticlePage(CmsArticle article, PageQuery pageQuery) {
        return articleMapper.selectArticleList(article, pageQuery);
    }

    @Override
    public CmsArticle selectArticleById(Long articleId) {
        return articleMapper.selectById(articleId);
    }

    @Override
    public int insertArticle(CmsArticle article) {
        return articleMapper.insert(article);
    }

    @Override
    public int updateArticle(CmsArticle article) {
        return articleMapper.update(article);
    }

    @Override
    public int deleteArticleByIds(Long[] articleIds) {
        int count = 0;
        for (Long articleId : articleIds) {
            count += articleMapper.deleteById(articleId);
        }
        return count;
    }

    @Override
    public int updateViewCount(Long articleId) {
        return articleMapper.updateViewCount(articleId);
    }
}
