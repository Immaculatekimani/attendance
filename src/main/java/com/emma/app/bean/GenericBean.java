package com.emma.app.bean;

import com.emma.app.model.Attendance;
import com.emma.app.model.Employee;
import com.emma.database.Database;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class GenericBean<T> implements GenericBeanI<T> {
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<T> list(Class<?> entity) {
        return (List<T>) Database.getDbInstance().getData(entity);

    }

    @Override
    public void addOrUpdateRecord(T entity) {
        Database database = Database.getDbInstance();

        database.getData().add(entity);

    }

    @Override
    public void deleteAccount(T entity) {

    }
}
