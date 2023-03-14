package com.iamruda.lildesk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLExample {

    public static void main(String[] args) throws ClassNotFoundException,
            SQLException {

        // Получаем соединение к базе данных MySQL.
        Connection conn = MySQLConnUtils.getMySQLConnection();

        // Создаем объект Statement для выполнения запросов.
        Statement statement = conn.createStatement();

        // Выполняем запрос SELECT.
        String sql = "SELECT * FROM Client";
        ResultSet resultSet = statement.executeQuery(sql);

        // Обрабатываем результаты запроса.
        while (resultSet.next()) {
            int id = resultSet.getInt("IdClient");
            String name = resultSet.getString("FullName");
            System.out.println("ID: " + id + ", Name: " + name);
        }

        // Закрываем соединение к базе данных.
        conn.close();
    }
}
/*

        */