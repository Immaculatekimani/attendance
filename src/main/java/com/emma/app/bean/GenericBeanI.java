package com.emma.app.bean;

import java.io.Serializable;
import java.util.List;

public interface GenericBeanI<T> extends Serializable {
    List<T> list(Class<?> entity);

    void addOrUpdateRecord(T entity);

    void deleteAccount(T entity);


}
