package com.cms.system.domain;

import com.cms.common.core.BaseEntity;

import java.util.Date;

/**
 * CMS文章
 */
public class CmsArticle extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long articleId;
    private Long categoryId;
    private String title;
    private String summary;
    private String content;
    private String coverImage;
    private String author;
    private String source;
    private String sourceUrl;
    private String seoTitle;
    private String seoKeywords;
    private String seoDescription;
    private String tags;
    private String status;
    private String isTop;
    private String isRecommend;
    private Integer viewCount;
    private Date publishTime;
    private String staticPath;

    // -- 非表字段
    /** 栏目名称 */
    private String categoryName;

    public Long getArticleId() { return articleId; }
    public void setArticleId(Long articleId) { this.articleId = articleId; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getSourceUrl() { return sourceUrl; }
    public void setSourceUrl(String sourceUrl) { this.sourceUrl = sourceUrl; }

    public String getSeoTitle() { return seoTitle; }
    public void setSeoTitle(String seoTitle) { this.seoTitle = seoTitle; }

    public String getSeoKeywords() { return seoKeywords; }
    public void setSeoKeywords(String seoKeywords) { this.seoKeywords = seoKeywords; }

    public String getSeoDescription() { return seoDescription; }
    public void setSeoDescription(String seoDescription) { this.seoDescription = seoDescription; }

    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getIsTop() { return isTop; }
    public void setIsTop(String isTop) { this.isTop = isTop; }

    public String getIsRecommend() { return isRecommend; }
    public void setIsRecommend(String isRecommend) { this.isRecommend = isRecommend; }

    public Integer getViewCount() { return viewCount; }
    public void setViewCount(Integer viewCount) { this.viewCount = viewCount; }

    public Date getPublishTime() { return publishTime; }
    public void setPublishTime(Date publishTime) { this.publishTime = publishTime; }

    public String getStaticPath() { return staticPath; }
    public void setStaticPath(String staticPath) { this.staticPath = staticPath; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
