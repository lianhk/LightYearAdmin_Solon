package com.cms.common.core;

/**
 * 通用常量
 */
public class Constants {

    /** 正常状态 */
    public static final String NORMAL = "0";

    /** 停用状态 */
    public static final String DISABLE = "1";

    /** 菜单类型-目录 */
    public static final String TYPE_DIR = "M";

    /** 菜单类型-菜单 */
    public static final String TYPE_MENU = "C";

    /** 菜单类型-按钮 */
    public static final String TYPE_BUTTON = "F";

    /** Layout组件标识 */
    public static final String LAYOUT = "Layout";

    /** ParentView组件标识 */
    public static final String PARENT_VIEW = "ParentView";

    /** InnerLink组件标识 */
    public static final String INNER_LINK = "InnerLink";

    /** 管理员角色key */
    public static final String ADMIN_ROLE_KEY = "admin";

    /** 验证码redis key */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /** 令牌前缀 */
    public static final String TOKEN_PREFIX = "Bearer ";

    /** 登录用户令牌头 */
    public static final String HEADER_AUTHORIZATION = "Authorization";
}
