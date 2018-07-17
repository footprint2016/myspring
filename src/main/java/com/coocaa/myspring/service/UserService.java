package com.coocaa.myspring.service;

import com.coocaa.myspring.po.User;
import com.coocaa.myspring.util.resp.RespObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by panwei on 2016/6/30.
 */
@Service
public class UserService extends BaseService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public RespObject getUserList() {
        RespObject respObject = new RespObject();
        List<User> userList = userMapper.selectAll();
        respObject.setData(userList);
        return respObject;
    }

    public User selectOne(String loginName) {
        return userMapper.selectOne(new User(loginName));
    }
}
