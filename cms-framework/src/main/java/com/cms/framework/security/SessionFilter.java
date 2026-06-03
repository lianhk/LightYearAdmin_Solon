package com.cms.framework.security;

import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Session认证过滤器
 */
@Component(index = 1)
public class SessionFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(SessionFilter.class);

    /** 不需要认证的路径 */
    private static final String[] EXCLUDE_URLS = {
            "/login", "/captcha", "/static/", "/lib/"
    };

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        String uri = ctx.pathNew();

        // 排除静态资源和登录相关
        if (isExcluded(uri) || uri.equals("/") || uri.startsWith("/static/")) {
            chain.doFilter(ctx);
            return;
        }

        // 根路径重定向到登录
        if ("/".equals(uri)) {
            ctx.redirect("/login");
            return;
        }

        // 检查session中是否有用户
        Object userId = ctx.session("userId");
        if (userId == null) {
            // AJAX请求返回JSON
            if (isAjax(ctx)) {
                ctx.contentType("application/json;charset=UTF-8");
                ctx.output("{\"code\":401,\"msg\":\"未登录\"}");
            } else {
                ctx.redirect("/login");
            }
            return;
        }

        chain.doFilter(ctx);
    }

    private boolean isExcluded(String uri) {
        for (String pattern : EXCLUDE_URLS) {
            if (uri.contains(pattern) || uri.startsWith(pattern)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAjax(Context ctx) {
        String requested = ctx.header("X-Requested-With");
        return "XMLHttpRequest".equals(requested);
    }
}
