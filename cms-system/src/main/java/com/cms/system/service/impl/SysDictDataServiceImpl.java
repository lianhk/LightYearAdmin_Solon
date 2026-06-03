package com.cms.system.service.impl;

import com.cms.system.domain.SysDictData;
import com.cms.system.mapper.SysDictDataMapper;
import com.cms.system.service.ISysDictDataService;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.List;

@Component
public class SysDictDataServiceImpl implements ISysDictDataService {

    @Inject
    private SysDictDataMapper dictDataMapper;

    @Override
    public List<SysDictData> selectDictDataList(SysDictData dictData) {
        return dictDataMapper.selectDictDataList(dictData);
    }

    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        return dictDataMapper.selectDictDataByType(dictType);
    }

    @Override
    public SysDictData selectDictDataById(Long dictCode) {
        return dictDataMapper.selectById(dictCode);
    }

    @Override
    public int insertDictData(SysDictData dictData) {
        return dictDataMapper.insert(dictData);
    }

    @Override
    public int updateDictData(SysDictData dictData) {
        return dictDataMapper.update(dictData);
    }

    @Override
    public int deleteDictDataByIds(Long[] dictCodes) {
        int count = 0;
        for (Long dictCode : dictCodes) {
            count += dictDataMapper.deleteById(dictCode);
        }
        return count;
    }
}
