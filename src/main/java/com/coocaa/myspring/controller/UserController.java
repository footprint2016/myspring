package com.coocaa.myspring.controller;

import com.alibaba.fastjson.JSONObject;
import com.coocaa.myspring.model.User;
import com.coocaa.myspring.service.UserService;
import com.coocaa.myspring.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by panwei on 2016/6/30.
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserList")
    @ResponseBody
    public String getUserList() {
        HashMap<String, Object> params = new HashMap<>();
        List<User> userList = userService.findBy(params);
        return JSONObject.toJSONString(userList);

    }

    public String getViews(String str) {
        return "user/" + str;
    }

    @RequestMapping("/index")
    public String index() {
        return getViews("index");
    }

    @RequestMapping("/get_users_json")
    @ResponseBody
    public Object getUsersJson(Integer start, Integer length, HttpServletRequest request, User User) {
        HashMap<String, Object> result = new HashMap<>();

        HashMap<String, Object> params = new HashMap<>();
        params = MyUtils.DataTables.getRequestSortParams(request, params);
        params = MyUtils.Conversion.objToMap(params, User, true);
        Integer icount = userService.countBy(params);
        result.put("recordsTotal", icount);//总记录数
        result.put("recordsFiltered", icount);//过滤后的总记录数

        params.put("pagination_limit", length);
        params.put("pagination_offset", start);
        List<User> list = userService.findBy(params);
        List<HashMap<String, Object>> lists = new ArrayList<>();
        for (User item : list) {
            HashMap<String, Object> map = new HashMap<>();
            MyUtils.Conversion.objToMap(map, item, false);
            lists.add(map);
        }
        result.put("data", lists);
        return JSONObject.toJSONString(result);
    }

    @RequestMapping("/checkUsersId")
    @ResponseBody
    public Object checkUsersId(String userId, Integer id) {
        if (!userId.isEmpty()) {
            HashMap<String, Object> params = new HashMap<>();
            params.put("userId", userId);
            if (id != null) {
                params.put("unequal", "user_id!=" + id);
            }
            List<User> userse = userService.findBy(params);
            if (userse.size() > 0) {
                return false;
            }
        }
        return true;
    }

    @RequestMapping("/modify")
    public String modify(Integer id, ModelMap model) {
        User user = new User();
        if (id != null) {
            user = userService.getEntityById(id);
        } else {
            user.setStatus(0);
            user.setRolesId(0);
        }

        model.put("model", user);
        return getViews("modify");
    }

    @RequestMapping("/add")
    public Object add(User user) {
        if (user.getId() == null) {
            user.setPwd(user.getPwd());
            userService.insert(user);
        } else {
            if (user.getPwd() != null && !user.getPwd().equals("")) {
                user.setPwd(user.getPwd());
            } else {
                user.setPwd(null);
            }
            userService.update(user);
        }
        return true;
    }

    @RequestMapping("/changeStatus")
    public Object changeStatus(Integer id, Integer status) {
        User user = userService.getEntityById(id);
        if (user != null) {
            user.setStatus(status);
            userService.update(user);
        }
        return true;
    }

    @RequestMapping("/del")
    public Object delete(Integer id) {
        if (id == null || id.equals(0)) {
            return false;
        } else {
            User user = userService.getEntityById(id);
            if (user != null)
                userService.delete(id);
            return true;
        }
    }

    public static void main(String[] args) {
        String str = "";
        System.out.println();
    }
}
