package com.coocaa.myspring.service;

import com.coocaa.myspring.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by panwei on 2018/7/17.
 */
public class BaseService {
    private static final Logger logger = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    UserMapper userMapper;
}
