package com.emma.action;

import com.emma.app.model.entity.User;
import com.emma.app.model.entity.UserRole;
import com.emma.database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user")
public class UserAction extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Database database = Database.getDbInstance();

        String username = req.getParameter("new-username");
        String password = req.getParameter("new-password");
        String confirmPassword = req.getParameter("confirmNewPassword");

        if (password.equals(confirmPassword))
            database.getUsers().add(new User("E230", username, password, UserRole.ADMIN));
        else {
            PrintWriter print = resp.getWriter();
            print.write("Sorry passwords don't match please register or login again.");
            resp.sendRedirect("./invalid-login");
        }

        resp.sendRedirect("./");
    }

}
