package com.emma.action;

import com.emma.app.bean.AttendanceBean;
import com.emma.app.bean.AttendanceBeanI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/home")
public class Home extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("loggedInId") != null) {
            AttendanceBeanI attendanceBean = new AttendanceBean();
            PrintWriter print = resp.getWriter();
            print.write("<!DOCTYPE html>\n" +
                    "            <head>\n" +
                    "            <style>\\n\"\n +" +
                    ".attendance {\n" +
                    "    margin: 20px;\n" +
                    "    padding: 20px;\n" +
                    "    background-color: #fff;\n" +
                    "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                    "    border-radius: 5px;\n" +
                    "}\n" +
                    "\n" +
                    "table {\n" +
                    "    width: 100%;\n" +
                    "    border-collapse: collapse;\n" +
                    "    margin-top: 20px;\n" +
                    "    background-color: #C08261;\n" +
                    "    border: 1px solid #ddd;\n" +
                    "}\n" +
                    "\n" +
                    "table th, table td {\n" +
                    "    border: 1px solid #ddd;\n" +
                    "    padding: 10px;\n" +
                    "    text-align: center;\n" +
                    "}\n" +
                    "\n" +
                    "table th {\n" +
                    "    background-color: #186F65;\n" +
                    "    color: #fff;\n" +
                    "}\n" +
                    "\n" +
                    "table tbody tr:nth-child(odd) {\n" +
                    "    background-color: #C08261;\n" +
                    "}\n" +
                    "\n" +
                    "table tbody tr:nth-child(even) {\n" +
                    "    background-color: #f2f2f2;\n" +
                    "}\n" +
                    "\n" +
                    "table tbody tr:hover {\n" +
                    "    background-color: #d1d1d1;\n" +
                    "}\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n\n" +
                    "<h2> Welcome " + session.getAttribute("username") + "</h2>" +
                    "<a href=\"./logout\" >LOGOUT</a>"+
                    "<h2 style = \"text-align: center; color: #533535; background-color: #fff; padding: 10px;\"> ATTENDANCE RECORDS </h2>\n"
            );
            print.write(attendanceBean.attendanceRecord());

            print.write("\n" +
                    "</body>\n" +
                    "</html>");

        } else
            resp.sendRedirect("./");


    }
}
