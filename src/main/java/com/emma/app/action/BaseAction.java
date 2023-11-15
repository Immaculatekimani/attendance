package com.emma.app.action;

import com.emma.app.model.EmployeeRole;
import com.emma.app.view.helper.HtmlComponent;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.List;


public class BaseAction extends HttpServlet {
    static {
        ConvertUtils.register(new EmployeeTypeConverter(), EmployeeRole.class);
    }

    public <T> T serializeForm(Class<?> clazz, Map<String, ?> requestMap) {
        T clazzInstance;

        try {
            clazzInstance = (T) clazz.getDeclaredConstructor().newInstance();
            BeanUtils.populate(clazzInstance, requestMap);
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return clazzInstance;

    }

    public void renderPage(HttpServletRequest request, HttpServletResponse response, int activeMenu, String header, Class<?> entity,
                           List<?> entityList) throws ServletException, IOException {
        request.setAttribute("activeMenu", activeMenu);
        request.setAttribute("header", header);
        request.setAttribute("content", HtmlComponent.table(entityList, entity));
        RequestDispatcher dispatcher = request.getRequestDispatcher("./app/index.jsp");
        dispatcher.forward(request, response);
    }

    public void renderAttendanceSheetPage(HttpServletRequest request, HttpServletResponse response, int activeMenu) throws ServletException, IOException {
        request.setAttribute("activeMenu", activeMenu);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./app/attendanceSheet.jsp");
        dispatcher.forward(request, response);
    }
}
