package com.cms.common.core;

import com.cms.common.utils.StringUtils;

import java.util.List;

/**
 * 基础控制器
 */
public class BaseController {

    /**
     * 获取分页参数
     */
    protected PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        String pageNum = org.noear.solon.core.handle.Context.current().param("pageNum");
        String pageSize = org.noear.solon.core.handle.Context.current().param("pageSize");
        String orderByColumn = org.noear.solon.core.handle.Context.current().param("orderByColumn");
        String isAsc = org.noear.solon.core.handle.Context.current().param("isAsc");

        pageDomain.setPageNum(StringUtils.isNotEmpty(pageNum) ? Integer.parseInt(pageNum) : 1);
        pageDomain.setPageSize(StringUtils.isNotEmpty(pageSize) ? Integer.parseInt(pageSize) : 10);
        pageDomain.setOrderByColumn(orderByColumn);
        pageDomain.setIsAsc(isAsc);

        return pageDomain;
    }

    /**
     * 封装分页数据
     */
    protected TableDataInfo getDataTable(List<?> list, long total) {
        return new TableDataInfo(list, total);
    }

    /**
     * 返回成功
     */
    protected AjaxResult success() {
        return AjaxResult.success();
    }

    protected AjaxResult success(String msg) {
        return AjaxResult.success(msg);
    }

    protected AjaxResult success(Object data) {
        return AjaxResult.success(data);
    }

    /**
     * 返回失败
     */
    protected AjaxResult error() {
        return AjaxResult.error();
    }

    protected AjaxResult error(String msg) {
        return AjaxResult.error(msg);
    }

    /**
     * 返回警告
     */
    protected AjaxResult warn(String msg) {
        return AjaxResult.warn(msg);
    }
}
