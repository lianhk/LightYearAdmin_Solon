package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.common.core.TableDataInfo;
import com.cms.system.domain.SysDictType;
import com.cms.system.service.ISysDictTypeService;
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
@Mapping("/system/dict/type")
public class SysDictTypeController extends BaseController {

    @Inject
    private ISysDictTypeService dictTypeService;

    @Get
    @Mapping("/list")
    public TableDataInfo list(SysDictType dictType) {
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list, list.size());
    }

    @Get
    @Mapping("/{dictId}")
    public AjaxResult getInfo(Long dictId) {
        return success(dictTypeService.selectDictTypeById(dictId));
    }

    @Post
    @Mapping
    public AjaxResult add(SysDictType dictType) {
        if (!dictTypeService.checkDictTypeUnique(dictType.getDictType())) {
            return error("字典类型已存在");
        }
        dictType.setCreateBy(getLoginUsername());
        return dictTypeService.insertDictType(dictType) > 0 ? success() : error();
    }

    @Put
    @Mapping
    public AjaxResult edit(SysDictType dictType) {
        dictType.setUpdateBy(getLoginUsername());
        return dictTypeService.updateDictType(dictType) > 0 ? success() : error();
    }

    @Delete
    @Mapping("/{dictIds}")
    public AjaxResult remove(Long[] dictIds) {
        return dictTypeService.deleteDictTypeByIds(dictIds) > 0 ? success() : error();
    }

    private String getLoginUsername() {
        return Context.current().attr("username");
    }
}
