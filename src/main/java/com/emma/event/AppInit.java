package com.emma.event;

import com.emma.app.model.Attendance;
import com.emma.app.model.Employee;
import com.emma.app.model.EmployeeRole;
import com.emma.app.model.User;
import com.emma.database.Database;
import com.emma.database.SqlDatabase;
import com.emma.database.helper.DbTable;
import com.emma.database.helper.DbTableColumn;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class AppInit implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("#################### Init Database ###############");

        try {
            Connection connection = SqlDatabase.getInstance().getConnection();

            List<Class<?>> entities = new ArrayList<>();
            entities.add(User.class);
            entities.add(Employee.class);
            entities.add(Attendance.class);

            for (Class<?> clazz : entities) {
                if (!clazz.isAnnotationPresent(DbTable.class))
                    continue;

                DbTable dbTable = clazz.getAnnotation(DbTable.class);
                StringBuilder sqlBuilder = new StringBuilder();

                sqlBuilder.append("create table if not exists ").append(dbTable.name()).append("(");
                for (Field field : clazz.getDeclaredFields()) {
                    if (!field.isAnnotationPresent(DbTableColumn.class))
                        continue;
                    DbTableColumn dbTableColumn = field.getAnnotation(DbTableColumn.class);
                    sqlBuilder.append(dbTableColumn.name()).append(" ").append(dbTableColumn.definition())
                            .append(",");
                }
                sqlBuilder.append(")");

                connection.prepareStatement(sqlBuilder.toString().replace(",)", ")")).executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("********* App destroyed   *******");
    }


}
