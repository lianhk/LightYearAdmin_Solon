package com.cms.system.mapper;

import com.cms.system.domain.SysDictData;
import org.beetl.sql.mapper.BaseMapper;
import org.beetl.sql.mapper.annotation.SqlResource;

import java.util.List;

@SqlResource("sysDictData")
public interface SysDictDataMapper extends BaseMapper<SysDictData> {

    List<SysDictData> selectDictDataList(SysDictData dictData);

    List<SysDictData> selectDictDataByType(String dictType);
}
