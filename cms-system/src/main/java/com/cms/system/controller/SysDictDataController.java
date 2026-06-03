package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.common.core.TableDataInfo;
import com.cms.system.domain.SysDictData;
import com.cms.system.service.ISysDictDataService;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Delete;
import org.noear.solon.annotation.Put;
import org.noear.solon.core.handle.Context;

import java.util.List;

@Controller
@Mapping("/system/dict/data")
public class SysDictDataController extends BaseController {

    @Inject
    private ISysDictDataService dictDataService;

    @Get
    @Mapping("/list")
    public TableDataInfo list(SysDictData dictData) {
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list, list.size());
    }

    @Get
    @Mapping("/type/{dictType}")
    public AjaxResult dictType(String dictType) {
        List<SysDictData> data = dictDataService.selectDictDataByType(dictType);
        return success(data);
    }

    @Get
    @Mapping("/{dictCode}")
    public AjaxResult getInfo(Long dictCode) {
        return success(dictDataService.selectDictDataById(dictCode));
    }

    @Post
    @Mapping
    public AjaxResult add(SysDictData dictData) {
        dictData.setCreateBy(getLoginUsername());
        return dictDataService.insertDictData(dictData) > 0 ? success() : error();
    }

    @Put
    @Mapping
    public AjaxResult edit(SysDictData dictData) {
        dictData.setUpdateBy(getLoginUsername());
        return dictDataService.updateDictData(dictData) > 0 ? success() : error();
    }

    @Delete
    @Mapping("/{dictCodes}")
    public AjaxResult remove(Long[] dictCodes) {
        return dictDataService.deleteDictDataByIds(dictCodes) > 0 ? success() : error();
    }

    private String getLoginUsername() {
        return Context.current().attr("username");
    }
}
