package com.cms.system.mapper;

import com.cms.system.domain.SysDictType;
import org.beetl.sql.mapper.BaseMapper;
import org.beetl.sql.mapper.annotation.SqlResource;

import java.util.List;

@SqlResource("sysDictType")
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {

    List<SysDictType> selectDictTypeList(SysDictType dictType);

    int checkDictTypeUnique(String dictType);
}
