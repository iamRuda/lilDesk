package com.iamruda.lildesk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class ShowController {

    @FXML private TableView table;

    public class Client {
        private int idClient;
        private String fullName;
        private String historyContract;
        private String contactPhone;
        private String email;

        public Client(int idClient, String fullName, String historyContract, String contactPhone, String email) {
            this.idClient = idClient;
            this.fullName = fullName;
            this.historyContract = historyContract;
            this.contactPhone = contactPhone;
            this.email = email;
        }

        public int getIdClient() {
            return idClient;
        }

        public String getFullName() {
            return fullName;
        }

        public String getHistoryContract() {
            return historyContract;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public String getEmail() {
            return email;
        }
    }

    public void initialize() {
        test();
    }

    private void SQLDBClient() throws ClassNotFoundException,
            SQLException {

        // Получаем соединение к базе данных MySQL.
        Connection conn = MySQLConnUtils.getMySQLConnection();

        // Создаем объект Statement для выполнения запросов.
        Statement statement = conn.createStatement();

        // Выполняем запрос SELECT.
        String sql = "SELECT * FROM Client";
        ResultSet resultSet = statement.executeQuery(sql);

        List<Client> newClients = Arrays.asList();

        // Обрабатываем результаты запроса.
        while (resultSet.next()) {
            int id = resultSet.getInt("IdClient");
            String fullName = resultSet.getString("FullName");
            Client newClient = new Client("Иван", "Иванов");
        }

        // Закрываем соединение к базе данных.
        conn.close();
    }

    private void test() {
        TableColumn<Client, String> firstNameCol = new TableColumn<>("First Name");
        TableColumn<Client, String> lastNameCol = new TableColumn<>("Last Name");
        TableColumn<Client, String> emailCol = new TableColumn<>("Email");

        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.getColumns().add(firstNameCol);
        table.getColumns().add(lastNameCol);
        table.getColumns().add(emailCol);

        ObservableList<Client> data = FXCollections.observableArrayList();

        table.setItems(data);
    }
}