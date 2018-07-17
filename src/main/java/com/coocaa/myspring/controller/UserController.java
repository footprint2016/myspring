package com.coocaa.myspring.controller;

import com.coocaa.myspring.util.enumobj.EnumSystem;
import com.coocaa.myspring.util.resp.RespObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by panwei on 2016/6/30.
 */
@Controller
public class UserController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/user/getUserList")
    @ResponseBody
    public RespObject getUserList() {
        RespObject respObject = new RespObject();
        RespObject userList = userService.getUserList();
        respObject.setData(userList);
        return respObject;
    }

    @RequestMapping("/loginPage")
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "login", method = {RequestMethod.POST}, produces = "application/json; charset=utf-8")
    @ResponseBody
    public RespObject login(String loginName, String password) {
        RespObject respObject = new RespObject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            logger.info("对用户[" + loginName + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + loginName + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            logger.info("对用户[" + loginName + "]进行登录验证..验证未通过,未知账户");
            respObject.setFailResult("未知账户");
            currentUser.logout();
        } catch (IncorrectCredentialsException ice) {
            logger.info("对用户[" + loginName + "]进行登录验证..验证未通过,错误的凭证");
            respObject.setFailResult("密码不正确");
            currentUser.logout();
        } catch (LockedAccountException lae) {
            logger.info("对用户[" + loginName + "]进行登录验证..验证未通过,账户已锁定");
            respObject.setFailResult("账户已锁定");
            currentUser.logout();
        } catch (ExcessiveAttemptsException eae) {
            logger.info("对用户[" + loginName + "]进行登录验证..验证未通过,错误次数过多");
            respObject.setFailResult("用户名或密码错误次数过多");
            currentUser.logout();
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + loginName + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            respObject.setFailResult("用户名或密码不正确");
            currentUser.logout();
        }
        logger.info("登录是否成功: " + currentUser.isAuthenticated());
        if (EnumSystem.SYSTEM_FAILED.getCode().equals(respObject.getCode())) {
            token.clear();
        }
        return respObject;
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    @RequestMapping("/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }
}
