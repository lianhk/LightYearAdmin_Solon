package com.cms.system.service;

import com.cms.system.domain.SysMenu;
import java.util.List;

public interface ISysMenuService {

    List<SysMenu> selectMenuList(SysMenu menu);

    List<SysMenu> selectMenuTreeAll();

    List<SysMenu> selectMenusByUserId(Long userId);

    List<String> selectMenuPermsByUserId(Long userId);

    SysMenu selectMenuById(Long menuId);

    int insertMenu(SysMenu menu);

    int updateMenu(SysMenu menu);

    int deleteMenuById(Long menuId);

    boolean hasChildByMenuId(Long menuId);

    boolean checkMenuNameUnique(String menuName, Long parentId);
}
