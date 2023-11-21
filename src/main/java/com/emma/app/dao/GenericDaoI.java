package com.emma.app.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDaoI <T> extends Serializable {
    List<T> list(Class<?> entity);

    void addOrUpdateRecord(T entity);

    void deleteAccount(T entity);

}
