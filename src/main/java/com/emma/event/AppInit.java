package com.emma.event;

import com.emma.app.model.Attendance;
import com.emma.app.model.Employee;
import com.emma.app.model.EmployeeRole;
import com.emma.app.model.User;
import com.emma.database.Database;
import com.emma.database.SqlDatabase;
import com.emma.database.helper.DbTable;
import com.emma.database.helper.DbTableColumn;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class AppInit implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        SqlDatabase.updateScheme();
/*
        Database db = Database.getDbInstance();
        db.getData().add(new Attendance("E12345", "John Doe", LocalDate.of(2023, 11, 5), LocalTime.of(8, 30), "Present"));
        db.getData().add(new Attendance("E38292", "Taylor Swift", LocalDate.of(2023, 11, 5), LocalTime.of(8, 50), "Present"));
        db.getData().add(new Attendance("E39293", "Eminem Mark", LocalDate.of(2023, 11, 5), LocalTime.of(8, 55), "Absent"));
        db.getData().add(new Attendance("E37829", "Hope Kimani", LocalDate.of(2023, 11, 5), LocalTime.of(8, 40), "Present"));
        db.getData().add(new Attendance("E27738", "Smino Otieno", LocalDate.of(2023, 11, 5), LocalTime.of(8, 25), "Absent"));
        db.getData().add(new Attendance("E40283", "Jack Ireri", LocalDate.of(2023, 11, 5), LocalTime.of(8, 34), "Present"));
        db.getData().add(new Attendance("E10392", "Cynthia Wangari", LocalDate.of(2023, 11, 5), LocalTime.of(8, 52), "Absent"));
        db.getData().add(new Attendance("E29390", "Grace Emily", LocalDate.of(2023, 11, 5), LocalTime.of(8, 41), "Present"));

        db.getData().add(new Employee("E12345","John", "Doe",EmployeeRole.DEVELOPER,"images/prof/1.png"));
        db.getData().add(new Employee("E38292","Taylor", "Swift", EmployeeRole.DEVELOPER,"images/prof/2.png"));
        db.getData().add(new Employee("E39293","Eminem", "Mark",EmployeeRole.QA,"images/prof/3.png"));
        db.getData().add(new Employee("E37829","Hope", "Kimani",EmployeeRole.DEVOPS,"images/prof/4.png"));
        db.getData().add(new Employee("E27738","Smino", "Otieno",EmployeeRole.DEVOPS,"images/prof/5.png"));
        db.getData().add(new Employee("E40283","Jack", "Ireri",EmployeeRole.QA,"images/prof/6.png"));
        db.getData().add(new Employee("E10392","Cynthia", "Wangari",EmployeeRole.QA,"images/prof/7.png"));
        db.getData().add(new Employee("E29390","Grace", "Emily",EmployeeRole.DEVELOPER,"images/prof/8.png"));
*/

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("********* App destroyed   *******");
    }


}
