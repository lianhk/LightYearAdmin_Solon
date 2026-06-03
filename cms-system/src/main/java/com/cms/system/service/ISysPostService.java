package com.cms.system.service;

import com.cms.system.domain.SysPost;
import org.beetl.sql.core.query.PageQuery;

import java.util.List;

public interface ISysPostService {

    List<SysPost> selectPostList(SysPost post);

    List<SysPost> selectPostPage(SysPost post, PageQuery pageQuery);

    List<SysPost> selectPostsByUserId(Long userId);

    SysPost selectPostById(Long postId);

    int insertPost(SysPost post);

    int updatePost(SysPost post);

    int deletePostByIds(Long[] postIds);

    boolean checkPostNameUnique(String postName);

    boolean checkPostCodeUnique(String postCode);
}
