package com.emma.app.bean;

import com.emma.app.model.User;
import com.emma.database.Database;
import com.emma.database.SqlDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthBean implements AuthBeanI {
    Database database = Database.getDbInstance();

    @Override
    public User authenticate(User loginUser) throws SQLException {
        PreparedStatement statement = SqlDatabase.getInstance().getConnection()
                .prepareStatement("select id,username from users where username=? and password=? limit 1");
        statement.setString(1, loginUser.getUsername());
        statement.setString(2, loginUser.getPassword());

        ResultSet result = statement.executeQuery();
        User user = new User();

        while (result.next()) {
            user.setUserId(result.getInt("id"));
            user.setUsername(result.getString("username"));

        }
        return user;

    }
}
