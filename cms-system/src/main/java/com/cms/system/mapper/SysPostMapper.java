package com.cms.system.mapper;

import com.cms.system.domain.SysPost;
import org.beetl.sql.core.query.PageQuery;
import org.beetl.sql.mapper.BaseMapper;
import org.beetl.sql.mapper.annotation.SqlResource;
import java.util.List;

@SqlResource("sysPost")
public interface SysPostMapper extends BaseMapper<SysPost> {

    List<SysPost> selectPostList(SysPost post);

    List<SysPost> selectPostList(SysPost post, PageQuery pageQuery);

    List<SysPost> selectPostsByUserId(Long userId);

    int checkPostNameUnique(String postName);

    int checkPostCodeUnique(String postCode);
}
