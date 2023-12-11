package com.emma.rest.api;

import com.emma.app.bean.EmployeeBeanI;
import com.emma.app.model.Employee;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employees")
public class EmployeeRestApi extends BaseRestApi {
    @EJB
    private EmployeeBeanI employeeBean;

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Employee employee) {
        employeeBean.addRecord(employee);
        return respond();
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return respond(employeeBean.list(new Employee()));
    }
}
