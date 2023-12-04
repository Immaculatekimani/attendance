package com.emma.app.action;

import com.emma.app.bean.AttendanceBeanI;
import com.emma.app.bean.AttendanceObserver;
import com.emma.app.bean.EmployeeBeanI;
import com.emma.app.model.Attendance;
import com.emma.app.model.AttendanceEvent;
import com.emma.app.model.Employee;
import com.emma.app.model.EmployeeRole;
import com.emma.app.utility.EmployeeTypeConverter;
import com.emma.app.view.helper.HtmlComponent;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


public class BaseAction extends HttpServlet {
    @Inject
    private EmployeeTypeConverter employeeTypeConverter;

    @Override
    public void init() throws ServletException {
        super.init();

        ConvertUtils.register(employeeTypeConverter, EmployeeRole.class);
    }

    @EJB
    AttendanceBeanI attendanceBean;
    @EJB
    EmployeeBeanI employeeBean;
    @EJB
    AttendanceObserver observer;

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
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime displayTime = LocalTime.parse(currentTime.format(formatter), formatter);
        LocalDate currentDate = LocalDate.now();

        List<Employee> allEmployees = employeeBean.list(Employee.class, "");
        List<Attendance> allAttendances = attendanceBean.list(Attendance.class, "");
        List<Attendance> todaysAttendances = allAttendances.stream()
                .filter(attendance -> attendance.getAttendanceDate().equals(currentDate))
                .collect(Collectors.toList());
        List<AttendanceEvent> events = observer.attendanceLogs(AttendanceObserver.class, "");
        System.out.println("$$$$$$$$$$$$$$$$$" +events);
        request.setAttribute("attendanceLogs", events);
        request.setAttribute("allEmployees", allEmployees);
        request.setAttribute("todaysAttendances", todaysAttendances);
        request.setAttribute("displayTime", displayTime);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./app/attendanceSheet.jsp");
        dispatcher.forward(request, response);
    }
}
