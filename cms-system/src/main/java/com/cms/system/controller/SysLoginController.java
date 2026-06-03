package com.cms.system.controller;

import cn.hutool.crypto.SecureUtil;
import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.common.utils.SecurityUtils;
import com.cms.common.utils.StringUtils;
import com.cms.framework.security.LoginBody;
import com.cms.system.domain.SysLogininfor;
import com.cms.system.domain.SysMenu;
import com.cms.system.domain.SysUser;
import com.cms.system.service.ISysMenuService;
import com.cms.system.service.ISysUserService;
import com.cms.system.mapper.SysLogininforMapper;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import org.noear.solon.annotation.Get;
import org.noear.solon.core.handle.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SysLoginController extends BaseController {

    @Inject
    private ISysUserService userService;

    @Inject
    private ISysMenuService menuService;

    @Inject
    private SysLogininforMapper logininforMapper;

    @Post
    @Mapping("/login")
    public AjaxResult login(Context ctx, LoginBody loginBody) {
        String username = loginBody.getUsername();
        String password = loginBody.getPassword();

        // 记录登录日志
        SysLogininfor logininfor = new SysLogininfor();
        logininfor.setUserName(username);
        logininfor.setIpaddr(ctx.realIp());
        logininfor.setStatus("0");

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            logininfor.setStatus("1");
            logininfor.setMsg("用户名或密码不能为空");
            logininforMapper.insert(logininfor);
            return error("用户名或密码不能为空");
        }

        // 查找用户
        SysUser user = userService.selectUserByUserName(username);
        if (user == null) {
            logininfor.setStatus("1");
            logininfor.setMsg("用户不存在");
            logininforMapper.insert(logininfor);
            return error("用户不存在");
        }

        if ("1".equals(user.getStatus())) {
            logininfor.setStatus("1");
            logininfor.setMsg("用户已停用");
            logininforMapper.insert(logininfor);
            return error("用户已停用");
        }

        // 验证密码
        if (!SecureUtil.md5(password).equals(user.getPassword())) {
            logininfor.setStatus("1");
            logininfor.setMsg("密码错误");
            logininforMapper.insert(logininfor);
            return error("密码错误");
        }

        logininfor.setMsg("登录成功");
        logininforMapper.insert(logininfor);

        // 生成Token
        String token = SecurityUtils.createToken(user.getUserId(), user.getUserName());

        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("user", user);

        return success("登录成功", result);
    }

    @Get
    @Mapping("/getInfo")
    public AjaxResult getInfo(Context ctx) {
        Long userId = ctx.attr("userId");
        SysUser user = userService.selectUserById(userId);

        Map<String, Object> result = new HashMap<>();
        result.put("user", user);

        // 获取角色权限
        List<String> roles = menuService.selectMenuPermsByUserId(userId);
        result.put("roles", roles);

        // 获取权限标识
        List<String> perms = menuService.selectMenuPermsByUserId(userId);
        result.put("permissions", perms);

        return success(result);
    }

    @Get
    @Mapping("/getRouters")
    public AjaxResult getRouters(Context ctx) {
        Long userId = ctx.attr("userId");
        List<SysMenu> menus = menuService.selectMenusByUserId(userId);
        return success(menus);
    }

    @Post
    @Mapping("/logout")
    public AjaxResult logout() {
        return success("退出成功");
    }
}
