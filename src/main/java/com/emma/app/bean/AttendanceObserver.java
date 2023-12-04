package com.emma.app.bean;

import com.emma.app.model.AttendanceLog;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless(name = "attendance/AttendanceObserver")
@Remote
public class AttendanceObserver {
    @PersistenceContext
    EntityManager em;

    public void log(@Observes AttendanceLog attendanceLog) {
        em.merge(attendanceLog);
    }

    public List<AttendanceLog> attendanceLogs(Class<?> entity, String whereClause, Object... parameters) {
        String jpql = "SELECT e FROM " + entity.getSimpleName() + " e";

        if (!whereClause.isEmpty()) {
            jpql += " WHERE " + whereClause;
        }

        // Create a query
        TypedQuery<AttendanceLog> query = em.createQuery(jpql, AttendanceLog.class);

        // Set parameters if available
        if (parameters != null && parameters.length > 0) {
            for (int i = 0; i < parameters.length; i++) {
                query.setParameter(i + 1, parameters[i]);
            }
        }
        List<AttendanceLog> events = query.getResultList();
        if (events.size() > 2) {
            List<AttendanceLog> lastTwoEvents = events.subList(events.size() - 2, events.size());
            return lastTwoEvents;
        } else {
            return events;
        }

    }
}
