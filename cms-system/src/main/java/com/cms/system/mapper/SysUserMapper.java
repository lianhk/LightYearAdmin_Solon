package com.cms.system.mapper;

import com.cms.system.domain.SysUser;
import org.beetl.sql.core.query.PageQuery;
import org.beetl.sql.mapper.BaseMapper;
import org.beetl.sql.mapper.annotation.Sql;
import org.beetl.sql.mapper.annotation.SqlResource;

import java.util.List;

@SqlResource("sysUser")
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser selectUserByUserName(String userName);

    List<SysUser> selectUserList(SysUser user);

    List<SysUser> selectUserList(SysUser user, PageQuery pageQuery);

    int checkUserNameUnique(String userName);

    int checkPhoneUnique(String phoneNumber);

    int checkEmailUnique(String email);

    int deleteUserRoleByUserId(Long userId);

    int insertUserRole(Long userId, Long roleId);
}
