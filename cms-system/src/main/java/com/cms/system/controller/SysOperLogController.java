package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.common.core.TableDataInfo;
import com.cms.system.domain.SysOperLog;
import com.cms.system.mapper.SysOperLogMapper;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Delete;

import java.util.List;

@Controller
@Mapping("/monitor/operlog")
public class SysOperLogController extends BaseController {

    @Inject
    private SysOperLogMapper operLogMapper;

    @Get
    @Mapping("/list")
    public TableDataInfo list(SysOperLog operLog) {
        List<SysOperLog> list = operLogMapper.selectOperLogList(operLog);
        return getDataTable(list, list.size());
    }

    @Delete
    @Mapping("/clean")
    public AjaxResult clean() {
        operLogMapper.cleanOperLog();
        return success();
    }
}
