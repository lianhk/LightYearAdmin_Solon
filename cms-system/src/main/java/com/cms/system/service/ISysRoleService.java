package com.cms.system.service;

import com.cms.system.domain.SysRole;
import org.beetl.sql.core.query.PageQuery;

import java.util.List;

public interface ISysRoleService {

    List<SysRole> selectRoleList(SysRole role);

    List<SysRole> selectRolePage(SysRole role, PageQuery pageQuery);

    List<SysRole> selectRolesByUserId(Long userId);

    SysRole selectRoleById(Long roleId);

    int insertRole(SysRole role);

    int updateRole(SysRole role);

    int deleteRoleByIds(Long[] roleIds);

    boolean checkRoleNameUnique(String roleName);

    boolean checkRoleKeyUnique(String roleKey);
}
