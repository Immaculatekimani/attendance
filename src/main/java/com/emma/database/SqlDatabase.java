package com.emma.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

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
}
