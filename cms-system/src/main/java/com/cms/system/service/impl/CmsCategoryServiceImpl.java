package com.cms.system.service.impl;

import com.cms.common.exception.ServiceException;
import com.cms.system.domain.CmsCategory;
import com.cms.system.mapper.CmsCategoryMapper;
import com.cms.system.service.ICmsCategoryService;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CmsCategoryServiceImpl implements ICmsCategoryService {

    @Inject
    private CmsCategoryMapper categoryMapper;

    @Override
    public List<CmsCategory> selectCategoryList(CmsCategory category) {
        List<CmsCategory> list = categoryMapper.selectCategoryList(category);
        return buildCategoryTree(list);
    }

    @Override
    public CmsCategory selectCategoryById(Long categoryId) {
        return categoryMapper.selectById(categoryId);
    }

    @Override
    public int insertCategory(CmsCategory category) {
        return categoryMapper.insert(category);
    }

    @Override
    public int updateCategory(CmsCategory category) {
        return categoryMapper.update(category);
    }

    @Override
    public int deleteCategoryById(Long categoryId) {
        return categoryMapper.deleteById(categoryId);
    }

    @Override
    public boolean checkNameUnique(String categoryName, Long parentId) {
        return categoryMapper.checkNameUnique(categoryName, parentId) == null;
    }

    /**
     * 构建栏目树
     */
    private List<CmsCategory> buildCategoryTree(List<CmsCategory> categoryList) {
        List<CmsCategory> returnList = new ArrayList<>();
        Map<Long, CmsCategory> categoryMap = new HashMap<>();
        for (CmsCategory category : categoryList) {
            categoryMap.put(category.getCategoryId(), category);
        }
        for (CmsCategory category : categoryList) {
            Long parentId = category.getParentId();
            if (parentId == null || parentId == 0) {
                returnList.add(category);
            } else {
                CmsCategory parent = categoryMap.get(parentId);
                if (parent != null) {
                    parent.getChildren().add(category);
                }
            }
        }
        return returnList;
    }
}
