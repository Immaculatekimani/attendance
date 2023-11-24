package com.emma.app.dao;

import com.emma.database.SqlDatabase;

import java.util.List;

public class GenericDao<T> implements GenericDaoI<T> {
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<T> list(Class<?> entity) {
        return (List<T>) SqlDatabase.select(entity);

    }

    @Override
    public void addOrUpdateRecord(T entity) {
        SqlDatabase.insert(entity);

    }

    @Override
    public void deleteAccount(T entity) {

    }

    @Override
    public int countRecords(Class<?> entity) {
        List<T> resultList = this.list(entity);
        return resultList.size();
    }
}
