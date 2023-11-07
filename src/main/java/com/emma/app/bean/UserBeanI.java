package com.emma.app.bean;

import com.emma.app.model.entity.User;

public interface UserBeanI {
    boolean register(User user);

    boolean unregister(User user);
}
