package com.emma.app.bean;

import com.emma.app.model.Attendance;
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

    public List<AttendanceLog> attendanceLogs() {
        String jpql = "SELECT e FROM  AttendanceLog  e";

        TypedQuery<AttendanceLog> query = em.createQuery(jpql, AttendanceLog.class);

        List<AttendanceLog> events = query.getResultList();
        if (events.size() > 2) {
            List<AttendanceLog> lastTwoEvents = events.subList(events.size() - 2, events.size());
            return lastTwoEvents;
        } else {
            return events;
        }

    }
}
