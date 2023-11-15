package com.emma.app.bean;

import com.emma.app.model.Attendance;
import com.emma.app.model.Employee;
import com.emma.database.Database;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class GenericBean<T> implements GenericBeanI<T> {
    @Override
    public List<T> list() {
        Class clazz = ((Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);

        if (clazz.equals(Employee.class))
            return (List<T>) Database.getDbInstance().getEmployees();
        if (clazz.equals(Attendance.class))
            return (List<T>) Database.getDbInstance().getAttendances();

        return new ArrayList<>();
    }

    @Override
    public T addOrUpdateRecord(T entity) {
        Database database = Database.getDbInstance();


        if (entity instanceof Employee) {
            database.getEmployees().add((Employee) entity);
        }
        if (entity instanceof Attendance) {
            database.getAttendances().add((Attendance) entity);
        }
        return entity;

    }

    @Override
    public void deleteAccount(T entity) {

    }
}
