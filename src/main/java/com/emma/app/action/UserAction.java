package com.emma.app.action;

import com.emma.app.bean.UserBean;
import com.emma.app.bean.UserBeanI;
import com.emma.app.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserAction extends BaseAction {
    UserBeanI userBean = new UserBean();

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        userBean.register(serializeForm(User.class, req.getParameterMap()));

        resp.sendRedirect("./");
        ;
    }

}
