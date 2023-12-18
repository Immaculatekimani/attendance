package com.emma.app.bean;

import com.emma.app.model.EmployeeLog;
import com.emma.app.utility.EventLogger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "attendance/EmployeeObserver")
@Remote
public class EmployeeObserver {
    @PersistenceContext
    EntityManager em;
    @Inject
    EventLogger eventLogger;

    public void log(@Observes EmployeeLog employeeLog) {
        em.merge(employeeLog);
    }

    public List<EmployeeLog> employeeLogs() {

        List<EmployeeLog> EmployeeEvents = eventLogger.logEvent(EmployeeLog.class);
        if (EmployeeEvents.size() > 2) {
            List<EmployeeLog> lastTwoEvents = EmployeeEvents.subList(EmployeeEvents.size() - 2, EmployeeEvents.size());
            return lastTwoEvents;
        } else {
            return EmployeeEvents;
        }

    }
}
