package com.cms.system.controller;

import com.cms.common.core.AjaxResult;
import com.cms.common.core.BaseController;
import com.cms.common.core.TableDataInfo;
import com.cms.system.domain.SysPost;
import com.cms.system.service.ISysPostService;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.Context;

import java.util.List;

@Controller
@Mapping("/system/post")
public class SysPostController extends BaseController {

    @Inject
    private ISysPostService postService;

    @Get
    @Mapping("/list")
    public TableDataInfo list(SysPost post) {
        List<SysPost> list = postService.selectPostList(post);
        return getDataTable(list, list.size());
    }

    @Get
    @Mapping("/all")
    public AjaxResult all() {
        return success(postService.selectPostList(new SysPost()));
    }

    @Get
    @Mapping("/{postId}")
    public AjaxResult getInfo(Long postId) {
        return success(postService.selectPostById(postId));
    }

    @Post
    @Mapping
    public AjaxResult add(SysPost post) {
        if (!postService.checkPostNameUnique(post.getPostName())) {
            return error("岗位名称已存在");
        }
        if (!postService.checkPostCodeUnique(post.getPostCode())) {
            return error("岗位编码已存在");
        }
        post.setCreateBy(getLoginUsername());
        return postService.insertPost(post) > 0 ? success() : error();
    }

    @Put
    @Mapping
    public AjaxResult edit(SysPost post) {
        post.setUpdateBy(getLoginUsername());
        return postService.updatePost(post) > 0 ? success() : error();
    }

    @Delete
    @Mapping("/{postIds}")
    public AjaxResult remove(Long[] postIds) {
        return postService.deletePostByIds(postIds) > 0 ? success() : error();
    }

    private String getLoginUsername() {
        return Context.current().attr("username");
    }
}
