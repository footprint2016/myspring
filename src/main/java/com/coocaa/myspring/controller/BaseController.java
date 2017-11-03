package com.coocaa.myspring.controller;

import com.coocaa.myspring.utils.MyUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by panwei on 2016/6/28.
 */
public class BaseController extends MyUtils {

    /**
     * Controller调用前调用
     *
     * @param request
     * @param response
     * @param session
     * @param model
     */
    @ModelAttribute
    protected void setter(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) {
        this.request = request;
        this.response = response;
        this.session = session;
        this.model = model;
    }
}
