package com.emma.filter;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req =  (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        String servletPath = req.getServletPath();

        if (session.isNew() || StringUtils.isBlank((String) session.getAttribute("loggedInId"))) {
            session.invalidate();
            if(servletPath.equals("/login") || servletPath.contains(".jsp") || servletPath.equals("/user") || servletPath.equals("/invalid-login")){
                filterChain.doFilter(req, res);
            } else {
                res.sendRedirect(req.getContextPath() + "/");
                res.getWriter().flush();
            }
        } else {
            if (session.getAttribute("loggedInId") != null) {
                filterChain.doFilter(req, res);
            }else {
                res.sendRedirect(req.getContextPath() + "/");
                res.getWriter().flush();
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
