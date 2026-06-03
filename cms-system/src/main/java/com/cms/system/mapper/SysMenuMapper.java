package com.cms.system.mapper;

import com.cms.system.domain.SysMenu;
import org.beetl.sql.mapper.BaseMapper;
import org.beetl.sql.mapper.annotation.SqlResource;

import java.util.List;

@SqlResource("sysMenu")
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> selectMenuList(SysMenu menu);

    List<SysMenu> selectMenuTreeAll();

    List<SysMenu> selectMenusByUserId(Long userId);

    List<String> selectMenuPermsByUserId(Long userId);

    List<Long> selectMenuListByRoleId(Long roleId);

    int hasChildByMenuId(Long menuId);

    int checkMenuNameUnique(String menuName, Long parentId);
}
