package com.cms.system.mapper;

import com.cms.system.domain.CmsBanner;
import org.beetl.sql.core.query.PageQuery;
import org.beetl.sql.mapper.BaseMapper;
import org.beetl.sql.mapper.annotation.SqlResource;
import java.util.List;

@SqlResource("cmsBanner")
public interface CmsBannerMapper extends BaseMapper<CmsBanner> {
    List<CmsBanner> selectBannerList(CmsBanner banner);
    List<CmsBanner> selectBannerList(CmsBanner banner, PageQuery pageQuery);
}
