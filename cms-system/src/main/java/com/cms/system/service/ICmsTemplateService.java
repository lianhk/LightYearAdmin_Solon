package com.cms.system.service;

import com.cms.system.domain.CmsTemplate;
import org.beetl.sql.core.query.PageQuery;

import java.util.List;

public interface ICmsTemplateService {

    List<CmsTemplate> selectTemplateList(CmsTemplate template);

    List<CmsTemplate> selectTemplatePage(CmsTemplate template, PageQuery pageQuery);

    CmsTemplate selectTemplateById(Long templateId);

    CmsTemplate selectTemplateByCode(String templateCode);

    int insertTemplate(CmsTemplate template);

    int updateTemplate(CmsTemplate template);

    int deleteTemplateByIds(Long[] templateIds);
}
