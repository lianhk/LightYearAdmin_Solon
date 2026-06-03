package com.cms.common.core;

/**
 * 基础Service接口
 */
public interface BaseService<T> {

    /**
     * 根据ID查询
     */
    T selectById(Long id);

    /**
     * 新增
     */
    int insert(T entity);

    /**
     * 修改
     */
    int update(T entity);

    /**
     * 删除
     */
    int deleteById(Long id);
}
