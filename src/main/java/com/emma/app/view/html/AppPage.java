package com.emma.app.view.html;

import com.emma.app.view.css.AppCss;
import com.emma.app.view.toolbar.TopBar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public class AppPage implements Serializable {
    public void renderHtml(HttpServletRequest req, HttpServletResponse res, int activeMenu, String content) throws IOException{
        HttpSession session = req.getSession();
        PrintWriter print = res.getWriter();
        print.write("<!DOCTYPE html>" +
                "<html>"+
                "            <head>" +
                new AppCss().getStyle()+
                "</head>" +
                "<body>" +
                new TopBar().menu(activeMenu)+
                "<h2> Welcome " + session.getAttribute("username") + "</h2>" +

                "<h2 style = \"text-align: center; color: #533535; background-color: #fff; padding: 10px;\"> ATTENDANCE RECORDS </h2>");
        print.write(content);
        print.write("<a href=\"./logout\" >LOGOUT</a>"+
                "</body>"+
                "</html>");
    }
}
