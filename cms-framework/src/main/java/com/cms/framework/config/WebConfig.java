package com.cms.framework.config;

import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;

/**
 * Web配置 - 静态文件MIME类型处理
 */
@Component(index = 3)
public class WebConfig implements Filter {

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        String uri = ctx.pathNew();

        if (uri.endsWith(".css")) {
            ctx.contentType("text/css;charset=UTF-8");
        } else if (uri.endsWith(".js")) {
            ctx.contentType("application/javascript;charset=UTF-8");
        } else if (uri.endsWith(".html")) {
            ctx.contentType("text/html;charset=UTF-8");
        }

        chain.doFilter(ctx);
    }
}
