package com.emma.action;

import com.emma.app.view.html.AppPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("reports")
public class Report extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("loggedInId") != null) {
            new AppPage().renderHtml(req, resp,  3,"<h2>Employee attendance info goes here</h2> ") ;

        } else
            resp.sendRedirect("./");


    }
}
