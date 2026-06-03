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
import org.noear.solon.core.handle.Context;

import java.util.List;

@Controller
@Mapping("/system/dept")
public class SysDeptController extends BaseController {

    @Inject
    private ISysDeptService deptService;

    @Get
    @Mapping
    public void page(Context ctx) {
        SysDept search = new SysDept();
        String deptName = ctx.param("deptName");
        if (deptName != null && !deptName.isEmpty()) search.setDeptName(deptName);
        String status = ctx.param("status");
        if (status != null && !status.isEmpty()) search.setStatus(status);
        ctx.attrSet("list", deptService.selectDeptList(search));
        ctx.render("dept.html");
    }

    @Post
    @Mapping("/add")
    public AjaxResult add(Context ctx, SysDept dept) {
        if (!deptService.checkDeptNameUnique(dept.getDeptName(), dept.getParentId())) {
            return error("部门名称已存在");
        }
        dept.setCreateBy(ctx.session("userName"));
        return deptService.insertDept(dept) > 0 ? success() : error();
    }

    @Post
    @Mapping("/edit")
    public AjaxResult edit(Context ctx, SysDept dept) {
        dept.setUpdateBy(ctx.session("userName"));
        return deptService.updateDept(dept) > 0 ? success() : error();
    }

    @Post
    @Mapping("/delete")
    public AjaxResult remove(Context ctx, Long deptId) {
        return deptService.deleteDeptById(deptId) > 0 ? success() : error();
    }
}
