package com.cms.system.mapper;

import com.cms.system.domain.CmsTemplate;
import org.beetl.sql.core.query.PageQuery;
import org.beetl.sql.mapper.BaseMapper;
import org.beetl.sql.mapper.annotation.SqlResource;

import java.util.List;

@SqlResource("cmsTemplate")
public interface CmsTemplateMapper extends BaseMapper<CmsTemplate> {

    List<CmsTemplate> selectTemplateList(CmsTemplate template);

    List<CmsTemplate> selectTemplateList(CmsTemplate template, PageQuery pageQuery);

    CmsTemplate selectByCode(String templateCode);
}
