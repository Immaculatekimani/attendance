package com.emma.app.dao;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericDao<T> implements GenericDaoI<T> {
    private EntityManager em;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<T> list(Object entity) {
        String jpql = "FROM " + entity.getClass().getSimpleName() + " e";

        List<T> results = (List<T>) em.createQuery(jpql, entity.getClass()).getResultList();

        return results;

    }

    @Override
    public void addRecord(T entity) {
        em.merge(entity);

    }


    @Override
    public void deleteRecord(Class<T> entityClass, String fieldName, Object columnValue) {
        String jpql = "DELETE FROM " + entityClass.getSimpleName() + " e WHERE e." + fieldName + " = :value";
        Query query = em.createQuery(jpql);
        query.setParameter("value", columnValue);
        query.executeUpdate();

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


    public T find(Class<T> entityClass, String fieldName, Object columnValue) {
        if (fieldName == null || fieldName.isEmpty()) {
            throw new IllegalArgumentException("Field name cannot be null or empty");
        }

        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e." + fieldName + " = :value";
        TypedQuery<T> query = em.createQuery(jpql, entityClass);
        query.setParameter("value", columnValue);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
