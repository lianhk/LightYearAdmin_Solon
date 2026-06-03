package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.system.domain.SysPost;
import com.cms.system.service.ISysPostService;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import org.noear.solon.annotation.Get;
import org.noear.solon.core.handle.Context;

import java.util.List;

@Controller
@Mapping("/system/post")
public class SysPostController extends BaseController {

    @Inject
    private ISysPostService postService;

    @Get
    @Mapping
    public void page(Context ctx) {
        SysPost search = new SysPost();
        String postName = ctx.param("postName");
        if (postName != null && !postName.isEmpty()) search.setPostName(postName);
        String postCode = ctx.param("postCode");
        if (postCode != null && !postCode.isEmpty()) search.setPostCode(postCode);
        String status = ctx.param("status");
        if (status != null && !status.isEmpty()) search.setStatus(status);
        ctx.attrSet("list", postService.selectPostList(search));
        ctx.render("post.html");
    }

    @Post
    @Mapping("/add")
    public AjaxResult add(Context ctx, SysPost post) {
        if (!postService.checkPostNameUnique(post.getPostName())) {
            return error("岗位名称已存在");
        }
        if (!postService.checkPostCodeUnique(post.getPostCode())) {
            return error("岗位编码已存在");
        }
        post.setCreateBy(ctx.session("userName"));
        return postService.insertPost(post) > 0 ? success() : error();
    }

    @Post
    @Mapping("/edit")
    public AjaxResult edit(Context ctx, SysPost post) {
        post.setUpdateBy(ctx.session("userName"));
        return postService.updatePost(post) > 0 ? success() : error();
    }

    @Post
    @Mapping("/delete")
    public AjaxResult remove(Context ctx, Long[] postIds) {
        return postService.deletePostByIds(postIds) > 0 ? success() : error();
    }
}
