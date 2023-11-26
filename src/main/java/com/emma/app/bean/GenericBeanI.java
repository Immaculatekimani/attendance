package com.emma.app.bean;

import java.io.Serializable;
import java.util.List;

public interface GenericBeanI<T> extends Serializable {
    public List<T> list(Class<?> entity,String whereClause, Object... parameters);
    void addOrUpdateRecord(T entity);

    public int countRecords(Class<?> entity);
    public void updateRecord(T entity, String idFieldName);

    void deleteAccount(T entity);


}
