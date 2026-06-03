package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.system.domain.SysUser;
import com.cms.system.service.ISysUserService;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import org.noear.solon.annotation.Get;
import org.noear.solon.core.handle.Context;

import java.util.List;

@Controller
@Mapping("/system/user")
public class SysUserController extends BaseController {

    @Inject
    private ISysUserService userService;

    @Get
    @Mapping
    public void page(Context ctx) {
        SysUser search = new SysUser();
        String userName = ctx.param("userName");
        if (userName != null && !userName.isEmpty()) search.setUserName(userName);
        String status = ctx.param("status");
        if (status != null && !status.isEmpty()) search.setStatus(status);
        String phoneNumber = ctx.param("phoneNumber");
        if (phoneNumber != null && !phoneNumber.isEmpty()) search.setPhoneNumber(phoneNumber);
        ctx.attrSet("list", userService.selectUserList(search));
        ctx.render("user.html");
    }

    @Get
    @Mapping("/detail")
    public AjaxResult detail(Context ctx) {
        Long id = Long.parseLong(ctx.param("id"));
        SysUser u = userService.selectUserById(id);
        u.setPassword(null);
        return success(u);
    }

    @Post
    @Mapping("/add")
    public AjaxResult add(Context ctx, SysUser user) {
        if (!userService.checkUserNameUnique(user.getUserName())) {
            return error("用户名已存在");
        }
        user.setCreateBy(ctx.session("userName"));
        int rows = userService.insertUser(user);
        if (user.getRoleIds() != null && user.getRoleIds().length > 0) {
            userService.insertUserRole(user.getUserId(), user.getRoleIds());
        }
        return rows > 0 ? success() : error();
    }

    @Post
    @Mapping("/edit")
    public AjaxResult edit(Context ctx, SysUser user) {
        user.setUpdateBy(ctx.session("userName"));
        int rows = userService.updateUser(user);
        if (user.getRoleIds() != null) {
            userService.insertUserRole(user.getUserId(), user.getRoleIds());
        }
        return rows > 0 ? success() : error();
    }

    @Post
    @Mapping("/delete")
    public AjaxResult remove(Context ctx, Long[] userIds) {
        return userService.deleteUserByIds(userIds) > 0 ? success() : error();
    }

    @Post
    @Mapping("/resetPwd")
    public AjaxResult resetPwd(Context ctx, SysUser user) {
        user.setUpdateBy(ctx.session("userName"));
        return userService.resetPwd(user) > 0 ? success() : error();
    }
}
