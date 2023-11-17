package com.emma.app.bean;

import com.emma.app.model.User;

import java.sql.SQLException;

public interface AuthBeanI {
    User authenticate(User loginUser) throws SQLException;
}
