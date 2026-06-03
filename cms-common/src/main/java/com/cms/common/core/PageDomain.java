package com.cms.common.core;

/**
 * 分页参数
 */
public class PageDomain {

    /** 当前页 */
    private Integer pageNum;

    /** 每页条数 */
    private Integer pageSize;

    /** 排序字段 */
    private String orderByColumn;

    /** 排序方向 asc/desc */
    private String isAsc;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(String isAsc) {
        this.isAsc = isAsc;
    }
}
