package com.cms.system.service;

import com.cms.system.domain.SysUser;
import java.util.List;

public interface ISysUserService {

    SysUser selectUserById(Long userId);

    SysUser selectUserByUserName(String userName);

    List<SysUser> selectUserList(SysUser user);

    int insertUser(SysUser user);

    int updateUser(SysUser user);

    int deleteUserByIds(Long[] userIds);

    int resetPwd(SysUser user);

    int updateUserProfile(SysUser user);

    boolean checkUserNameUnique(String userName);

    boolean checkPhoneUnique(String phoneNumber);

    boolean checkEmailUnique(String email);

    void insertUserRole(Long userId, Long[] roleIds);
}
