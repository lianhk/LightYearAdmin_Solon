package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.system.domain.SysDictType;
import com.cms.system.domain.SysDictData;
import com.cms.system.service.ISysDictTypeService;
import com.cms.system.service.ISysDictDataService;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import org.noear.solon.annotation.Get;
import org.noear.solon.core.handle.Context;

import java.util.List;

@Controller
@Mapping("/system/dict")
public class SysDictTypeController extends BaseController {

    @Inject
    private ISysDictTypeService dictTypeService;

    @Inject
    private ISysDictDataService dictDataService;

    @Get
    @Mapping
    public void page(Context ctx) {
        SysDictType typeSearch = new SysDictType();
        String dictName = ctx.param("dictName");
        if (dictName != null && !dictName.isEmpty()) typeSearch.setDictName(dictName);
        String dictType = ctx.param("dictType");
        if (dictType != null && !dictType.isEmpty()) typeSearch.setDictType(dictType);
        ctx.attrSet("typeList", dictTypeService.selectDictTypeList(typeSearch));

        String selectedType = ctx.param("selectedType");
        if (selectedType != null && !selectedType.isEmpty()) {
            ctx.attrSet("dataList", dictDataService.selectDictDataByType(selectedType));
            ctx.attrSet("selectedType", selectedType);
        }
        ctx.render("dict.html");
    }

    @Post
    @Mapping("/type/add")
    public AjaxResult addType(Context ctx, SysDictType dictType) {
        if (!dictTypeService.checkDictTypeUnique(dictType.getDictType())) {
            return error("字典类型已存在");
        }
        dictType.setCreateBy(ctx.session("userName"));
        return dictTypeService.insertDictType(dictType) > 0 ? success() : error();
    }

    @Post
    @Mapping("/type/edit")
    public AjaxResult editType(Context ctx, SysDictType dictType) {
        dictType.setUpdateBy(ctx.session("userName"));
        return dictTypeService.updateDictType(dictType) > 0 ? success() : error();
    }

    @Post
    @Mapping("/type/delete")
    public AjaxResult removeType(Context ctx, Long[] dictIds) {
        return dictTypeService.deleteDictTypeByIds(dictIds) > 0 ? success() : error();
    }

    @Post
    @Mapping("/data/add")
    public AjaxResult addData(Context ctx, SysDictData dictData) {
        dictData.setCreateBy(ctx.session("userName"));
        return dictDataService.insertDictData(dictData) > 0 ? success() : error();
    }

    @Post
    @Mapping("/data/edit")
    public AjaxResult editData(Context ctx, SysDictData dictData) {
        dictData.setUpdateBy(ctx.session("userName"));
        return dictDataService.updateDictData(dictData) > 0 ? success() : error();
    }

    @Post
    @Mapping("/data/delete")
    public AjaxResult removeData(Context ctx, Long[] dictCodes) {
        return dictDataService.deleteDictDataByIds(dictCodes) > 0 ? success() : error();
    }
}
