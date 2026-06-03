package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Get;

import java.lang.management.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 服务监控
 */
@Controller
@Mapping("/monitor/server")
public class SysServerController extends BaseController {

    @Get
    @Mapping
    public AjaxResult getInfo() {
        Map<String, Object> result = new HashMap<>();
        result.put("cpu", getCpuInfo());
        result.put("mem", getMemInfo());
        result.put("jvm", getJvmInfo());
        result.put("sys", getSysInfo());
        result.put("sysFiles", getSysFiles());
        return success(result);
    }

    private Map<String, Object> getCpuInfo() {
        Map<String, Object> info = new HashMap<>();
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        info.put("cpuNum", Runtime.getRuntime().availableProcessors());
        info.put("total", 100.0);
        double load = osBean.getSystemLoadAverage();
        info.put("used", load < 0 ? 0 : Math.min(load * 100, 100));
        info.put("sys", 0);
        info.put("free", load < 0 ? 100 : Math.max(100 - load * 100, 0));
        return info;
    }

    private Map<String, Object> getMemInfo() {
        Map<String, Object> info = new HashMap<>();
        com.sun.management.OperatingSystemMXBean osBean =
            (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long total = osBean.getTotalPhysicalMemorySize();
        long free = osBean.getFreePhysicalMemorySize();
        long used = total - free;
        info.put("total", formatBytes(total));
        info.put("used", formatBytes(used));
        info.put("free", formatBytes(free));
        info.put("usage", total > 0 ? new DecimalFormat("#.##").format(used * 100.0 / total) : "0");
        return info;
    }

    private Map<String, Object> getJvmInfo() {
        Map<String, Object> info = new HashMap<>();
        Runtime rt = Runtime.getRuntime();
        long total = rt.totalMemory();
        long free = rt.freeMemory();
        long max = rt.maxMemory();
        info.put("total", formatBytes(total));
        info.put("max", formatBytes(max));
        info.put("free", formatBytes(free));
        info.put("used", formatBytes(total - free));
        info.put("usage", new DecimalFormat("#.##").format((total - free) * 100.0 / total));
        RuntimeMXBean rtBean = ManagementFactory.getRuntimeMXBean();
        info.put("name", System.getProperty("java.vm.name"));
        info.put("version", System.getProperty("java.version"));
        info.put("home", System.getProperty("java.home"));
        info.put("startTime", new Date(rtBean.getStartTime()));
        info.put("runTime", formatRunTime(rtBean.getUptime()));
        return info;
    }

    private Map<String, Object> getSysInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("computerName", System.getenv().getOrDefault("COMPUTERNAME", "Unknown"));
        info.put("osName", System.getProperty("os.name"));
        info.put("osArch", System.getProperty("os.arch"));
        info.put("userDir", System.getProperty("user.dir"));
        return info;
    }

    private List<Map<String, Object>> getSysFiles() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (java.io.File root : java.io.File.listRoots()) {
            Map<String, Object> info = new HashMap<>();
            info.put("dirName", root.getPath());
            info.put("sysTypeName", root.getPath().replace("\\", ""));
            info.put("typeName", root.getPath());
            info.put("total", formatBytes(root.getTotalSpace()));
            info.put("free", formatBytes(root.getFreeSpace()));
            info.put("used", formatBytes(root.getTotalSpace() - root.getFreeSpace()));
            long t = root.getTotalSpace();
            info.put("usage", t > 0 ? new DecimalFormat("#.##").format((t - root.getFreeSpace()) * 100.0 / t) : "0");
            list.add(info);
        }
        return list;
    }

    private String formatBytes(long bytes) {
        if (bytes < 1024) return bytes + " B";
        double k = bytes / 1024.0;
        if (k < 1024) return new DecimalFormat("#.##").format(k) + " KB";
        double m = k / 1024;
        if (m < 1024) return new DecimalFormat("#.##").format(m) + " MB";
        double g = m / 1024;
        return new DecimalFormat("#.##").format(g) + " GB";
    }

    private String formatRunTime(long ms) {
        long days = ms / (1000 * 60 * 60 * 24);
        long hours = (ms % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (ms % (1000 * 60 * 60)) / (1000 * 60);
        return days + "天" + hours + "小时" + minutes + "分钟";
    }
}
