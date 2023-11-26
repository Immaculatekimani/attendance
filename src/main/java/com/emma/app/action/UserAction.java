package com.emma.app.action;

import com.emma.app.bean.UserBean;
import com.emma.app.bean.UserBeanI;
import com.emma.app.model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user")
public class UserAction extends BaseAction {
    @EJB
    UserBeanI userBean;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            userBean.register(serializeForm(User.class, req.getParameterMap()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("./");
        ;
    }

}
