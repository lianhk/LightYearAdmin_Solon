package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.common.core.TableDataInfo;
import com.cms.common.utils.SecurityUtils;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.Context;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 在线用户管理
 */
@Controller
@Mapping("/monitor/online")
public class SysOnlineController extends BaseController {

    /** 在线用户记录: tokenHash -> {userId, username, ip, loginTime} */
    public static final Map<String, Map<String, Object>> ONLINE_USERS = new ConcurrentHashMap<>();
    /** 被强退的token黑名单 */
    public static final Set<String> TOKEN_BLACKLIST = ConcurrentHashMap.newKeySet();

    public static void addUser(String token, Long userId, String username, String ip) {
        int hash = Math.abs(token.hashCode());
        Map<String, Object> info = new HashMap<>();
        info.put("tokenId", String.valueOf(hash));
        info.put("tokenHash", hash);
        info.put("userId", userId);
        info.put("userName", username);
        info.put("ipaddr", ip);
        info.put("loginTime", new Date());
        ONLINE_USERS.put(String.valueOf(hash), info);
    }

    public static void removeUser(String token) {
        int hash = Math.abs(token.hashCode());
        ONLINE_USERS.remove(String.valueOf(hash));
    }

    public static boolean isBlacklisted(String token) {
        return TOKEN_BLACKLIST.contains(String.valueOf(Math.abs(token.hashCode())));
    }

    @Get
    @Mapping("/list")
    public TableDataInfo list() {
        List<Map<String, Object>> list = new ArrayList<>(ONLINE_USERS.values());
        list.sort((a, b) -> ((Date) b.get("loginTime")).compareTo((Date) a.get("loginTime")));
        return getDataTable(list, list.size());
    }

    @Delete
    @Mapping("/{tokenHash}")
    public AjaxResult forceLogout(String tokenHash) {
        Map<String, Object> user = ONLINE_USERS.remove(tokenHash);
        if (user != null) {
            TOKEN_BLACKLIST.add(tokenHash);
        }
        return success();
    }
}
