package com.emma.app.bean;

import com.emma.app.model.AttendanceLog;
import com.emma.app.utility.EventLogger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "attendance/AttendanceObserver")
@Remote
public class AttendanceObserver {
    @PersistenceContext
    EntityManager em;

    @Inject
    EventLogger eventLogger;

    public void log(@Observes AttendanceLog attendanceLog) {
        em.merge(attendanceLog);
    }

    public List<AttendanceLog> attendanceLogs() {
        List<AttendanceLog> attendanceEvents = eventLogger.logEvent(AttendanceLog.class);
        if (attendanceEvents.size() > 2) {
            List<AttendanceLog> lastTwoEvents = attendanceEvents.subList(attendanceEvents.size() - 2, attendanceEvents.size());
            return lastTwoEvents;
        } else {
            return attendanceEvents;
        }

    }
}
