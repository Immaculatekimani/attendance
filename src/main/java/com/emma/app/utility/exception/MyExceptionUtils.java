package com.emma.app.utility.exception;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyExceptionUtils {
    public static void throwNotFoundException(String entity, String id) {
        throw new CustomNotFoundException(entity + " not found with ID: " + id);
    }
    public static void throwNotFoundException(String message) {
        throw new CustomNotFoundException(message);
    }
    public static void redirectToErrorPage(HttpServletRequest req, HttpServletResponse resp, Exception e) throws ServletException, IOException {
        String errorMessage = e.getMessage();
        req.setAttribute("errorMessage", errorMessage);
        RequestDispatcher dispatcher = req.getRequestDispatcher("./errorPage");
        dispatcher.forward(req, resp);
    }

}
