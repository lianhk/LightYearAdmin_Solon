package com.cms.common.core;

import java.util.HashMap;

/**
 * 统一响应结果
 */
public class AjaxResult extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public static final String CODE_TAG = "code";
    public static final String MSG_TAG = "msg";
    public static final String DATA_TAG = "data";

    public AjaxResult() {
    }

    public AjaxResult(int code, String msg) {
        put(CODE_TAG, code);
        put(MSG_TAG, msg);
    }

    public AjaxResult(int code, String msg, Object data) {
        put(CODE_TAG, code);
        put(MSG_TAG, msg);
        if (data != null) {
            put(DATA_TAG, data);
        }
    }

    public static AjaxResult success() {
        return new AjaxResult(0, "操作成功");
    }

    public static AjaxResult success(String msg) {
        return new AjaxResult(0, msg);
    }

    public static AjaxResult success(Object data) {
        AjaxResult result = new AjaxResult(0, "操作成功");
        result.put(DATA_TAG, data);
        return result;
    }

    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(0, msg, data);
    }

    public static AjaxResult error() {
        return new AjaxResult(1, "操作失败");
    }

    public static AjaxResult error(String msg) {
        return new AjaxResult(1, msg);
    }

    public static AjaxResult error(int code, String msg) {
        return new AjaxResult(code, msg);
    }

    public static AjaxResult warn(String msg) {
        return new AjaxResult(601, msg);
    }
}
