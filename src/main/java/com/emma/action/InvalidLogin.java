package com.emma.action;

import com.emma.app.bean.AttendanceBean;
import com.emma.app.bean.AttendanceBeanI;
import com.emma.app.view.html.AppPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/invalid-login")
public class InvalidLogin extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter print = resp.getWriter();
        print.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Invalid Login</title>\n" +
                "<style>" +
                "    body {\n" +
                "    font-family: Arial, sans-serif;\n" +
                "    background-color: #3D0C11;\n" +
                "    margin: 0;\n" +
                "    padding: 0;\n" +
                "    display: flex;\n" +
                "    justify-content: center;\n" +
                "    align-items: center;\n" +
                "    height: 100vh;\n" +
                "}\n" +
                " h1 {\n" +
                "            text-align: center;\n" +
                "            color: #186F65;\n" +
                "   }\n" +
                ".login-container {\n" +
                "    background-color: #F9DEC9;\n" +
                "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);\n" +
                "    border-radius: 5px;\n" +
                "    padding: 20px;\n" +
                "    width: 400px;\n" +
                "}\n" +
                "\n" +
                ".login-container h2 {\n" +
                "    text-align: center;\n" +
                "    color: #068DA9;\n" +
                "}" +
                "    .login-container button {\n" +
                "    background-color: #068DA9;\n" +
                "    color: #fff;\n" +
                "    border: none;\n" +
                "    border-radius: 5px;\n" +
                "    padding: 10px 20px;\n" +
                "    cursor: pointer;\n" +
                "     margin-left: 35%" +
                "}\n" +
                "\n" +
                ".login-container button:hover {\n" +
                "    background-color: #1f77c6;\n" +
                "}\n" +
                "</style>" +

                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<div class=\"login-container\">\n" +
                "    <h2>Invalid Login or Register</h2>\n" +
                "    <p>Your login attempt was unsuccessful. Please check your credentials and try again.</p>\n" +
                "    <button><a href=\"./login\">Back to Login</a></button>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>");
        req.getSession().invalidate();
        resp.getWriter().flush();

    }

}