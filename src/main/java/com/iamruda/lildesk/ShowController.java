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
import java.util.ArrayList;

public class ShowController {

    @FXML private TableView table;

    public class Client {
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

    public void initialize() {
        ;
    }

    private ArrayList<Client> getSQLClients() throws ClassNotFoundException,
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
            System.out.println(review);
            String contactPhone = resultSet.getString("ContactPhone");
            String email = resultSet.getString("Email");
            Client newClient = new Client(idClient, fullName, review, contactPhone, email);
            listClient.add(newClient);
        }

        // Закрываем соединение к базе данных.
        conn.close();
        return listClient;
    }

    public void showSQLClient() throws SQLException, ClassNotFoundException {
        table.getColumns().clear(); // удаление всех столбцов из таблицы
        table.getItems().clear(); // удаление всех элементов из ObservableList

        TableColumn<Client, String> idCol = new TableColumn<>("ID");
        TableColumn<Client, String> fullNameCol = new TableColumn<>("Full name");
        TableColumn<Client, String> reviewCol = new TableColumn<>("Review");
        TableColumn<Client, String> contactCol = new TableColumn<>("Contact phone");
        TableColumn<Client, String> emailCol = new TableColumn<>("Email");

        idCol.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        fullNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        reviewCol.setCellValueFactory(new PropertyValueFactory<>("review"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactPhone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.getColumns().add(idCol);
        table.getColumns().add(fullNameCol);
        table.getColumns().add(reviewCol);
        table.getColumns().add(contactCol);
        table.getColumns().add(emailCol);

        ObservableList<Client> data = FXCollections.observableArrayList(getSQLClients());

        table.setItems(data);
        table.refresh();
    }

    /* private void test() {
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
    } */
}