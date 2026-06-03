package com.cms.common.exception;

import com.cms.common.core.AjaxResult;
import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 全局异常处理器
 */
@Component
public class GlobalExceptionHandler implements Filter {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        try {
            chain.doFilter(ctx);
        } catch (ServiceException e) {
            log.error("业务异常: {}", e.getMessage());
            ctx.contentType("application/json;charset=UTF-8");
            ctx.output(AjaxResult.error(e.getMessage()).toString());
        } catch (Exception e) {
            log.error("系统异常: ", e);
            ctx.contentType("application/json;charset=UTF-8");
            ctx.output(AjaxResult.error("服务器内部错误").toString());
        }
    }
}
