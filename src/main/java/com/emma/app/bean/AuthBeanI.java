package com.emma.app.bean;

import com.emma.app.model.entity.User;

public interface AuthBeanI {
    User authenticate(User loginUser);
}
