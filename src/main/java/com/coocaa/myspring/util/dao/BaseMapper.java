package com.coocaa.myspring.util.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by panwei on 2018/6/19.
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
