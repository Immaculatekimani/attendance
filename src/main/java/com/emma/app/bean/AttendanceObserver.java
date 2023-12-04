package com.emma.app.bean;

import com.emma.app.model.AttendanceLog;
import com.emma.database.SqlDatabase;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import java.util.List;

@Stateless(name = "attendance/AttendanceObserver")
@Remote
public class AttendanceObserver {
    @EJB
    SqlDatabase database;

    public void log(@Observes AttendanceLog attendanceLog) {
        database.insert(attendanceLog);
    }

    public List<AttendanceLog> attendanceLogs(Class<?> entity, String whereClause, Object... parameters) {
        List<AttendanceLog> events = (List<AttendanceLog>) database.select(entity, whereClause, parameters);
        if (events.size() > 2) {
            List<AttendanceLog> lastTwoEvents = events.subList(events.size() - 2, events.size());
            return lastTwoEvents;
        } else {
            return events;
        }

    }
}
