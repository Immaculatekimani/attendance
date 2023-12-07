package com.emma.app.bean;

import com.emma.app.dao.GenericDao;
import com.emma.app.dao.GenericDaoI;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public class GenericBean<T> implements GenericBeanI<T> {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private GenericDaoI<T> genricDao;

    @Override
    public List<T> list(Object entity) {
        genricDao.setEm(em);
        return genricDao.list(entity);

    }

    @Override
    public void addRecord(T entity) {
        genricDao.setEm(em);
        genricDao.addRecord(entity);

    }

    @Override
    public int countRecords(Class<?> entity, String whereClause, Object... parameters) {
        genricDao.setEm(em);
        return genricDao.countRecords(entity, whereClause, parameters);
    }

    @Override
    public void update(Object entity, String columnName, Object columnValue) {
        genricDao.setEm(em);
        genricDao.update(entity, columnName, columnValue);
    }

    @Override
    public void deleteRecord(Class<T> entityClass, String fieldName, Object columnValue) {
        genricDao.setEm(em);
        genricDao.deleteRecord(entityClass, fieldName, columnValue);

    }

    @Override
    public List<T> select(Class<T> entityClass, String whereClause, Object... parameters) {
        genricDao.setEm(em);
        return genricDao.select(entityClass, whereClause, parameters);
    }

    public GenericDao<T> getDao() {
        genricDao.setEm(em);
        return (GenericDao<T>) genricDao;
    }

}
