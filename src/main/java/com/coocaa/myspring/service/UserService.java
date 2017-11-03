package com.coocaa.myspring.service;

import com.coocaa.myspring.dao.UserMapper;
import com.coocaa.myspring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by panwei on 2016/6/30.
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void insert(User user) {
        userMapper.insert(user);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public void delete(Integer id) {
        userMapper.delete(id);
    }

    public Integer countBy(HashMap<?, ?> params) {
        return userMapper.countBy(params);
    }

    public List<User> findBy(HashMap<?, ?> params) {
        return userMapper.findBy(params);
    }

    public User getEntityById(Integer id) {
        return userMapper.getEntityById(id);
    }

}
