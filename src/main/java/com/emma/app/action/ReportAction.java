package com.emma.app.action;

import com.emma.app.model.Attendance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("reports")
public class ReportAction extends BaseAction {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        renderPage(req, resp, 3, "<h2>Employee attendance info goes here</h2> ", Attendance.class, new ArrayList<Attendance>());


    }
}
