package com.emma.app.model;

import com.emma.database.helper.DbTable;
import com.emma.database.helper.DbTableColumn;

@DbTable(name = "users")
public class User {
    @DbTableColumn(name = "id", definition = "int")
    private int userId;
    @DbTableColumn(name = "username")
    private String username;
    @DbTableColumn(name = "password")
    private String password;
    private String confirmPassword;

    public User() {
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public User(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
