package com.emma.app.action;

import com.emma.app.bean.UserBeanI;
import com.emma.app.model.User;
import com.emma.app.utility.exception.MyExceptionUtils;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/user")
public class UserAction extends BaseAction {
    @EJB
    UserBeanI userBean;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            userBean.register(serializeForm(User.class, req.getParameterMap()));
            resp.sendRedirect("./");

        } catch (Exception e) {
            MyExceptionUtils.redirectToErrorPage(req, resp, e);

        }

    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        List<User> usersResult = userBean.getUser(new User(username));

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Create a JSON response
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        jsonBuilder.add("exists", !usersResult.isEmpty());

        try (PrintWriter out = resp.getWriter()) {
            out.println(jsonBuilder.build().toString());
        }

    }


}
