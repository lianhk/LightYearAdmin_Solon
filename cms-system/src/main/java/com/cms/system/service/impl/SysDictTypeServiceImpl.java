package com.cms.system.service.impl;

import com.cms.system.domain.SysDictType;
import com.cms.system.mapper.SysDictTypeMapper;
import com.cms.system.service.ISysDictTypeService;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.List;

@Component
public class SysDictTypeServiceImpl implements ISysDictTypeService {

    @Inject
    private SysDictTypeMapper dictTypeMapper;

    @Override
    public List<SysDictType> selectDictTypeList(SysDictType dictType) {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    @Override
    public SysDictType selectDictTypeById(Long dictId) {
        return dictTypeMapper.selectById(dictId);
    }

    @Override
    public int insertDictType(SysDictType dictType) {
        return dictTypeMapper.insert(dictType);
    }

    @Override
    public int updateDictType(SysDictType dictType) {
        return dictTypeMapper.update(dictType);
    }

    @Override
    public int deleteDictTypeByIds(Long[] dictIds) {
        int count = 0;
        for (Long dictId : dictIds) {
            count += dictTypeMapper.deleteById(dictId);
        }
        return count;
    }

    @Override
    public boolean checkDictTypeUnique(String dictType) {
        return dictTypeMapper.checkDictTypeUnique(dictType) == 0;
    }
}
