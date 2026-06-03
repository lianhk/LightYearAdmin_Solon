package com.cms.system.controller;

import cn.hutool.crypto.SecureUtil;
import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.system.domain.SysUser;
import com.cms.system.service.ISysUserService;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Put;
import org.noear.solon.core.handle.Context;

import java.util.HashMap;
import java.util.Map;

@Controller
@Mapping("/system/user/profile")
public class SysProfileController extends BaseController {

    @Inject
    private ISysUserService userService;

    @Get
    @Mapping
    public AjaxResult profile(Context ctx) {
        Long userId = ctx.attr("userId");
        SysUser user = userService.selectUserById(userId);
        user.setPassword(null);
        return success(user);
    }

    @Put
    @Mapping
    public AjaxResult updateProfile(Context ctx, SysUser user) {
        Long userId = ctx.attr("userId");
        user.setUserId(userId);
        user.setUpdateBy(ctx.attr("username"));
        return userService.updateUserProfile(user) > 0 ? success() : error();
    }

    @Put
    @Mapping("/updatePwd")
    public AjaxResult updatePwd(Context ctx, String oldPassword, String newPassword) {
        Long userId = ctx.attr("userId");
        SysUser user = userService.selectUserById(userId);

        if (!SecureUtil.md5(oldPassword).equals(user.getPassword())) {
            return error("原密码错误");
        }

        user.setPassword(SecureUtil.md5(newPassword));
        user.setUpdateBy(ctx.attr("username"));
        return userService.resetPwd(user) > 0 ? success() : error();
    }
}
