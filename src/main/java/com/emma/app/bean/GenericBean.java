package com.emma.app.bean;

import com.emma.app.dao.GenericDao;
import com.emma.app.dao.GenericDaoI;
import com.emma.database.SqlDatabase;

import javax.ejb.EJB;
import java.util.List;


public class GenericBean<T> implements GenericBeanI<T> {
    @EJB
    SqlDatabase database;
    private final GenericDaoI<T> genricDao = new GenericDao<>();

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<T> list(Class<?> entity, String whereClause, Object... parameters) {
        genricDao.setDatabse(database);
        return genricDao.list(entity, whereClause, parameters);

    }

    @Override
    public void addOrUpdateRecord(T entity) {
        genricDao.setDatabse(database);
        genricDao.addOrUpdateRecord(entity);

    }

    @Override
    public int countRecords(Class<?> entity) {
        genricDao.setDatabse(database);
        return genricDao.countRecords(entity);
    }

    @Override
    public void updateRecord(T entity, String idFieldName) {
        genricDao.updateRecord(entity, idFieldName);
    }

    @Override
    public void deleteAccount(T entity) {

    }

    public GenericDao<T> getDao() {
        genricDao.setDatabse(database);
        return (GenericDao<T>) genricDao;
    }

}
