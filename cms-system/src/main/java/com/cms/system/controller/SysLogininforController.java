package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.common.core.TableDataInfo;
import com.cms.system.domain.SysLogininfor;
import com.cms.system.mapper.SysLogininforMapper;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Delete;

import java.util.List;

@Controller
@Mapping("/monitor/logininfor")
public class SysLogininforController extends BaseController {

    @Inject
    private SysLogininforMapper logininforMapper;

    @Get
    @Mapping("/list")
    public TableDataInfo list(SysLogininfor logininfor) {
        List<SysLogininfor> list = logininforMapper.selectLogininforList(logininfor);
        return getDataTable(list, list.size());
    }

    @Delete
    @Mapping("/clean")
    public AjaxResult clean() {
        logininforMapper.cleanLogininfor();
        return success();
    }
}
