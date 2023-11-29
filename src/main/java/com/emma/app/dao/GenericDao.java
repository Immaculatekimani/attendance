package com.emma.app.dao;

import com.emma.database.SqlDatabase;

import java.util.List;

public class GenericDao<T> implements GenericDaoI<T> {
    private SqlDatabase database;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<T> list(Class<?> entity, String whereClause, Object... parameters) {
        return (List<T>) database.select(entity, whereClause, parameters);

    }

    @Override
    public void addRecord(T entity) {
        database.insert(entity);

    }

    public void update(Object entity, String columnName, Object columnValue) {
        // You need to implement a method to update the record based on the entity and its ID.
        // For simplicity, let's assume that there's a method named `update` in your SqlDatabase class.
        // You may need to modify this based on your actual database schema and update logic.
        database.update(entity,columnName,columnValue);
    }

    @Override
    public SqlDatabase getDatabase() {
        return database;
    }

    @Override
    public void setDatabse(SqlDatabase database) {
        this.database = database;
    }

    @Override
    public void deleteRecord(Class<?> entityClass, String columnName, Object columnValue) {
        database.delete(entityClass, columnName, columnValue);

    }

    @Override
    public int countRecords(Class<?> entity, String whereClause, Object... parameters) {
        List<T> resultList = this.list(entity, whereClause,parameters);
        return resultList.size();
    }
}
