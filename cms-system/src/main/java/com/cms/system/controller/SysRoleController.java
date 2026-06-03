package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.system.domain.SysRole;
import com.cms.system.service.ISysRoleService;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import org.noear.solon.annotation.Get;
import org.noear.solon.core.handle.Context;

import java.util.List;

@Controller
@Mapping("/system/role")
public class SysRoleController extends BaseController {

    @Inject
    private ISysRoleService roleService;

    @Get
    @Mapping
    public void page(Context ctx) {
        SysRole search = new SysRole();
        String roleName = ctx.param("roleName");
        if (roleName != null && !roleName.isEmpty()) search.setRoleName(roleName);
        String roleKey = ctx.param("roleKey");
        if (roleKey != null && !roleKey.isEmpty()) search.setRoleKey(roleKey);
        String status = ctx.param("status");
        if (status != null && !status.isEmpty()) search.setStatus(status);
        ctx.attrSet("list", roleService.selectRoleList(search));
        ctx.render("role.html");
    }

    @Post
    @Mapping("/add")
    public AjaxResult add(Context ctx, SysRole role) {
        if (!roleService.checkRoleNameUnique(role.getRoleName())) {
            return error("角色名称已存在");
        }
        if (!roleService.checkRoleKeyUnique(role.getRoleKey())) {
            return error("角色权限已存在");
        }
        role.setCreateBy(ctx.session("userName"));
        return roleService.insertRole(role) > 0 ? success() : error();
    }

    @Post
    @Mapping("/edit")
    public AjaxResult edit(Context ctx, SysRole role) {
        role.setUpdateBy(ctx.session("userName"));
        return roleService.updateRole(role) > 0 ? success() : error();
    }

    @Post
    @Mapping("/delete")
    public AjaxResult remove(Context ctx, Long[] roleIds) {
        return roleService.deleteRoleByIds(roleIds) > 0 ? success() : error();
    }
}
