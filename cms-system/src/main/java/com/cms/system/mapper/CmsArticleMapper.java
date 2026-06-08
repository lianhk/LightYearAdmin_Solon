package com.cms.system.mapper;

import com.cms.system.domain.CmsArticle;
import org.beetl.sql.core.query.PageQuery;
import org.beetl.sql.mapper.BaseMapper;
import org.beetl.sql.mapper.annotation.SqlResource;

import java.util.List;

@SqlResource("cmsArticle")
public interface CmsArticleMapper extends BaseMapper<CmsArticle> {

    List<CmsArticle> selectArticleList(CmsArticle article);

    List<CmsArticle> selectArticleList(CmsArticle article, PageQuery pageQuery);
}
