package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.Context;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@Mapping("/monitor/online")
public class SysOnlineController {

    public static final Map<String, Map<String, Object>> ONLINE = new ConcurrentHashMap<>();
    public static final Set<String> BLACKLIST = ConcurrentHashMap.newKeySet();

    public static void addSession(String sid, Long userId, String userName, String ip) {
        Map<String, Object> m = new HashMap<>();
        m.put("sid", sid);
        m.put("userId", userId);
        m.put("userName", userName);
        m.put("ipaddr", ip);
        m.put("loginTime", new Date());
        ONLINE.put(sid, m);
    }

    public static void removeSession(String sid) {
        ONLINE.remove(sid);
    }

    public static boolean isBlacklisted(String sid) {
        return BLACKLIST.contains(sid);
    }

    @Get
    @Mapping
    public void page(Context ctx) {
        List<Map<String, Object>> list = new ArrayList<>(ONLINE.values());
        ctx.attrSet("onlineList", list);
        ctx.render("online.html");
    }

    @Post
    @Mapping("/kick/{sid}")
    public AjaxResult kick(String sid) {
        ONLINE.remove(sid);
        BLACKLIST.add(sid);
        return AjaxResult.success();
    }
}
