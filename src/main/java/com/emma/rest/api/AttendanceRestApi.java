package com.emma.rest.api;

import com.emma.app.bean.AttendanceBeanI;
import com.emma.app.model.Attendance;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/attendances")
public class AttendanceRestApi extends BaseRestApi {
    @EJB
    private AttendanceBeanI attendanceBean;

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Attendance attendance) {
        attendanceBean.addRecord(attendance);
        return respond();
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return respond(attendanceBean.list(new Attendance()));
    }
}