package com.emma.app.bean;

import com.emma.app.model.User;
import com.emma.app.utility.HashText;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;

@Stateless
@Remote
public class UserBean extends GenericBean<User> implements UserBeanI {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private HashText hashText;

    @Override
    public boolean register(User user) throws SQLException {
        if (!user.getPassword().equals(user.getConfirmPassword()))
            throw new RuntimeException("Password & confirm password do not match");

        List<User> users = select(User.class, "username = ?1", user.getUsername());
        if (!users.isEmpty())
            throw new RuntimeException("User already exists!");

        try {
            user.setPassword(hashText.hash(user.getPassword()));
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }

        getDao().addRecord(user);

        return false;
    }

    @Override
    public boolean unregister(User user) {
        getDao().deleteRecord(User.class, "id", user.getId());
        return true;
    }

    public List<User> getUser(User checkUser) {
        List<User> usersResult = select(User.class, "username = ?1", checkUser.getUsername());
        return usersResult;
    }
}
