package com.emma.database;

import com.emma.app.model.Attendance;
import com.emma.app.model.Employee;
import com.emma.app.model.User;
import com.emma.database.helper.DbTable;
import com.emma.database.helper.DbTableColumn;
import com.emma.database.helper.DbTableId;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SqlDatabase implements Serializable {
    private static SqlDatabase database;
    private Connection connection;

    private SqlDatabase() throws SQLException, NamingException {
        Context ctx = new InitialContext();
        DataSource dataSource = (DataSource) ctx.lookup("java:jboss/datasources/attendance");
        connection = dataSource.getConnection();
    }

    public static SqlDatabase getInstance() throws SQLException {
        if (database == null) {
            try {
                database = new SqlDatabase();

            } catch (SQLException | NamingException e) {
                throw new RuntimeException(e);
            }
        }

        return database;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static void updateScheme() {
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
                List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
                fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

                for (Field field : fields) {
                    if (!field.isAnnotationPresent(DbTableColumn.class))
                        continue;
                    DbTableColumn dbTableColumn = field.getAnnotation(DbTableColumn.class);
                    sqlBuilder.append(dbTableColumn.name()).append(" ").append(dbTableColumn.definition());

                    // Add additional constraints for primary key and AUTO_INCREMENT
                    if (field.isAnnotationPresent(DbTableId.class)) {
                        sqlBuilder.append(" NOT NULL AUTO_INCREMENT PRIMARY KEY");
                    }

                    sqlBuilder.append(",");
                }
                sqlBuilder.append(")");

                connection.prepareStatement(sqlBuilder.toString().replace(",)", ")")).executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public static void insert(Object entity) {
        try {
            Class<?> clazz = entity.getClass();
            if (!clazz.isAnnotationPresent(DbTable.class))
                return;
            DbTable dbTable = clazz.getAnnotation(DbTable.class);

            List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

            StringBuilder columnBuilder = new StringBuilder();
            StringBuilder placeHolder = new StringBuilder();
            List<Object> parameters = new ArrayList<>();

            for (Field field : fields) {
                if (!field.isAnnotationPresent(DbTableColumn.class) || field.isAnnotationPresent(DbTableId.class))
                    continue;
                field.setAccessible(true);
                if (field.get(entity) == null)
                    continue;
                DbTableColumn dbTableColumn = field.getAnnotation(DbTableColumn.class);
                columnBuilder.append(dbTableColumn.name()).append(",");
                placeHolder.append("?").append(",");

                if (field.getType() == LocalDate.class) {
                    parameters.add(java.sql.Date.valueOf((LocalDate) field.get(entity)));
                } else if (field.getType() == LocalTime.class) {
                    parameters.add(java.sql.Time.valueOf((LocalTime) field.get(entity)));
                } else {
                    parameters.add(field.get(entity));
                }
            }

            String queryBuilder = "insert into " + dbTable.name() + "(" + columnBuilder +
                    ") values(" + placeHolder + ")";
            String sqlQuery = queryBuilder.replace(",)", ")");

            try (PreparedStatement statement = SqlDatabase.getInstance().getConnection().prepareStatement(sqlQuery)) {
                int paramId = 1;
                for (Object param : parameters) {
                    if (param instanceof Enum) {
                        statement.setString(paramId++, ((Enum<?>) param).name());
                    } else if (param.getClass().isAssignableFrom(BigDecimal.class)) {
                        statement.setBigDecimal(paramId++, (BigDecimal) param);
                    } else if (param.getClass().isAssignableFrom(Long.class)) {
                        statement.setLong(paramId++, (long) param);
                    } else if (param.getClass().isAssignableFrom(java.sql.Date.class)) {
                        statement.setDate(paramId++, (java.sql.Date) param);
                    } else if (param.getClass().isAssignableFrom(java.sql.Time.class)) {
                        statement.setTime(paramId++, (java.sql.Time) param);
                    } else {
                        statement.setString(paramId++, (String) param);
                    }
                }
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> select(Class<T> filter, String whereClause, Object... parameters) {
        try {
            Class<?> clazz = filter;
            System.out.println();

            if (!clazz.isAnnotationPresent(DbTable.class))
                return new ArrayList<>();

            DbTable dbTable = clazz.getAnnotation(DbTable.class);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT * FROM ").append(dbTable.name());

            if (whereClause != null && !whereClause.isEmpty()) {
                stringBuilder.append(" WHERE ").append(whereClause);
            }

            stringBuilder.append(";");

            Connection conn = SqlDatabase.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(stringBuilder.toString());

            // Set parameters for the WHERE clause
            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> result = new ArrayList<>();

            while (resultSet.next()) {
                T object = (T) clazz.getDeclaredConstructor().newInstance();

                for (Field field : clazz.getDeclaredFields()) {
                    DbTableColumn dbColumn = field.getAnnotation(DbTableColumn.class);
                    if (dbColumn != null) {
                        String columnName = dbColumn.name();

                        Object value = resultSet.getObject(columnName);

                        // Convert java.sql.Date to java.time.LocalDate if needed
                        if (value instanceof java.sql.Date && field.getType().equals(java.time.LocalDate.class)) {
                            value = ((java.sql.Date) value).toLocalDate();
                        }

                        // Convert java.sql.Time to java.time.LocalTime if needed
                        if (value instanceof java.sql.Time && field.getType().equals(java.time.LocalTime.class)) {
                            value = ((java.sql.Time) value).toLocalTime();
                        }

                        // Convert String to enum if needed
                        if (field.getType().isEnum() && value instanceof String) {
                            value = Enum.valueOf((Class<Enum>) field.getType(), (String) value);
                        }

                        field.setAccessible(true);
                        field.set(object, value);
                    }
                }

                result.add(object);
            }
            return result;

        } catch (SQLException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static void update(Object entity, String idFieldName) {
        try {
            Class<?> clazz = entity.getClass();
            if (!clazz.isAnnotationPresent(DbTable.class))
                return;

            DbTable dbTable = clazz.getAnnotation(DbTable.class);

            List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

            StringBuilder setClause = new StringBuilder();
            List<Object> parameters = new ArrayList<>();

            for (Field field : fields) {
                if (!field.isAnnotationPresent(DbTableColumn.class) || field.isAnnotationPresent(DbTableId.class))
                    continue;

                field.setAccessible(true);
                if (field.get(entity) == null)
                    continue;

                DbTableColumn dbTableColumn = field.getAnnotation(DbTableColumn.class);
                setClause.append(dbTableColumn.name()).append(" = ?, ");

                if (field.getType() == LocalDate.class) {
                    parameters.add(java.sql.Date.valueOf((LocalDate) field.get(entity)));
                } else if (field.getType() == LocalTime.class) {
                    parameters.add(java.sql.Time.valueOf((LocalTime) field.get(entity)));
                } else {
                    parameters.add(field.get(entity));
                }
            }

            // Remove the trailing comma and space
            setClause.setLength(setClause.length() - 2);

            String queryBuilder = "UPDATE " + dbTable.name() + " SET " + setClause +
                    " WHERE " + idFieldName + " = ?";
            String sqlQuery = queryBuilder.replace(",)", ")");

            try (PreparedStatement statement = SqlDatabase.getInstance().getConnection().prepareStatement(sqlQuery)) {
                int paramId = 1;
                for (Object param : parameters) {
                    if (param instanceof Enum) {
                        statement.setString(paramId++, ((Enum<?>) param).name());
                    } else if (param.getClass().isAssignableFrom(BigDecimal.class)) {
                        statement.setBigDecimal(paramId++, (BigDecimal) param);
                    } else if (param.getClass().isAssignableFrom(Long.class)) {
                        statement.setLong(paramId++, (long) param);
                    } else if (param.getClass().isAssignableFrom(java.sql.Date.class)) {
                        statement.setDate(paramId++, (java.sql.Date) param);
                    } else if (param.getClass().isAssignableFrom(java.sql.Time.class)) {
                        statement.setTime(paramId++, (java.sql.Time) param);
                    } else {
                        statement.setString(paramId++, (String) param);
                    }
                }

                // Set the value for the WHERE clause based on the ID field
                Field idField = clazz.getDeclaredField(idFieldName);
                idField.setAccessible(true);
                statement.setObject(paramId, idField.get(entity));

                statement.executeUpdate();
            } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
