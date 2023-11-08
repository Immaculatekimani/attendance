package com.emma.app.bean;

import com.emma.app.model.entity.User;
import com.emma.database.Database;

public class AuthBean implements AuthBeanI {
    Database database = Database.getDbInstance();

    @Override
    public User authenticate(User loginUser) {
        User userDetails = null;

        for (User user : database.getUsers()) {
            if (loginUser.getUsername().equals(user.getUsername())
                    && loginUser.getPassword().equals(user.getPassword())) {
                userDetails = user;

                break;
            }
        }
        return userDetails;
    }
}
