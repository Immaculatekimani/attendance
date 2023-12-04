package com.emma.app.model;

import com.emma.app.view.helper.MyTableColHeader;
import com.emma.database.helper.DbTable;
import com.emma.database.helper.DbTableColumn;
@DbTable(name = "attendance_logs")
public class AttendanceEvent {
    @DbTableColumn(name = "attendance_details", definition = "longtext")
    @MyTableColHeader(header = "Attendance log")
    private String attendanceDetails;

    public String getAttendanceDetails() {
        return attendanceDetails;
    }

    public void setAttendanceDetails(String attendanceDetails) {
        this.attendanceDetails = attendanceDetails;
    }
}
