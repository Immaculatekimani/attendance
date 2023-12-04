package com.emma.app.bean;

import java.io.Serializable;
import java.util.List;

public interface GenericBeanI<T> extends Serializable {
    public List<T> list(Object entity);
    void addRecord(T entity);

    public int countRecords(Class<?> entity, String whereClause, Object... parameters);
    public void update(T entity, String whereClause, Object... parameters) ;

    public void deleteRecord(T entity);
    public List<T> select(Class<T> entityClass, String whereClause, Object... parameters);


}
