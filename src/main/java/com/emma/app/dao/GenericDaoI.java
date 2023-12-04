package com.emma.app.dao;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public interface GenericDaoI<T> extends Serializable {
    public List<T> list(Object entity);

    public void addRecord(T entity);

    public void deleteRecord(T entity);

    public int countRecords(Class<?> entity, String whereClause, Object... parameters) ;

    public void update(T entity, String whereClause, Object... parameters);

    public EntityManager getEm();

    void setEm(EntityManager em);
    public List<T> select(Class<T> entityClass, String whereClause, Object... parameters);

}
