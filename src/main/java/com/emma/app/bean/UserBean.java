package com.emma.app.bean;

import com.emma.app.model.User;
import com.emma.database.SqlDatabase;

import java.io.Serializable;
import java.sql.SQLException;

public class UserBean implements UserBeanI, Serializable {

    @Override
    public boolean register(User user) throws SQLException {
        if (user.getPassword().equals(user.getConfirmPassword())) {

            SqlDatabase.insert(user);

            return true;
        }

        return false;
    }

    @Override
    public boolean unregister(User user) {
        return true;
    }
}
