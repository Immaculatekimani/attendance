package com.emma.app.bean;

import com.emma.app.dao.GenericDao;
import com.emma.app.dao.GenericDaoI;

import java.util.List;

public class GenericBean<T> implements GenericBeanI<T> {
    private final GenericDaoI<T> genricDao = new GenericDao<>();

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<T> list(Class<?> entity) {
        return genricDao.list(entity);

    }

    @Override
    public void addOrUpdateRecord(T entity) {
        genricDao.addOrUpdateRecord(entity);

    }

    @Override
    public int countRecords(Class<?> entity) {
        return genricDao.countRecords(entity);
    }

    @Override
    public void deleteAccount(T entity) {

    }

}
