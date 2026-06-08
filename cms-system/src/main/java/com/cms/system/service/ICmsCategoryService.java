package com.cms.system.service;

import com.cms.system.domain.CmsCategory;

import java.util.List;

public interface ICmsCategoryService {

    List<CmsCategory> selectCategoryList(CmsCategory category);

    CmsCategory selectCategoryById(Long categoryId);

    int insertCategory(CmsCategory category);

    int updateCategory(CmsCategory category);

    int deleteCategoryById(Long categoryId);

    boolean checkNameUnique(String categoryName, Long parentId);
}
