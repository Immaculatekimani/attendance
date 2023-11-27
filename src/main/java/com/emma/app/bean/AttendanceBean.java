package com.emma.app.bean;

import com.emma.app.model.Attendance;
import com.emma.app.model.Employee;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Stateless(name = "attendance/AttendanceBean")
@Remote
public class AttendanceBean extends GenericBean<Attendance> implements AttendanceBeanI {

    @EJB
    private EmployeeBeanI employeeBean;

    public Attendance logAttendance(Attendance attendance, String selectedValue) {
        String[] parts = selectedValue.split("_");
        String employeeId = parts[0];
        String attendStatus = parts[1];

        List<Attendance> existingRecords = getEmployeeAttendance(employeeId);
        Attendance existingRecord = findExistingRecord(existingRecords, LocalDate.now());

        for (Employee employee : employeeBean.list(Employee.class, "")) {
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

                // No existing record, create a new one
                if (existingRecord == null) {
                    handleAttendanceStatus(attendance, attendStatus);

                    try {
                        addRecord(attendance);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    // Update existing record
                    handleAttendanceStatus(attendance, attendStatus);
                    existingRecord.setTimeOut(attendance.getAttendanceTime());
                    existingRecord.setAttendanceStatus(attendance.getAttendanceStatus());

                    try {
                        update(existingRecord, "employee_id", employeeId);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return attendance;
    }


    private Attendance findExistingRecord(List<Attendance> records, LocalDate currentDate) {
        for (Attendance record : records) {
            if (record.getAttendanceDate().equals(currentDate)) {
                return record;
            }
        }
        return null;
    }

    private void handleAttendanceStatus(Attendance attendance, String attendStatus) {
        switch (attendStatus) {
            case "Entry":
                if (attendance.getAttendanceTime().isAfter(LocalTime.parse("08:00", DateTimeFormatter.ofPattern("HH:mm")))) {
                    attendance.setAttendanceStatus("Late");
                    attendance.setJoiningStatus("Late");
                } else {
                    attendance.setAttendanceStatus("Present");
                    attendance.setJoiningStatus("In-Time");
                }
                attendance.setTimeIn(attendance.getAttendanceTime());
                break;
            case "Exit":
                attendance.setTimeOut(attendance.getAttendanceTime());

                // Check if the exit time is before 5:00 PM
                if (attendance.getTimeOut().isBefore(LocalTime.parse("17:00", DateTimeFormatter.ofPattern("HH:mm")))) {
                    attendance.setAttendanceStatus("HalfDay");
                } else {
                    attendance.setAttendanceStatus("FullDay");
                }
                break;
            case "Absent":
                attendance.setAttendanceStatus("Absent");
                break;
        }
    }

    @Override
    public List<Attendance> getEmployeeAttendance(String employeeId) {
        try {
            String whereClause = "employee_id = ?";
            return list(Attendance.class, whereClause, employeeId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
