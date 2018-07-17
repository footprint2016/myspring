package com.coocaa.myspring.controller;

import com.coocaa.myspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by panwei on 2016/6/28.
 */
public class BaseController {
    @Autowired
    UserService userService;
}
