package com.cms.system.mapper;

import com.cms.system.domain.SysDept;
import org.beetl.sql.mapper.BaseMapper;
import org.beetl.sql.mapper.annotation.SqlResource;

import java.util.List;

@SqlResource("sysDept")
public interface SysDeptMapper extends BaseMapper<SysDept> {

    List<SysDept> selectDeptList(SysDept dept);

    int checkDeptNameUnique(String deptName, Long parentId);

    int hasChildByDeptId(Long deptId);

    int checkDeptExistUser(Long deptId);
}
