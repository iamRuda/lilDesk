package com.iamruda.lildesk;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils {

    public static Connection getMySQLConnection() throws SQLException,
            ClassNotFoundException {
        String hostName = "localhost";
        String dbName = "agile_db";
        String userName = "root";
        String password = "12345678";
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException,
            ClassNotFoundException {

        // Загружаем драйвер JDBC для MySQL.
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Строка подключения к базе данных MySQL.
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        // Создаем соединение к базе данных.
        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        return conn;
    }
}