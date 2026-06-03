package com.cms.system.service.impl;

import com.cms.common.exception.ServiceException;
import com.cms.system.domain.SysDept;
import com.cms.system.mapper.SysDeptMapper;
import com.cms.system.service.ISysDeptService;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SysDeptServiceImpl implements ISysDeptService {

    @Inject
    private SysDeptMapper deptMapper;

    @Override
    public List<SysDept> selectDeptList(SysDept dept) {
        List<SysDept> deptList = deptMapper.selectDeptList(dept);
        return buildDeptTree(deptList);
    }

    @Override
    public SysDept selectDeptById(Long deptId) {
        return deptMapper.selectById(deptId);
    }

    @Override
    public int insertDept(SysDept dept) {
        SysDept parent = deptMapper.selectById(dept.getParentId());
        if (parent != null) {
            dept.setAncestors(parent.getAncestors() + "," + dept.getParentId());
        } else {
            dept.setAncestors("0");
        }
        return deptMapper.insert(dept);
    }

    @Override
    public int updateDept(SysDept dept) {
        SysDept parent = deptMapper.selectById(dept.getParentId());
        if (parent != null) {
            dept.setAncestors(parent.getAncestors() + "," + dept.getParentId());
        } else {
            dept.setAncestors("0");
        }
        return deptMapper.update(dept);
    }

    @Override
    public int deleteDeptById(Long deptId) {
        if (hasChildByDeptId(deptId)) {
            throw new ServiceException("存在下级部门,不允许删除");
        }
        if (checkDeptExistUser(deptId)) {
            throw new ServiceException("部门存在用户,不允许删除");
        }
        return deptMapper.deleteById(deptId);
    }

    @Override
    public boolean hasChildByDeptId(Long deptId) {
        return deptMapper.hasChildByDeptId(deptId) > 0;
    }

    @Override
    public boolean checkDeptExistUser(Long deptId) {
        return deptMapper.checkDeptExistUser(deptId) > 0;
    }

    @Override
    public boolean checkDeptNameUnique(String deptName, Long parentId) {
        return deptMapper.checkDeptNameUnique(deptName, parentId) == 0;
    }

    private List<SysDept> buildDeptTree(List<SysDept> deptList) {
        List<SysDept> returnList = new ArrayList<>();
        Map<Long, SysDept> deptMap = new HashMap<>();
        for (SysDept dept : deptList) {
            deptMap.put(dept.getDeptId(), dept);
        }
        for (SysDept dept : deptList) {
            Long parentId = dept.getParentId();
            if (parentId == null || parentId == 0) {
                returnList.add(dept);
            } else {
                SysDept parent = deptMap.get(parentId);
                if (parent != null) {
                    parent.getChildren().add(dept);
                }
            }
        }
        return returnList;
    }
}
