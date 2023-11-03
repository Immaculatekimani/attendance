package com.emma.event;

import com.emma.app.model.entity.User;
import com.emma.app.model.entity.UserRole;
import com.emma.database.Database;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppInit implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("#################### App Stariting ###############");

        Database db = Database.getDbInstance();
        db.getUsers().add(new User("E123", "Emma", "123s", UserRole.ADMIN));
        db.getUsers().add(new User("E456", "Tatu", "456", UserRole.EMPLOYER));
        db.getUsers().add(new User("E789", "Kim", "789", UserRole.EMPLOYEE));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("********* App destroyed   *******");
    }


}
