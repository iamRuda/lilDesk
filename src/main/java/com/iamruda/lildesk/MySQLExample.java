package com.iamruda.lildesk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MySQLExample {
    public static class Client {
        private int idClient;
        private String fullName;
        private String review;
        private String contactPhone;
        private String email;

        public Client(int idClient, String fullName, String review, String contactPhone, String email) {
            this.idClient = idClient;
            this.fullName = fullName;
            this.review = review;
            this.contactPhone = contactPhone;
            this.email = email;
        }

        public int getIdClient() {
            return idClient;
        }

        public String getFullName() {
            return fullName;
        }

        public String review() {
            return review;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public String getEmail() {
            return email;
        }
    }
    public static void main(String[] args) throws ClassNotFoundException,
            SQLException {

        // Получаем соединение к базе данных MySQL.
        Connection conn = MySQLConnUtils.getMySQLConnection();

        // Создаем объект Statement для выполнения запросов.
        Statement statement = conn.createStatement();

        // Выполняем запрос SELECT.
        String sql = "SELECT * FROM Client";
        ResultSet resultSet = statement.executeQuery(sql);

        ArrayList<Client> listClient = new ArrayList<>();

        // Обрабатываем результаты запроса.
        while (resultSet.next()) {
            int idClient = resultSet.getInt("IdClient");
            String fullName = resultSet.getString("FullName");
            String review = resultSet.getString("Review");
            String contactPhone = resultSet.getString("ContactPhone");
            String email = resultSet.getString("Email");
            Client newClient = new Client(idClient, fullName, review, contactPhone, email);
            listClient.add(newClient);
        }

        // Закрываем соединение к базе данных.
        System.out.println(listClient);
        conn.close();
    }
}
/*
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
        */