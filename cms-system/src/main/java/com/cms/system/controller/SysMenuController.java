package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.common.core.Constants;
import com.cms.system.domain.SysMenu;
import com.cms.system.service.ISysMenuService;
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
@Mapping("/system/menu")
public class SysMenuController extends BaseController {

    @Inject
    private ISysMenuService menuService;

    @Get
    @Mapping("/list")
    public AjaxResult list(SysMenu menu) {
        List<SysMenu> menus = menuService.selectMenuList(menu);
        return success(menus);
    }

    @Get
    @Mapping("/treeselect")
    public AjaxResult treeselect(SysMenu menu) {
        List<SysMenu> menus = menuService.selectMenuList(menu);
        return success(menus);
    }

    @Get
    @Mapping("/{menuId}")
    public AjaxResult getInfo(Long menuId) {
        return success(menuService.selectMenuById(menuId));
    }

    @Get
    @Mapping("/roleMenuTreeselect/{roleId}")
    public AjaxResult roleMenuTreeselect(Long roleId, SysMenu menu) {
        List<SysMenu> menus = menuService.selectMenuList(menu);
        // 这里可以添加角色已选菜单的ID
        return success(menus);
    }

    @Post
    @Mapping
    public AjaxResult add(SysMenu menu) {
        if (!menuService.checkMenuNameUnique(menu.getMenuName(), menu.getParentId())) {
            return error("菜单名称已存在");
        }
        menu.setCreateBy(getLoginUsername());
        return menuService.insertMenu(menu) > 0 ? success() : error();
    }

    @Put
    @Mapping
    public AjaxResult edit(SysMenu menu) {
        menu.setUpdateBy(getLoginUsername());
        return menuService.updateMenu(menu) > 0 ? success() : error();
    }

    @Delete
    @Mapping("/{menuId}")
    public AjaxResult remove(Long menuId) {
        if (menuService.hasChildByMenuId(menuId)) {
            return error("存在子菜单,不允许删除");
        }
        return menuService.deleteMenuById(menuId) > 0 ? success() : error();
    }

    private String getLoginUsername() {
        return Context.current().attr("username");
    }
}
