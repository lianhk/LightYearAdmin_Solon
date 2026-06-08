package com.cms.system.domain;

import com.cms.common.core.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * CMS栏目
 */
public class CmsCategory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long categoryId;
    private Long parentId;
    private String categoryName;
    private String categoryCode;
    private Long templateId;
    private Long articleTemplateId;
    private String seoTitle;
    private String seoKeywords;
    private String seoDescription;
    private String urlPath;
    private String image;
    private String targetBlank;
    private Integer sortNum;
    private String status;
    private List<CmsCategory> children = new ArrayList<>();

    // -- 非表字段(树结构)
    /** 父栏目名称 */
    private String parentName;

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public Long getParentId() { return parentId; }
    public void setParentId(Long parentId) { this.parentId = parentId; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getCategoryCode() { return categoryCode; }
    public void setCategoryCode(String categoryCode) { this.categoryCode = categoryCode; }

    public Long getTemplateId() { return templateId; }
    public void setTemplateId(Long templateId) { this.templateId = templateId; }

    public Long getArticleTemplateId() { return articleTemplateId; }
    public void setArticleTemplateId(Long articleTemplateId) { this.articleTemplateId = articleTemplateId; }

    public String getSeoTitle() { return seoTitle; }
    public void setSeoTitle(String seoTitle) { this.seoTitle = seoTitle; }

    public String getSeoKeywords() { return seoKeywords; }
    public void setSeoKeywords(String seoKeywords) { this.seoKeywords = seoKeywords; }

    public String getSeoDescription() { return seoDescription; }
    public void setSeoDescription(String seoDescription) { this.seoDescription = seoDescription; }

    public String getUrlPath() { return urlPath; }
    public void setUrlPath(String urlPath) { this.urlPath = urlPath; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getTargetBlank() { return targetBlank; }
    public void setTargetBlank(String targetBlank) { this.targetBlank = targetBlank; }

    public Integer getSortNum() { return sortNum; }
    public void setSortNum(Integer sortNum) { this.sortNum = sortNum; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<CmsCategory> getChildren() { return children; }
    public void setChildren(List<CmsCategory> children) { this.children = children; }

    public String getParentName() { return parentName; }
    public void setParentName(String parentName) { this.parentName = parentName; }
}
