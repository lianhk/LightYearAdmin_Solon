package com.cms.system.mapper;

import com.cms.system.domain.CmsCategory;
import org.beetl.sql.mapper.BaseMapper;
import org.beetl.sql.mapper.annotation.SqlResource;

import java.util.List;

@SqlResource("cmsCategory")
public interface CmsCategoryMapper extends BaseMapper<CmsCategory> {

    List<CmsCategory> selectCategoryList(CmsCategory category);

    CmsCategory checkNameUnique(String categoryName, Long parentId);
}
