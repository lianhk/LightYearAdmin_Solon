package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.system.domain.SysDept;
import com.cms.system.service.ISysDeptService;
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
@Mapping("/system/dept")
public class SysDeptController extends BaseController {

    @Inject
    private ISysDeptService deptService;

    @Get
    @Mapping("/list")
    public AjaxResult list(SysDept dept) {
        List<SysDept> depts = deptService.selectDeptList(dept);
        return success(depts);
    }

    @Get
    @Mapping("/{deptId}")
    public AjaxResult getInfo(Long deptId) {
        return success(deptService.selectDeptById(deptId));
    }

    @Post
    @Mapping
    public AjaxResult add(SysDept dept) {
        if (!deptService.checkDeptNameUnique(dept.getDeptName(), dept.getParentId())) {
            return error("部门名称已存在");
        }
        dept.setCreateBy(getLoginUsername());
        return deptService.insertDept(dept) > 0 ? success() : error();
    }

    @Put
    @Mapping
    public AjaxResult edit(SysDept dept) {
        dept.setUpdateBy(getLoginUsername());
        return deptService.updateDept(dept) > 0 ? success() : error();
    }

    @Delete
    @Mapping("/{deptId}")
    public AjaxResult remove(Long deptId) {
        return deptService.deleteDeptById(deptId) > 0 ? success() : error();
    }

    private String getLoginUsername() {
        return Context.current().attr("username");
    }
}
