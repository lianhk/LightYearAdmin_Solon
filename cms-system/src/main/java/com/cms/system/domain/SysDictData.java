package com.cms.system.domain;

import com.cms.common.core.BaseEntity;

/**
 * 字典数据
 */
public class SysDictData extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long dictCode;
    private Integer dictSort;
    private String dictLabel;
    private String dictValue;
    private String dictType;
    private String cssClass;
    private String listClass;
    private String isDefault;
    private String status;

    public Long getDictCode() { return dictCode; }
    public void setDictCode(Long dictCode) { this.dictCode = dictCode; }

    public Integer getDictSort() { return dictSort; }
    public void setDictSort(Integer dictSort) { this.dictSort = dictSort; }

    public String getDictLabel() { return dictLabel; }
    public void setDictLabel(String dictLabel) { this.dictLabel = dictLabel; }

    public String getDictValue() { return dictValue; }
    public void setDictValue(String dictValue) { this.dictValue = dictValue; }

    public String getDictType() { return dictType; }
    public void setDictType(String dictType) { this.dictType = dictType; }

    public String getCssClass() { return cssClass; }
    public void setCssClass(String cssClass) { this.cssClass = cssClass; }

    public String getListClass() { return listClass; }
    public void setListClass(String listClass) { this.listClass = listClass; }

    public String getIsDefault() { return isDefault; }
    public void setIsDefault(String isDefault) { this.isDefault = isDefault; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
