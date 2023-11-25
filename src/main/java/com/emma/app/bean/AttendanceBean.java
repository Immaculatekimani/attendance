package com.emma.app.bean;

import com.emma.app.model.Attendance;
import com.emma.app.model.Employee;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Stateless(name = "attendance/AttendanceBean")
@Remote
public class AttendanceBean extends GenericBean<Attendance> implements AttendanceBeanI {

    @EJB
    private EmployeeBeanI employeeBean;

    public Attendance logAttendance(Attendance attendance, String selectedValue) {
        String[] parts = selectedValue.split("_");
        String employeeId = parts[0];
        String attendStatus = parts[1];

        for (Employee employee : employeeBean.list(Employee.class)) {
            String currentEmployeeId = employee.getEmployeeId();
            String employeeName = employee.getFirstName() + " " + employee.getLastName();
            String employeeImage = employee.getEmployeeImage();

            if (attendStatus != null && currentEmployeeId.equals(employeeId)) {
                LocalTime currentTime = LocalTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime displayTime = LocalTime.parse(currentTime.format(formatter), formatter);

                attendance.setEmployeeImage(employeeImage);
                attendance.setEmployeeID(employeeId);
                attendance.setEmployeeName(employeeName);
                attendance.setAttendanceDate(LocalDate.now());
                attendance.setAttendanceTime(displayTime);
                attendance.setAttendanceStatus(attendStatus);

                try {
                    addOrUpdateRecord(attendance);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }


        }
        return attendance;
    }

}
