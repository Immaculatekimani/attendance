package com.emma.app.bean;

import com.emma.app.model.AttendanceLog;
import com.emma.app.model.EmployeeLog;
import com.emma.database.SqlDatabase;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import java.util.List;

@Stateless(name = "attendance/EmployeeObserver")
@Remote
public class EmployeeObserver {
    @EJB
    SqlDatabase database;

    public void log(@Observes EmployeeLog employeeLog) {
        database.insert(employeeLog);
    }

    public List<EmployeeLog> employeeLogs(Class<?> entity, String whereClause, Object... parameters) {
        List<EmployeeLog> events = (List<EmployeeLog>) database.select(entity, whereClause, parameters);
        if (events.size() > 2) {
            List<EmployeeLog> lastTwoEvents = events.subList(events.size() - 2, events.size());
            return lastTwoEvents;
        } else {
            return events;
        }

    }
}
