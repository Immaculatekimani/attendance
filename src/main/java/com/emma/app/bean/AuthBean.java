package com.emma.app.bean;

import com.emma.app.model.User;
import com.emma.app.utility.HashText;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Stateless(name = "attendance/AuthBean")
@Remote
public class AuthBean implements AuthBeanI, Serializable {
    @PersistenceContext
    EntityManager em;
    @Inject
    private HashText hashText;

    @Override
    public User authenticate(User loginUser) {
        try {
            loginUser.setPassword(hashText.hash(loginUser.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        List<User> users = em.createQuery("FROM User u WHERE u.password=:password AND u.username=:username", User.class)
                .setParameter("password", loginUser.getPassword())
                .setParameter("username", loginUser.getUsername())
                .getResultList();
        if (users.isEmpty() || users.get(0) == null) {
            throw new RuntimeException("Invalid user!!");
        }

        return users.get(0);

    }
}
