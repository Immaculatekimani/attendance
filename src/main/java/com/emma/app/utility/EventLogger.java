package com.emma.app.utility;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class EventLogger<T> {
    @PersistenceContext
    EntityManager em;

    public <T> List<T> logEvent(Class<T> entityClass) {
        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";

        TypedQuery<T> query = em.createQuery(jpql, entityClass);

        List<T> events = query.getResultList();
        return events;

    }
}
