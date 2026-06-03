package com.cms.framework.web;

import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;

/**
 * CORS跨域过滤器
 */
@Component(index = 0)
public class CorsFilter implements Filter {

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        ctx.headerSet("Access-Control-Allow-Origin", "*");
        ctx.headerSet("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        ctx.headerSet("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");
        ctx.headerSet("Access-Control-Max-Age", "3600");

        if ("OPTIONS".equals(ctx.method())) {
            ctx.status(200);
            return;
        }

        chain.doFilter(ctx);
    }
}
