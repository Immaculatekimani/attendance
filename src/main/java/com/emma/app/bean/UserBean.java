package com.emma.app.bean;

import com.emma.app.model.User;
import com.emma.database.SqlDatabase;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.sql.SQLException;

@Stateless
@Remote
public class UserBean extends GenericBean<User> implements UserBeanI {
    @Override
    public boolean register(User user) throws SQLException {
        if (!user.getPassword().equals(user.getConfirmPassword()))
            throw new RuntimeException("Password & confirm password do not match");

        //1. check if username already exist
        //2. hash password
        //3. initiate event to send email ...Observer design pattern

        getDao().addOrUpdateRecord(user);
        return false;

    }

    @Override
    public boolean unregister(User user) {
        return true;
    }
}
