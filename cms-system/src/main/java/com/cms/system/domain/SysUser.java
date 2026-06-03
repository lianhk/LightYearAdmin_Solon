package com.cms.system.domain;

import com.cms.common.core.BaseEntity;

import java.util.Date;

/**
 * 系统用户
 */
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private Long deptId;
    private String userName;
    private String nickName;
    private String email;
    private String phoneNumber;
    private String sex;
    private String avatar;
    private String password;
    private String status;
    private String delFlag;
    private String loginIp;
    private Date loginDate;
    private String deptName;
    private Long[] roleIds;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getDeptId() { return deptId; }
    public void setDeptId(Long deptId) { this.deptId = deptId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getNickName() { return nickName; }
    public void setNickName(String nickName) { this.nickName = nickName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDelFlag() { return delFlag; }
    public void setDelFlag(String delFlag) { this.delFlag = delFlag; }

    public String getLoginIp() { return loginIp; }
    public void setLoginIp(String loginIp) { this.loginIp = loginIp; }

    public Date getLoginDate() { return loginDate; }
    public void setLoginDate(Date loginDate) { this.loginDate = loginDate; }

    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }

    public Long[] getRoleIds() { return roleIds; }
    public void setRoleIds(Long[] roleIds) { this.roleIds = roleIds; }

    public boolean isAdmin() {
        return userId != null && userId == 1L;
    }
}
