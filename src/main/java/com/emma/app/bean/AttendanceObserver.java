package com.emma.app.bean;

import com.emma.app.model.Attendance;
import com.emma.app.model.AttendanceEvent;
import com.emma.database.SqlDatabase;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import java.util.List;

@Stateless(name = "attendance/AttendanceObserver")
@Remote
public class AttendanceObserver  {
    @EJB
    SqlDatabase database;

    public void log(@Observes AttendanceEvent attendanceEvent){
        System.out.println("Adding event log!!"+ attendanceEvent.getAttendanceDetails());
        database.insert(attendanceEvent);
    }
    public List<AttendanceEvent> attendanceLogs( Class<?> entity, String whereClause, Object... parameters) {
        return (List<AttendanceEvent>) database.select(entity, whereClause, parameters);

    }
}
