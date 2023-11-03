package com.emma.event;

import com.emma.app.model.entity.Attendance;
import com.emma.app.model.entity.User;
import com.emma.app.model.entity.UserRole;
import com.emma.database.Database;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class AppInit implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("#################### App Stariting ###############");

        Database db = Database.getDbInstance();
        db.getUsers().add(new User("E123", "Emma", "123s", UserRole.ADMIN));
        db.getUsers().add(new User("E456", "Tatu", "456", UserRole.EMPLOYER));
        db.getUsers().add(new User("E789", "Kim", "789", UserRole.EMPLOYEE));

        db.getAttendances().add(new Attendance("E12345", "John Doe", LocalDate.of(2023, 11, 5), LocalTime.of(8, 30), "Present"));
        db.getAttendances().add(new Attendance("E38292", "Taylor Swift", LocalDate.of(2023, 11, 5), LocalTime.of(8, 50), "Present"));
        db.getAttendances().add(new Attendance("E39293", "Eminem Mark", LocalDate.of(2023, 11, 5), LocalTime.of(8, 55), "Absent"));

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("********* App destroyed   *******");
    }


}
