package com.emma.app.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/errorPage")
public class ErrorAction extends BaseAction {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("./app/errorPage.jsp");
        dispatcher.forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String errorMessage = req.getParameter("errorMessage");

        if (errorMessage == null) {
            errorMessage = "OOOPs!!! Something went wrong. Please try again";
        }

        req.setAttribute("errorMessage", errorMessage);

        RequestDispatcher dispatcher = req.getRequestDispatcher("./app/errorPage.jsp");
        dispatcher.forward(req, resp);
    }
}
