package com.emma.app.bean;

import com.emma.app.dao.GenericDao;
import com.emma.app.dao.GenericDaoI;
import com.emma.database.SqlDatabase;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.util.List;


public class GenericBean<T> implements GenericBeanI<T> {
    @EJB
    SqlDatabase database;
    @Inject
    private GenericDaoI<T> genricDao;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<T> list(Class<?> entity, String whereClause, Object... parameters) {
        genricDao.setDatabse(database);
        return genricDao.list(entity, whereClause, parameters);

    }

    @Override
    public void addRecord(T entity) {
        genricDao.setDatabse(database);
        genricDao.addRecord(entity);

    }

    @Override
    public int countRecords(Class<?> entity, String whereClause, Object... parameters){
        genricDao.setDatabse(database);
        return genricDao.countRecords(entity, whereClause, parameters);
    }

    @Override
    public void update(Object entity, String columnName, Object columnValue) {
        genricDao.setDatabse(database);
        genricDao.update(entity,columnName,columnValue);
    }

    @Override
    public void deleteAccount(T entity) {

    }

    public GenericDao<T> getDao() {
        genricDao.setDatabse(database);
        return (GenericDao<T>) genricDao;
    }

}
