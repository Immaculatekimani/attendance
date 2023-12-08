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

    public void update(Object entity, String columnName, Object columnValue) {
        try {
            Class<?> clazz = entity.getClass();
            if (!clazz.isAnnotationPresent(Entity.class)) {
                throw new RuntimeException("Entity Annotation Does Not Exist");
            }

            List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

            StringBuilder setBuilder = new StringBuilder();

            for (Field field : fields) {
                if (!field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(Id.class)) {
                    continue;
                }

                field.setAccessible(true);
                Column column = field.getAnnotation(Column.class);

                setBuilder.append(column.name()).append(" = :").append(field.getName()).append(", ");
            }

            // Remove the trailing comma and space from setBuilder
            setBuilder.delete(setBuilder.length() - 2, setBuilder.length());

            String jpqlQuery = "UPDATE " + clazz.getSimpleName() + " SET " + setBuilder +
                    " WHERE " + columnName + " = :columnValue";

            Query query = em.createQuery(jpqlQuery);

            // Set parameters for SET clause
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class) && !field.isAnnotationPresent(Id.class)) {
                    query.setParameter(field.getName(), field.get(entity));
                }
            }

            // Set parameter for WHERE clause (column value)
            query.setParameter("columnValue", columnValue);

            query.executeUpdate();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public T find(Class<T> entityClass, String fieldName, Object columnValue) {
        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e." + fieldName + " = :value";
        TypedQuery<T> query = em.createQuery(jpql, entityClass);
        query.setParameter("value", columnValue);
        return query.getSingleResult();
    }


    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
