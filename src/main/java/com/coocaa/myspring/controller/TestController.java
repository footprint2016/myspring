package com.coocaa.myspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by panwei on 2016/6/30.
 */
@Controller
public class TestController {

    @RequestMapping("/test.do")
    public String test(ModelMap modelMap) {
        modelMap.put("msg", "hello spring!");
        return "/hello";
    }
}
