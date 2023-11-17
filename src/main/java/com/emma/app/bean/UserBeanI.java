package com.emma.app.bean;

import com.emma.app.model.User;

import java.sql.SQLException;

public interface UserBeanI {
    boolean register(User user) throws SQLException;

    boolean unregister(User user);
}
