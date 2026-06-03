package com.cms.system.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.cms.common.core.UserConstants;
import com.cms.common.exception.ServiceException;
import com.cms.common.utils.StringUtils;
import com.cms.system.domain.SysUser;
import com.cms.system.mapper.SysUserMapper;
import com.cms.system.service.ISysUserService;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.List;

@Component
public class SysUserServiceImpl implements ISysUserService {

    @Inject
    private SysUserMapper userMapper;

    @Override
    public SysUser selectUserById(Long userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public SysUser selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }

    @Override
    public List<SysUser> selectUserList(SysUser user) {
        return userMapper.selectUserList(user);
    }

    @Override
    public int insertUser(SysUser user) {
        // 密码加密
        user.setPassword(SecureUtil.md5(user.getPassword()));
        return userMapper.insert(user);
    }

    @Override
    public int updateUser(SysUser user) {
        return userMapper.update(user);
    }

    @Override
    public int deleteUserByIds(Long[] userIds) {
        int count = 0;
        for (Long userId : userIds) {
            if (UserConstants.ADMIN_ID.equals(userId)) {
                throw new ServiceException("不允许删除超级管理员用户");
            }
            count += userMapper.deleteById(userId);
        }
        return count;
    }

    @Override
    public int resetPwd(SysUser user) {
        user.setPassword(SecureUtil.md5(user.getPassword()));
        return userMapper.update(user);
    }

    @Override
    public int updateUserProfile(SysUser user) {
        return userMapper.update(user);
    }

    @Override
    public boolean checkUserNameUnique(String userName) {
        return userMapper.checkUserNameUnique(userName) == 0;
    }

    @Override
    public boolean checkPhoneUnique(String phoneNumber) {
        return userMapper.checkPhoneUnique(phoneNumber) == 0;
    }

    @Override
    public boolean checkEmailUnique(String email) {
        return userMapper.checkEmailUnique(email) == 0;
    }

    @Override
    public void insertUserRole(Long userId, Long[] roleIds) {
        // 删除原有角色关联
        userMapper.deleteUserRoleByUserId(userId);
        // 插入新的角色关联
        if (roleIds != null) {
            for (Long roleId : roleIds) {
                userMapper.insertUserRole(userId, roleId);
            }
        }
    }
}
