package com.cms.system.mapper;

import com.cms.system.domain.SysRole;
import org.beetl.sql.mapper.BaseMapper;
import org.beetl.sql.mapper.annotation.SqlResource;

import java.util.List;

@SqlResource("sysRole")
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> selectRoleList(SysRole role);

    List<SysRole> selectRolesByUserId(Long userId);

    int checkRoleNameUnique(String roleName);

    int checkRoleKeyUnique(String roleKey);

    int deleteRoleMenuByRoleId(Long roleId);

    int insertRoleMenu(Long roleId, Long menuId);
}
