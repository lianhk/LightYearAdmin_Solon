package com.cms.system.service;

import com.cms.system.domain.SysDictType;
import java.util.List;

public interface ISysDictTypeService {

    List<SysDictType> selectDictTypeList(SysDictType dictType);

    SysDictType selectDictTypeById(Long dictId);

    int insertDictType(SysDictType dictType);

    int updateDictType(SysDictType dictType);

    int deleteDictTypeByIds(Long[] dictIds);

    boolean checkDictTypeUnique(String dictType);
}
