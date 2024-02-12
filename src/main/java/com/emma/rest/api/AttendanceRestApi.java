package com.emma.rest.api;

import com.emma.app.bean.AttendanceBeanI;
import com.emma.app.model.Attendance;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/attendances")
public class AttendanceRestApi extends BaseRestApi {
    @EJB
    private AttendanceBeanI attendanceBean;

    @RolesAllowed("LOGGED-IN")
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Attendance attendance) {
        attendanceBean.addRecord(attendance);
        return respond();
    }

    @NoAuthentication
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return respond(attendanceBean.list(new Attendance()));
    }

    @NoAuthentication
    @Path("/list/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response employeeAttendance(@PathParam("id") String id) {
        System.out.println("id " + id);

        return respond(attendanceBean.getEmployeeAttendance(id));
    }
    @RolesAllowed("LOGGED-IN")
    @Path("/log")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response log(Attendance attendance) {
        String selectedValue = attendance.getAttendanceStatus();
        attendanceBean.logAttendance(attendance, selectedValue);
        return respond();
    }
}
