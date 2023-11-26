package com.emma.app.dao;

import com.emma.database.SqlDatabase;

import java.io.Serializable;
import java.util.List;

public interface GenericDaoI <T> extends Serializable {
    public List<T> list(Class<?> entity,String whereClause, Object... parameters);
    void addOrUpdateRecord(T entity);

    void deleteAccount(T entity);
    public int countRecords(Class<?> entity);
    public void updateRecord(T entity, String idFieldName);
    SqlDatabase getDatabase();

    void setDatabse(SqlDatabase database);

}
