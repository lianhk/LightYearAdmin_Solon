package com.cms.system.controller;

import cn.hutool.crypto.SecureUtil;
import com.cms.common.utils.CaptchaUtils;
import com.cms.common.utils.StringUtils;
import com.cms.framework.security.LoginBody;
import com.cms.system.domain.*;
import com.cms.system.service.*;
import com.cms.system.mapper.SysLogininforMapper;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.*;

import java.util.*;

@Controller
public class SysLoginController {

    @Inject
    private ISysUserService userService;

    @Inject
    private ISysMenuService menuService;

    @Inject
    private SysLogininforMapper logininforMapper;

    /** 登录页 */
    @Get
    @Mapping("/login")
    public void loginPage(Context ctx) {
        if (ctx.session("userId") != null) {
            ctx.redirect("/index");
            return;
        }
        ctx.render("login.html");
    }

    /** 验证码图片 */
    @Get
    @Mapping("/captcha")
    public void captcha(Context ctx) throws Exception {
        CaptchaUtils.CaptchaResult cr = CaptchaUtils.generate();
        // 验证码存在session中用于校验
        ctx.sessionSet("captchaCode", CaptchaUtils.getCode(cr.getUuid()));
        String b64 = cr.getBase64();
        byte[] imgBytes = Base64.getDecoder().decode(b64);
        ctx.contentType("image/png");
        ctx.output(imgBytes);
    }

    /** 登录处理 */
    @Post
    @Mapping("/login")
    public void doLogin(Context ctx, LoginBody loginBody) {
        String username = loginBody.getUsername();
        String password = loginBody.getPassword();
        String code = loginBody.getCode();

        // 验证码校验
        String captchaCode = ctx.session("captchaCode");
        if (captchaCode == null || !captchaCode.equalsIgnoreCase(code != null ? code : "")) {
            ctx.attrSet("error", "验证码错误");
            ctx.attrSet("username", username);
            ctx.render("login.html");
            return;
        }
        ctx.sessionRemove("captchaCode");

        // 记录登录日志
        SysLogininfor log = new SysLogininfor();
        log.setUserName(username);
        log.setIpaddr(ctx.realIp());

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            log.setStatus("1"); log.setMsg("用户名或密码不能为空");
            logininforMapper.insert(log);
            ctx.attrSet("error", "用户名或密码不能为空");
            ctx.render("login.html");
            return;
        }

        SysUser user = userService.selectUserByUserName(username);
        if (user == null) {
            log.setStatus("1"); log.setMsg("用户不存在");
            logininforMapper.insert(log);
            ctx.attrSet("error", "用户不存在");
            ctx.render("login.html");
            return;
        }
        if ("1".equals(user.getStatus())) {
            log.setStatus("1"); log.setMsg("用户已停用");
            logininforMapper.insert(log);
            ctx.attrSet("error", "用户已停用");
            ctx.render("login.html");
            return;
        }
        if (!SecureUtil.md5(password).equals(user.getPassword())) {
            log.setStatus("1"); log.setMsg("密码错误");
            logininforMapper.insert(log);
            ctx.attrSet("error", "密码错误");
            ctx.attrSet("username", username);
            ctx.render("login.html");
            return;
        }

        log.setStatus("0"); log.setMsg("登录成功");
        logininforMapper.insert(log);

        // 设置Session
        ctx.sessionSet("userId", user.getUserId());
        ctx.sessionSet("userName", user.getUserName());
        ctx.sessionSet("nickName", user.getNickName());
        ctx.sessionSet("deptName", user.getDeptName());

        // 记录在线用户
        SysOnlineController.addSession(ctx.sessionId(), user.getUserId(), user.getUserName(), ctx.realIp());

        ctx.redirect("/index");
    }

    /** 主页面 */
    @Get
    @Mapping("/index")
    public void index(Context ctx) {
        Long userId = ctx.session("userId");
        if (userId == null) {
            ctx.redirect("/login");
            return;
        }
        SysUser user = userService.selectUserById(userId);
        List<SysMenu> menus = menuService.selectMenusByUserId(userId);
        ctx.attrSet("user", user);
        ctx.attrSet("menus", menus);
        ctx.render("index.html");
    }

    /** 个人中心 */
    @Get
    @Mapping("/profile")
    public void profile(Context ctx) {
        Long userId = ctx.session("userId");
        SysUser user = userService.selectUserById(userId);
        ctx.attrSet("user", user);
        ctx.render("profile.html");
    }

    /** 更新个人信息 */
    @Post
    @Mapping("/profile")
    public void updateProfile(Context ctx, SysUser user) {
        Long userId = ctx.session("userId");
        user.setUserId(userId);
        user.setUpdateBy(ctx.session("userName"));
        userService.updateUserProfile(user);
        ctx.sessionSet("nickName", user.getNickName());
        ctx.redirect("/profile");
    }

    /** 修改密码 */
    @Post
    @Mapping("/profile/pwd")
    public void updatePwd(Context ctx, String oldPassword, String newPassword) {
        Long userId = ctx.session("userId");
        SysUser user = userService.selectUserById(userId);
        if (!SecureUtil.md5(oldPassword).equals(user.getPassword())) {
            ctx.attrSet("pwdError", "原密码错误");
            ctx.attrSet("user", user);
            ctx.render("profile.html");
            return;
        }
        user.setPassword(SecureUtil.md5(newPassword));
        user.setUpdateBy(ctx.session("userName"));
        userService.resetPwd(user);
        ctx.attrSet("pwdOk", "密码修改成功");
        ctx.attrSet("user", user);
        ctx.render("profile.html");
    }

    /** 首页仪表盘 */
    @Get
    @Mapping("/welcome")
    public void welcome(Context ctx) {
        Long userId = ctx.session("userId");
        if (userId == null) { ctx.redirect("/login"); return; }
        SysUser user = userService.selectUserById(userId);
        ctx.attrSet("user", user);
        ctx.render("welcome.html");
    }

    /** 退出 */
    @Get
    @Mapping("/logout")
    public void logout(Context ctx) {
        SysOnlineController.removeSession(ctx.sessionId());
        ctx.sessionClear();
        ctx.redirect("/login");
    }
}
