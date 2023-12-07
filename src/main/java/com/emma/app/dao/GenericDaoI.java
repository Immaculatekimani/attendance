package com.emma.app.dao;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public interface GenericDaoI<T> extends Serializable {
    public List<T> list(Object entity);

    public void addRecord(T entity);

    public void deleteRecord(Class<T> entityClass, String fieldName, Object columnValue);

    public int countRecords(Class<?> entity, String whereClause, Object... parameters);

    public void update(Object entity, String columnName, Object columnValue);

    public EntityManager getEm();

    void setEm(EntityManager em);

    public List<T> select(Class<T> entityClass, String whereClause, Object... parameters);

}
