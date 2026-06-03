package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.system.domain.SysMenu;
import com.cms.system.service.ISysMenuService;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import org.noear.solon.annotation.Get;
import org.noear.solon.core.handle.Context;

import java.util.List;

@Controller
@Mapping("/system/menu")
public class SysMenuController extends BaseController {

    @Inject
    private ISysMenuService menuService;

    @Get
    @Mapping
    public void page(Context ctx) {
        SysMenu search = new SysMenu();
        String menuName = ctx.param("menuName");
        if (menuName != null && !menuName.isEmpty()) search.setMenuName(menuName);
        String status = ctx.param("status");
        if (status != null && !status.isEmpty()) search.setStatus(status);
        ctx.attrSet("list", menuService.selectMenuList(search));
        ctx.render("menu.html");
    }

    @Get
    @Mapping("/detail")
    public AjaxResult detail(Context ctx) {
        return success(menuService.selectMenuById(Long.parseLong(ctx.param("id"))));
    }

    @Post
    @Mapping("/add")
    public AjaxResult add(Context ctx, SysMenu menu) {
        if (!menuService.checkMenuNameUnique(menu.getMenuName(), menu.getParentId())) {
            return error("菜单名称已存在");
        }
        menu.setCreateBy(ctx.session("userName"));
        return menuService.insertMenu(menu) > 0 ? success() : error();
    }

    @Post
    @Mapping("/edit")
    public AjaxResult edit(Context ctx, SysMenu menu) {
        menu.setUpdateBy(ctx.session("userName"));
        return menuService.updateMenu(menu) > 0 ? success() : error();
    }

    @Post
    @Mapping("/delete")
    public AjaxResult remove(Context ctx, Long menuId) {
        if (menuService.hasChildByMenuId(menuId)) {
            return error("存在子菜单,不允许删除");
        }
        return menuService.deleteMenuById(menuId) > 0 ? success() : error();
    }
}
