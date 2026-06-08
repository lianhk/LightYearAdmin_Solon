package com.cms.system.service.impl;

import com.cms.system.domain.CmsTemplate;
import com.cms.system.mapper.CmsTemplateMapper;
import com.cms.system.service.ICmsTemplateService;
import org.beetl.sql.core.query.PageQuery;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.List;

@Component
public class CmsTemplateServiceImpl implements ICmsTemplateService {

    @Inject
    private CmsTemplateMapper templateMapper;

    @Override
    public List<CmsTemplate> selectTemplateList(CmsTemplate template) {
        return templateMapper.selectTemplateList(template);
    }

    @Override
    public List<CmsTemplate> selectTemplatePage(CmsTemplate template, PageQuery pageQuery) {
        return templateMapper.selectTemplateList(template, pageQuery);
    }

    @Override
    public CmsTemplate selectTemplateById(Long templateId) {
        return templateMapper.selectById(templateId);
    }

    @Override
    public CmsTemplate selectTemplateByCode(String templateCode) {
        return templateMapper.selectByCode(templateCode);
    }

    @Override
    public int insertTemplate(CmsTemplate template) {
        return templateMapper.insert(template);
    }

    @Override
    public int updateTemplate(CmsTemplate template) {
        return templateMapper.update(template);
    }

    @Override
    public int deleteTemplateByIds(Long[] templateIds) {
        int count = 0;
        for (Long templateId : templateIds) {
            count += templateMapper.deleteById(templateId);
        }
        return count;
    }
}
