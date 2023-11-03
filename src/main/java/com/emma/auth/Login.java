package com.emma.auth;

import com.emma.app.model.entity.User;
import com.emma.database.Database;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;
import java.util.Date;



@WebServlet(urlPatterns = "/login")

public class Login extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        if(httpSession.getAttribute("loggedInId") != null){
            resp.sendRedirect("./home");
        } else
            resp.sendRedirect("./");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Database database =  Database.getDbInstance();

        for (User user : database.getUsers()){
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())){
                HttpSession httpSession = req.getSession(true);

                httpSession.setAttribute("loggedInId", new Date().getTime()+"");
                httpSession.setAttribute("username", username);

                resp.sendRedirect("./home");
            }
        }

        PrintWriter print = resp.getWriter();
        print.write("<html><body>Invalid login details <a href=\".\"> Login again </a></body></html>");


    }

}
