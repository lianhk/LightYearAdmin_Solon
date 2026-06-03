package com.cms.framework.security;

import com.cms.common.utils.SecurityUtils;
import com.cms.common.utils.StringUtils;
import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JWT认证过滤器
 */
@Component(index = 1)
public class JwtFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);

    /** 不需要认证的路径 */
    private static final String[] EXCLUDE_URLS = {
            "/login",
            "/captcha",
            "/static/",
            "/.png",
            "/.jpg",
            "/.css",
            "/.js",
            "/.ico",
            "/.html"
    };

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        String uri = ctx.pathNew();

        // 检查是否在排除列表中
        if (isExcluded(uri)) {
            chain.doFilter(ctx);
            return;
        }

        // OPTIONS请求直接放行
        if ("OPTIONS".equals(ctx.method())) {
            chain.doFilter(ctx);
            return;
        }

        // 获取Token
        String token = ctx.header("Authorization");
        if (StringUtils.isBlank(token)) {
            ctx.contentType("application/json;charset=UTF-8");
            ctx.status(401);
            ctx.output("{\"code\":401,\"msg\":\"未登录或登录已过期\"}");
            return;
        }

        // 移除Bearer前缀
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // 验证Token
        if (!SecurityUtils.isValid(token)) {
            ctx.contentType("application/json;charset=UTF-8");
            ctx.status(401);
            ctx.output("{\"code\":401,\"msg\":\"Token无效或已过期\"}");
            return;
        }

        // 将用户信息设置到上下文
        Long userId = SecurityUtils.getUserId(token);
        String username = SecurityUtils.getUsername(token);
        ctx.attrSet("userId", userId);
        ctx.attrSet("username", username);

        chain.doFilter(ctx);
    }

    private boolean isExcluded(String uri) {
        for (String pattern : EXCLUDE_URLS) {
            if (uri.contains(pattern)) {
                return true;
            }
        }
        return false;
    }
}
