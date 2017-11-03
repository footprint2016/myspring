package com.coocaa.myspring.dao;

import com.coocaa.myspring.model.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by panwei on 2016/6/30.
 */
public interface UserMapper {

    void insert(User user);

    void update(User user);

    void delete(Integer id);

    List<User> findBy(HashMap<?, ?> params);

    Integer countBy(HashMap<?, ?> params);

    User getEntityById(Integer id);
}
