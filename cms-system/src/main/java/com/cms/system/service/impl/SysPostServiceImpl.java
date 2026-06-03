package com.cms.system.service.impl;

import com.cms.system.domain.SysPost;
import com.cms.system.mapper.SysPostMapper;
import com.cms.system.service.ISysPostService;
import org.beetl.sql.core.query.PageQuery;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.List;

@Component
public class SysPostServiceImpl implements ISysPostService {

    @Inject
    private SysPostMapper postMapper;

    @Override
    public List<SysPost> selectPostList(SysPost post) {
        return postMapper.selectPostList(post);
    }

    @Override
    public List<SysPost> selectPostPage(SysPost post, PageQuery pageQuery) {
        return postMapper.selectPostList(post, pageQuery);
    }

    @Override
    public List<SysPost> selectPostsByUserId(Long userId) {
        return postMapper.selectPostsByUserId(userId);
    }

    @Override
    public SysPost selectPostById(Long postId) {
        return postMapper.selectById(postId);
    }

    @Override
    public int insertPost(SysPost post) {
        return postMapper.insert(post);
    }

    @Override
    public int updatePost(SysPost post) {
        return postMapper.update(post);
    }

    @Override
    public int deletePostByIds(Long[] postIds) {
        int count = 0;
        for (Long postId : postIds) {
            count += postMapper.deleteById(postId);
        }
        return count;
    }

    @Override
    public boolean checkPostNameUnique(String postName) {
        return postMapper.checkPostNameUnique(postName) == 0;
    }

    @Override
    public boolean checkPostCodeUnique(String postCode) {
        return postMapper.checkPostCodeUnique(postCode) == 0;
    }
}
