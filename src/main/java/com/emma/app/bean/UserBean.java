package com.emma.app.bean;

import com.emma.app.model.User;
import com.emma.database.Database;
import com.emma.database.SqlDatabase;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

public class UserBean implements UserBeanI, Serializable {
    Database database = Database.getDbInstance();

    @Override
    public boolean register(User user) throws SQLException {
        if (user.getPassword().equals(user.getConfirmPassword())) {
            PreparedStatement statement = SqlDatabase.getInstance().getConnection()
                    .prepareStatement("insert into users(id, username,password) values(?,?,?)");
            statement.setInt(1, ThreadLocalRandom.current().nextInt(1, 1000));
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());

            statement.executeUpdate();

            return true;
        }

        return false;
    }

    @Override
    public boolean unregister(User user) {
        return true;
    }
}
