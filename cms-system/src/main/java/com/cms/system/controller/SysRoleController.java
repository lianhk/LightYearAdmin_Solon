package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.common.core.TableDataInfo;
import com.cms.system.domain.SysRole;
import com.cms.system.service.ISysRoleService;
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
@Mapping("/system/role")
public class SysRoleController extends BaseController {

    @Inject
    private ISysRoleService roleService;

    @Get
    @Mapping("/list")
    public TableDataInfo list(SysRole role) {
        List<SysRole> list = roleService.selectRoleList(role);
        return getDataTable(list, list.size());
    }

    @Get
    @Mapping("/{roleId}")
    public AjaxResult getInfo(Long roleId) {
        return success(roleService.selectRoleById(roleId));
    }

    @Post
    @Mapping
    public AjaxResult add(SysRole role) {
        if (!roleService.checkRoleNameUnique(role.getRoleName())) {
            return error("角色名称已存在");
        }
        if (!roleService.checkRoleKeyUnique(role.getRoleKey())) {
            return error("角色权限已存在");
        }
        role.setCreateBy(getLoginUsername());
        return roleService.insertRole(role) > 0 ? success() : error();
    }

    @Put
    @Mapping
    public AjaxResult edit(SysRole role) {
        role.setUpdateBy(getLoginUsername());
        return roleService.updateRole(role) > 0 ? success() : error();
    }

    @Delete
    @Mapping("/{roleIds}")
    public AjaxResult remove(Long[] roleIds) {
        return roleService.deleteRoleByIds(roleIds) > 0 ? success() : error();
    }

    private String getLoginUsername() {
        return Context.current().attr("username");
    }
}
