package com.cms.system.service;

import com.cms.system.domain.SysPost;
import java.util.List;

public interface ISysPostService {

    List<SysPost> selectPostList(SysPost post);

    List<SysPost> selectPostsByUserId(Long userId);

    SysPost selectPostById(Long postId);

    int insertPost(SysPost post);

    int updatePost(SysPost post);

    int deletePostByIds(Long[] postIds);

    boolean checkPostNameUnique(String postName);

    boolean checkPostCodeUnique(String postCode);
}
