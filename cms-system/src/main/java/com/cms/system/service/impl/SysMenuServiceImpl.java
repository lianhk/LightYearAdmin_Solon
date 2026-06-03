package com.cms.system.service.impl;

import com.cms.system.domain.SysMenu;
import com.cms.system.mapper.SysMenuMapper;
import com.cms.system.service.ISysMenuService;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.*;

@Component
public class SysMenuServiceImpl implements ISysMenuService {

    @Inject
    private SysMenuMapper menuMapper;

    @Override
    public List<SysMenu> selectMenuList(SysMenu menu) {
        return menuMapper.selectMenuList(menu);
    }

    @Override
    public List<SysMenu> selectMenuTreeAll() {
        return buildMenuTree(menuMapper.selectMenuTreeAll());
    }

    @Override
    public List<SysMenu> selectMenusByUserId(Long userId) {
        List<SysMenu> menus;
        // 管理员拥有所有菜单
        if (userId == 1L) {
            menus = menuMapper.selectMenuTreeAll();
        } else {
            menus = menuMapper.selectMenusByUserId(userId);
        }
        return buildMenuTree(menus);
    }

    @Override
    public List<String> selectMenuPermsByUserId(Long userId) {
        return menuMapper.selectMenuPermsByUserId(userId);
    }

    @Override
    public SysMenu selectMenuById(Long menuId) {
        return menuMapper.selectById(menuId);
    }

    @Override
    public int insertMenu(SysMenu menu) {
        return menuMapper.insert(menu);
    }

    @Override
    public int updateMenu(SysMenu menu) {
        return menuMapper.update(menu);
    }

    @Override
    public int deleteMenuById(Long menuId) {
        return menuMapper.deleteById(menuId);
    }

    @Override
    public boolean hasChildByMenuId(Long menuId) {
        return menuMapper.hasChildByMenuId(menuId) > 0;
    }

    @Override
    public boolean checkMenuNameUnique(String menuName, Long parentId) {
        return menuMapper.checkMenuNameUnique(menuName, parentId) == 0;
    }

    /**
     * 构建树形结构
     */
    private List<SysMenu> buildMenuTree(List<SysMenu> menuList) {
        List<SysMenu> returnList = new ArrayList<>();
        Map<Long, SysMenu> menuMap = new HashMap<>();
        for (SysMenu menu : menuList) {
            menuMap.put(menu.getMenuId(), menu);
        }
        for (SysMenu menu : menuList) {
            Long parentId = menu.getParentId();
            if (parentId == null || parentId == 0) {
                returnList.add(menu);
            } else {
                SysMenu parent = menuMap.get(parentId);
                if (parent != null) {
                    parent.getChildren().add(menu);
                }
            }
        }
        return returnList;
    }
}
