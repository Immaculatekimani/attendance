package com.emma.app.bean;

import com.emma.app.model.User;
import com.emma.app.utility.HashText;
import com.emma.database.SqlDatabase;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Stateless
@Remote
public class UserBean extends GenericBean<User> implements UserBeanI {
    @Inject
    private HashText hashText;

    @Override
    public boolean register(User user) throws SQLException {
        if (!user.getPassword().equals(user.getConfirmPassword()))
            throw new RuntimeException("Password & confirm password do not match");

        String whereClause = "username = ? AND password = ?"; // Adjust the condition based on your authentication logic
        List<User> users = list(User.class, whereClause, user.getUsername(), user.getPassword());
        if (!users.isEmpty())
            throw new RuntimeException("User already exists!");

        try {
            user.setPassword(hashText.hash(user.getPassword()));
        } catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }

        //3. initiate event to send email ...Observer design pattern

        getDao().addRecord(user);

        return false;
    }

    @Override
    public boolean unregister(User user) {
        return true;
    }
}
