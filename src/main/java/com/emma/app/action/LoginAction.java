package com.emma.app.action;

import com.emma.app.bean.AuthBeanI;
import com.emma.app.model.User;
import com.emma.app.utility.exception.MyExceptionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;


@WebServlet(urlPatterns = "/login")

public class LoginAction extends BaseAction {

    @EJB
    AuthBeanI authBean;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            HttpSession httpSession = req.getSession();

            if (httpSession.getAttribute("loggedInId") != null) {
                resp.sendRedirect("./home");
            } else
                resp.sendRedirect("./");

        } catch (Exception e) {
            MyExceptionUtils.redirectToErrorPage(req, resp, e);

        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = serializeForm(User.class, req.getParameterMap());

        try {
            User userDetails = authBean.authenticate(loginUser);
            if (userDetails != null && StringUtils.isNotBlank(userDetails.getUsername())) {
                HttpSession httpSession = req.getSession(true);

                httpSession.setAttribute("loggedInId", new Date().getTime() + "");
                httpSession.setAttribute("username", userDetails.getUsername());

                resp.sendRedirect("./home");
            }

        } catch (Exception e) {
            MyExceptionUtils.redirectToErrorPage(req, resp, e);

        }

    }

}
