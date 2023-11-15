package com.emma.event;

import com.emma.app.model.Attendance;
import com.emma.app.model.Employee;
import com.emma.app.model.EmployeeRole;
import com.emma.app.model.User;
import com.emma.database.Database;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDate;
import java.time.LocalTime;

@WebListener
public class AppInit implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("#################### App Stariting ###############");

        Database db = Database.getDbInstance();
        db.getUsers().add(new User("E123", "Emma", "123s"));
        db.getUsers().add(new User("E456", "Tatu", "456"));
        db.getUsers().add(new User("E789", "Kim", "789"));

        db.getAttendances().add(new Attendance("E12345", "John Doe", LocalDate.of(2023, 11, 5), LocalTime.of(8, 30), "Present"));
        db.getAttendances().add(new Attendance("E38292", "Taylor Swift", LocalDate.of(2023, 11, 5), LocalTime.of(8, 50), "Present"));
        db.getAttendances().add(new Attendance("E39293", "Eminem Mark", LocalDate.of(2023, 11, 5), LocalTime.of(8, 55), "Absent"));
        db.getAttendances().add(new Attendance("E37829", "Hope Kimani", LocalDate.of(2023, 11, 5), LocalTime.of(8, 40), "Present"));
        db.getAttendances().add(new Attendance("E27738", "Smino Otieno", LocalDate.of(2023, 11, 5), LocalTime.of(8, 25), "Absent"));
        db.getAttendances().add(new Attendance("E40283", "Jack Ireri", LocalDate.of(2023, 11, 5), LocalTime.of(8, 34), "Present"));
        db.getAttendances().add(new Attendance("E10392", "Cynthia Wangari", LocalDate.of(2023, 11, 5), LocalTime.of(8, 52), "Absent"));
        db.getAttendances().add(new Attendance("E29390", "Grace Emily", LocalDate.of(2023, 11, 5), LocalTime.of(8, 41), "Present"));

        db.getEmployees().add(new Employee("E12345","John", "Doe",EmployeeRole.DEVELOPER));
        db.getEmployees().add(new Employee("E38292","Taylor", "Swift",EmployeeRole.DEVELOPER));
        db.getEmployees().add(new Employee("E39293","Eminem", "Mark",EmployeeRole.QA));
        db.getEmployees().add(new Employee("E37829","Hope", "Kimani",EmployeeRole.DEVOPS));
        db.getEmployees().add(new Employee("E27738","Smino", "Otieno",EmployeeRole.DEVOPS));
        db.getEmployees().add(new Employee("E40283","Jack", "Ireri",EmployeeRole.QA));
        db.getEmployees().add(new Employee("E10392","Cynthia", "Wangari",EmployeeRole.QA));
        db.getEmployees().add(new Employee("E29390","Grace", "Emily",EmployeeRole.DEVELOPER));

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("********* App destroyed   *******");
    }


}
