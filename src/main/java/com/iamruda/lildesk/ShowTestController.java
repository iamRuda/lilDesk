package com.iamruda.lildesk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowTestController {

    @FXML private TableView table;

    public class Person {
        private String firstName;
        private String lastName;
        private String email;

        public Person(String firstName, String lastName, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getEmail() {
            return email;
        }
    }

    public void initialize() {
        test();
    }

    private void test() {
        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        TableColumn<Person, String> emailCol = new TableColumn<>("Email");

        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.getColumns().add(firstNameCol);
        table.getColumns().add(lastNameCol);
        table.getColumns().add(emailCol);

        ObservableList<Person> data = FXCollections.observableArrayList(
                new Person("John", "Doe", "johndoe@example.com"),
                new Person("Jane", "Smith", "janedoe@example.com"),
                new Person("Bob", "Johnson", "bobsmith@example.com"),
                new Person("John", "Doe", "johndoe@example.com"),
                new Person("Jane", "Smith", "janedoe@example.com"),
                new Person("Bob", "Johnson", "bobsmith@example.com"),
                new Person("John", "Doe", "johndoe@example.com"),
                new Person("Jane", "Smith", "janedoe@example.com"),
                new Person("Bob", "Johnson", "bobsmith@example.com"),
                new Person("John", "Doe", "johndoe@example.com"),
                new Person("Jane", "Smith", "janedoe@example.com"),
                new Person("Bob", "Johnson", "bobsmith@example.com"),
                new Person("John", "Doe", "johndoe@example.com"),
                new Person("Jane", "Smith", "janedoe@example.com"),
                new Person("Bob", "Johnson", "bobsmith@example.com"),
                new Person("John", "Doe", "johndoe@example.com"),
                new Person("Jane", "Smith", "janedoe@example.com"),
                new Person("Bob", "Johnson", "bobsmith@example.com")
        );

        table.setItems(data);
    }
}