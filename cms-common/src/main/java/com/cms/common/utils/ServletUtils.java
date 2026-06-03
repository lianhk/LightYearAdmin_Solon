package com.cms.common.utils;

import org.noear.solon.core.handle.Context;

/**
 * Servlet工具类
 */
public class ServletUtils {

    /**
     * 获取当前请求上下文
     */
    public static Context getRequest() {
        return Context.current();
    }

    /**
     * 获取请求参数
     */
    public static String getParameter(String name) {
        return getRequest().param(name);
    }

    /**
     * 获取整数参数
     */
    public static Integer getParameterToInt(String name) {
        String value = getRequest().param(name);
        if (StringUtils.isNotBlank(value)) {
            return Integer.parseInt(value);
        }
        return null;
    }

    /**
     * 获取Header
     */
    public static String getHeader(String name) {
        return getRequest().header(name);
    }
}
