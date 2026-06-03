package com.cms.system.service;

import com.cms.system.domain.SysDept;
import java.util.List;

public interface ISysDeptService {

    List<SysDept> selectDeptList(SysDept dept);

    SysDept selectDeptById(Long deptId);

    int insertDept(SysDept dept);

    int updateDept(SysDept dept);

    int deleteDeptById(Long deptId);

    boolean hasChildByDeptId(Long deptId);

    boolean checkDeptExistUser(Long deptId);

    boolean checkDeptNameUnique(String deptName, Long parentId);
}
