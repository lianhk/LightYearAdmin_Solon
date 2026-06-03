package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.common.core.TableDataInfo;
import com.cms.common.core.UserConstants;
import com.cms.system.domain.SysUser;
import com.cms.system.service.ISysUserService;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Delete;
import org.noear.solon.annotation.Put;
import org.noear.solon.core.handle.Context;

import java.util.List;

@Controller
@Mapping("/system/user")
public class SysUserController extends BaseController {

    @Inject
    private ISysUserService userService;

    @Get
    @Mapping("/list")
    public TableDataInfo list(SysUser user) {
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list, list.size());
    }

    @Get
    @Mapping("/{userId}")
    public AjaxResult getInfo(Long userId) {
        SysUser user = userService.selectUserById(userId);
        user.setPassword(null);
        return success(user);
    }

    @Post
    @Mapping
    public AjaxResult add(SysUser user) {
        if (!userService.checkUserNameUnique(user.getUserName())) {
            return error("用户名已存在");
        }
        user.setCreateBy(getLoginUsername());
        int rows = userService.insertUser(user);
        // 保存角色关联
        if (user.getRoleIds() != null && user.getRoleIds().length > 0) {
            userService.insertUserRole(user.getUserId(), user.getRoleIds());
        }
        return rows > 0 ? success() : error();
    }

    @Put
    @Mapping
    public AjaxResult edit(SysUser user) {
        user.setUpdateBy(getLoginUsername());
        int rows = userService.updateUser(user);
        // 保存角色关联
        if (user.getRoleIds() != null) {
            userService.insertUserRole(user.getUserId(), user.getRoleIds());
        }
        return rows > 0 ? success() : error();
    }

    @Delete
    @Mapping("/{userIds}")
    public AjaxResult remove(Long[] userIds) {
        return userService.deleteUserByIds(userIds) > 0 ? success() : error();
    }

    @Put
    @Mapping("/resetPwd")
    public AjaxResult resetPwd(SysUser user) {
        user.setUpdateBy(getLoginUsername());
        return userService.resetPwd(user) > 0 ? success() : error();
    }

    /**
     * 获取当前登录用户名
     */
    private String getLoginUsername() {
        Context ctx = Context.current();
        return ctx.attr("username");
    }
}
