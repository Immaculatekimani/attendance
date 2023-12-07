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

    public List<EmployeeLog> employeeLogs() {
        String jpql = "SELECT e FROM EmployeeLog e";

        TypedQuery<EmployeeLog> query = em.createQuery(jpql, EmployeeLog.class);

        List<EmployeeLog> events = query.getResultList();
        if (events.size() > 2) {
            List<EmployeeLog> lastTwoEvents = events.subList(events.size() - 2, events.size());
            return lastTwoEvents;
        } else {
            return events;
        }

    }
}
