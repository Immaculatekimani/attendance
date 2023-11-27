package com.emma.app.bean;

import java.io.Serializable;
import java.util.List;

public interface GenericBeanI<T> extends Serializable {
    public List<T> list(Class<?> entity,String whereClause, Object... parameters);
    void addRecord(T entity);

    public int countRecords(Class<?> entity, String whereClause, Object... parameters);
    public void update(Object entity, String columnName, Object columnValue);

    void deleteAccount(T entity);


}
