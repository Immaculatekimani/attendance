package com.emma.app.bean;

import com.emma.app.model.User;
import com.emma.app.utility.HashText;
import com.emma.database.SqlDatabase;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Stateless(name = "attendance/AuthBean")
@Remote
public class AuthBean implements AuthBeanI, Serializable {
    @EJB
    SqlDatabase database;
    @Inject
    private HashText hashText;

    @Override
    public User authenticate(User loginUser) {
        try {
            loginUser.setPassword(hashText.hash(loginUser.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        String whereClause = "username = ? AND password = ?"; // Adjust the condition based on your authentication logic
        List<User> users = database.select(User.class, whereClause, loginUser.getUsername(), loginUser.getPassword());

        if (users.isEmpty() || users.get(0) == null) {
            throw new RuntimeException("Invalid user!!");
        }

        return users.get(0);

    }
}
