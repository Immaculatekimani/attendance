package com.emma.database;

import com.emma.app.model.entity.Attendance;
import com.emma.app.model.entity.Employee;
import com.emma.app.model.entity.User;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Database implements Serializable {
    private String databaseCreation;
    private List<User> users = new ArrayList<>();
    private List<Attendance> attendances = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();

    private static Database dbInstance;

    private Database() {
    }

    public static Database getDbInstance() {
        if (dbInstance == null) {
            dbInstance = new Database();
            dbInstance.databaseCreation = DateFormat.getDateTimeInstance().format(new Date());

        }
        return dbInstance;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
