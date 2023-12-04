package com.emma.app.bean;

import com.emma.app.model.EmployeeLog;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless(name = "attendance/EmployeeObserver")
@Remote
public class EmployeeObserver {
    @PersistenceContext
    EntityManager em;

    public void log(@Observes EmployeeLog employeeLog) {
        em.merge(employeeLog);
    }

    public List<EmployeeLog> employeeLogs(Class<?> entity, String whereClause, Object... parameters) {
        String jpql = "SELECT e FROM " + entity.getSimpleName() + " e";

        if (!whereClause.isEmpty()) {
            jpql += " WHERE " + whereClause;
        }

        // Create a query
        TypedQuery<EmployeeLog> query = em.createQuery(jpql, EmployeeLog.class);

        // Set parameters if available
        if (parameters != null && parameters.length > 0) {
            for (int i = 0; i < parameters.length; i++) {
                query.setParameter(i + 1, parameters[i]);
            }
        }
        List<EmployeeLog> events = query.getResultList();
        if (events.size() > 2) {
            List<EmployeeLog> lastTwoEvents = events.subList(events.size() - 2, events.size());
            return lastTwoEvents;
        } else {
            return events;
        }

    }
}
