package com.emma.action;

import com.emma.app.view.html.AppPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/attendance-sheet")
public class AttendanceSheet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("loggedInId") != null) {
            new AppPage().renderHtml(req, resp,  1,"<h2>Adding and updating daily attendances</h2> ") ;

        } else
            resp.sendRedirect("./");


    }
}
