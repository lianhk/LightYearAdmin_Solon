package com.cms.framework.config;

import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;

/**
 * 资源映射配置 - 静态文件处理
 */
@Component(index = 2)
public class ResourcesConfig implements Filter {

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        String uri = ctx.pathNew();

        // 静态资源请求直接放行
        if (uri.startsWith("/static/")) {
            chain.doFilter(ctx);
            return;
        }

        // 根路径默认跳转到登录页
        if ("/".equals(uri)) {
            ctx.redirect("/static/login.html");
            return;
        }

        chain.doFilter(ctx);
    }
}
