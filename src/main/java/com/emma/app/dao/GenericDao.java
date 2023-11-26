package com.emma.app.dao;

import com.emma.database.SqlDatabase;

import java.util.List;

public class GenericDao<T> implements GenericDaoI<T> {
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<T> list(Class<?> entity,String whereClause, Object... parameters) {
        return (List<T>) SqlDatabase.select(entity, whereClause, parameters);

    }

    @Override
    public void addOrUpdateRecord(T entity) {
        SqlDatabase.insert(entity);

    }
    public void updateRecord(T entity, String idFieldName) {
        // You need to implement a method to update the record based on the entity and its ID.
        // For simplicity, let's assume that there's a method named `update` in your SqlDatabase class.
        // You may need to modify this based on your actual database schema and update logic.
        SqlDatabase.update(entity, idFieldName);
    }

    @Override
    public void deleteAccount(T entity) {

    }

    @Override
    public int countRecords(Class<?> entity) {
        List<T> resultList = this.list(entity,"");
        return resultList.size();
    }
}
