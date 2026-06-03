package com.cms.system.service;

import com.cms.system.domain.SysDictData;
import java.util.List;

public interface ISysDictDataService {

    List<SysDictData> selectDictDataList(SysDictData dictData);

    List<SysDictData> selectDictDataByType(String dictType);

    SysDictData selectDictDataById(Long dictCode);

    int insertDictData(SysDictData dictData);

    int updateDictData(SysDictData dictData);

    int deleteDictDataByIds(Long[] dictCodes);
}
