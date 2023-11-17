package com.emma.database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlDatabase implements Serializable {

    private static final String URL = "jdbc:mysql://localhost:3306/attendance";
    private static final String USER = "tatu";
    private static final String PASSWORD = "tatu";
    private static SqlDatabase database;
    private Connection connection;

    private SqlDatabase() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static SqlDatabase getInstance() throws SQLException {
        if (database == null)
            database = new SqlDatabase();

        return database;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
