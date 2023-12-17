package com.emma.app.bean;

import com.emma.app.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserBeanI extends GenericBeanI<User> {
    boolean register(User user) throws SQLException;

    boolean unregister(User user);

    public List<User> getUser(User checkUser);
}
