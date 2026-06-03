package com.cms.framework.aspect;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.enums.BusinessType;
import com.cms.system.domain.SysOperLog;
import com.cms.system.mapper.SysOperLogMapper;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.aspect.Interceptor;
import org.noear.solon.core.aspect.Invocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 操作日志记录切面
 */
@Component
public class LogAspect implements Interceptor {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Inject
    private SysOperLogMapper operLogMapper;

    @Override
    public Object doIntercept(Invocation inv) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = null;
        Exception exception = null;

        try {
            result = inv.invoke();
        } catch (Exception e) {
            exception = e;
            throw e;
        } finally {
            try {
                long costTime = System.currentTimeMillis() - startTime;
                saveLog(inv, result, exception, costTime);
            } catch (Exception e) {
                log.error("保存操作日志失败", e);
            }
        }

        return result;
    }

    private void saveLog(Invocation inv, Object result, Exception exception, long costTime) {
        SysOperLog operLog = new SysOperLog();
        operLog.setTitle("系统操作");
        operLog.setBusinessType(BusinessType.OTHER.ordinal());

        if (exception != null) {
            operLog.setStatus(1);
            operLog.setErrorMsg(exception.getMessage());
        } else {
            operLog.setStatus(0);
        }

        operLog.setMethod(inv.method().getMethod().getName());
        operLog.setOperTime(new Date());
        operLog.setCostTime(costTime);

        // 获取当前用户信息
        try {
            org.noear.solon.core.handle.Context ctx = org.noear.solon.core.handle.Context.current();
            operLog.setOperName(ctx.attr("username"));
            operLog.setOperIp(ctx.realIp());
            operLog.setOperUrl(ctx.pathNew());
        } catch (Exception ignored) {
        }

        operLogMapper.insert(operLog);
    }
}
