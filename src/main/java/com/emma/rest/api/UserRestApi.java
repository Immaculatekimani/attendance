package com.emma.rest.api;

import com.emma.app.bean.UserBeanI;
import com.emma.app.model.User;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/users")
public class UserRestApi extends BaseRestApi {
    @EJB
    UserBeanI userBean;

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(User user) {
        try {
            userBean.register(user);
            return respond();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return respond(userBean.list(new User()));
    }

}
