package com.cms.system.service.impl;

import com.cms.common.exception.ServiceException;
import com.cms.system.domain.SysRole;
import com.cms.system.mapper.SysRoleMapper;
import com.cms.system.service.ISysRoleService;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.List;

@Component
public class SysRoleServiceImpl implements ISysRoleService {

    @Inject
    private SysRoleMapper roleMapper;

    @Override
    public List<SysRole> selectRoleList(SysRole role) {
        return roleMapper.selectRoleList(role);
    }

    @Override
    public List<SysRole> selectRolesByUserId(Long userId) {
        return roleMapper.selectRolesByUserId(userId);
    }

    @Override
    public SysRole selectRoleById(Long roleId) {
        return roleMapper.selectById(roleId);
    }

    @Override
    public int insertRole(SysRole role) {
        int rows = roleMapper.insert(role);
        // 插入角色菜单关联
        if (role.getMenuIds() != null) {
            roleMapper.deleteRoleMenuByRoleId(role.getRoleId());
            for (Long menuId : role.getMenuIds()) {
                roleMapper.insertRoleMenu(role.getRoleId(), menuId);
            }
        }
        return rows;
    }

    @Override
    public int updateRole(SysRole role) {
        int rows = roleMapper.update(role);
        // 更新角色菜单关联
        roleMapper.deleteRoleMenuByRoleId(role.getRoleId());
        if (role.getMenuIds() != null) {
            for (Long menuId : role.getMenuIds()) {
                roleMapper.insertRoleMenu(role.getRoleId(), menuId);
            }
        }
        return rows;
    }

    @Override
    public int deleteRoleByIds(Long[] roleIds) {
        int count = 0;
        for (Long roleId : roleIds) {
            roleMapper.deleteRoleMenuByRoleId(roleId);
            count += roleMapper.deleteById(roleId);
        }
        return count;
    }

    @Override
    public boolean checkRoleNameUnique(String roleName) {
        return roleMapper.checkRoleNameUnique(roleName) == 0;
    }

    @Override
    public boolean checkRoleKeyUnique(String roleKey) {
        return roleMapper.checkRoleKeyUnique(roleKey) == 0;
    }
}
