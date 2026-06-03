package com.cms.system.domain;

import com.cms.common.core.BaseEntity;

/**
 * 系统角色
 */
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long roleId;
    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private String dataScope;
    private String menuCheckStrictly;
    private String deptCheckStrictly;
    private String status;
    private String delFlag;
    private Long[] menuIds;
    private Long[] deptIds;

    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public String getRoleKey() { return roleKey; }
    public void setRoleKey(String roleKey) { this.roleKey = roleKey; }

    public Integer getRoleSort() { return roleSort; }
    public void setRoleSort(Integer roleSort) { this.roleSort = roleSort; }

    public String getDataScope() { return dataScope; }
    public void setDataScope(String dataScope) { this.dataScope = dataScope; }

    public String getMenuCheckStrictly() { return menuCheckStrictly; }
    public void setMenuCheckStrictly(String menuCheckStrictly) { this.menuCheckStrictly = menuCheckStrictly; }

    public String getDeptCheckStrictly() { return deptCheckStrictly; }
    public void setDeptCheckStrictly(String deptCheckStrictly) { this.deptCheckStrictly = deptCheckStrictly; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDelFlag() { return delFlag; }
    public void setDelFlag(String delFlag) { this.delFlag = delFlag; }

    public Long[] getMenuIds() { return menuIds; }
    public void setMenuIds(Long[] menuIds) { this.menuIds = menuIds; }

    public Long[] getDeptIds() { return deptIds; }
    public void setDeptIds(Long[] deptIds) { this.deptIds = deptIds; }
}
