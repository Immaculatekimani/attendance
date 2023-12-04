package com.emma.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.util.List;

public class GenericDao<T> implements GenericDaoI<T> {
    private EntityManager em;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<T> list(Object entity) {
        String jpql  = "FROM " + entity.getClass().getSimpleName() + " e";

        List<T> results = (List<T>) em.createQuery(jpql, entity.getClass()).getResultList();

        return results;

    }

    @Override
    public void addRecord(T entity) {
        em.merge(entity);

    }


    @Override
    public void deleteRecord(T entity) {
        em.remove(entity);

    }
    public List<T> select(Class<T> entityClass, String whereClause, Object... parameters) {
        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";

        if (!whereClause.isEmpty()) {
            jpql += " WHERE " + whereClause;
        }

        // Create a query
        TypedQuery<T> query = em.createQuery(jpql, entityClass);

        // Set parameters if available
        if (parameters != null && parameters.length > 0) {
            for (int i = 0; i < parameters.length; i++) {
                query.setParameter(i + 1, parameters[i]);
            }
        }

        // Execute the query and get the result
        return query.getResultList();
    }
    @Override
    public int countRecords(Class<?> entity, String whereClause, Object... parameters) {
        String jpql = "SELECT COUNT(e) FROM " + entity.getSimpleName() + " e";

        if (!whereClause.isEmpty()) {
            jpql += " WHERE " + whereClause;
        }

        // Create a query
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);

        // Set parameters if available
        if (parameters != null && parameters.length > 0) {
            for (int i = 0; i < parameters.length; i++) {
                query.setParameter(i + 1, parameters[i]);
            }
        }

        // Execute the query and get the result
        Long result = query.getSingleResult();

        return result.intValue();
    }
    public void update(T entity, String whereClause, Object... parameters) {
        // You may need to modify the WHERE clause based on your entity's fields and database schema
        String jpql = "UPDATE " + entity.getClass().getSimpleName() + " e SET ";
        String[] fieldNames = getFieldNames(entity);

        for (String fieldName : fieldNames) {
            jpql += "e." + fieldName + " = :" + fieldName + ", ";
        }

        jpql = jpql.substring(0, jpql.length() - 2); // Remove the trailing comma and space

        if (!whereClause.isEmpty()) {
            jpql += " WHERE " + whereClause;
        }

        // Create a query
        Query query = em.createQuery(jpql);

        // Set parameters based on your entity's fields
        for (String fieldName : fieldNames) {
            Object value = getFieldValue(entity, fieldName);
            query.setParameter(fieldName, value);
        }

        // Set parameters if available
        if (parameters != null && parameters.length > 0) {
            for (int i = 0; i < parameters.length; i++) {
                query.setParameter("param" + (i + 1), parameters[i]);
            }
        }

        // Execute the update query
        query.executeUpdate();
    }

    private String[] getFieldNames(Object entity) {
        Class<?> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    private Object getFieldValue(Object entity, String fieldName) {
        try {
            Field field = entity.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(entity);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error getting field value", e);
        }
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
