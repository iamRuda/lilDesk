package com.iamruda.lildesk;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class AddElemController {
    @FXML private SplitMenuButton chooser_table;
    @FXML private VBox values;
    private String current_table;
    private ArrayList<TextField> listTextField = new ArrayList<>();

    @FXML
    public void initialize() {
        ;
    }

    private ArrayList<String> getSQLFields() throws ClassNotFoundException,
            SQLException {

        // Получаем соединение к базе данных MySQL.
        Connection conn = MySQLConnUtils.getMySQLConnection();

        // Создаем объект Statement для выполнения запросов.
        Statement statement = conn.createStatement();

        // Выполняем запрос SELECT
        String sql = "SELECT * FROM " + current_table;
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();

        ArrayList<String> listField = new ArrayList<>();

        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            listField.add(columnName);
        }

        // Закрываем соединение к базе данных.
        conn.close();
        return listField;
    }

    public void fieldSetter() throws SQLException, ClassNotFoundException {
        ArrayList<String> listField = getSQLFields();

        listTextField = new ArrayList<>();
        values.getChildren().clear();

        for (int i = 1; i < listField.size(); i++) {
            // Создаем HBox с указанной структурой
            HBox hbox = fieldValue(listField.get(i));
            values.getChildren().add(hbox);
        }
    }

    private HBox fieldValue(String nameField) {
        HBox hbox = new HBox();
        hbox.setAlignment(javafx.geometry.Pos.CENTER);
        hbox.setSpacing(10);

        Label label = new Label(nameField);
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setPrefHeight(22.0);
        label.setPrefWidth(166.0);
        label.setFont(new Font(14.0));

        TextField textField = new TextField();
        listTextField.add(textField);
        textField.setPrefHeight(26.0);
        textField.setPrefWidth(326.0);

        hbox.setMargin(textField, new javafx.geometry.Insets(4, 0, 4, 0));
        hbox.getChildren().addAll(label, textField);

        return hbox;
    }

    public void chooseTable(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String menuItemText = ((MenuItem)event.getSource()).getText();
        this.current_table = ((MenuItem)event.getSource()).getId();
        chooser_table.setText(menuItemText);
        fieldSetter();
        System.out.println(this.current_table);
    }

    public void handleAddButton(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        if (current_table != null) {
            // Получаем соединение к базе данных MySQL.
            Connection conn = MySQLConnUtils.getMySQLConnection();

            // Создаем объект Statement для выполнения запросов.
            Statement statement = conn.createStatement();

            // Выполняем запрос SELECT
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + current_table);
            ResultSetMetaData metaData = resultSet.getMetaData();
            String idColumn = metaData.getColumnName(1);

            // Выполняем запрос SELECT.
            ResultSet resultCountSet = statement.executeQuery("SELECT MAX(" + idColumn + ") AS " + idColumn + " FROM " + current_table);
            resultCountSet.next();
            int countRows = resultCountSet.getInt(1) + 1;

            ArrayList<String> listValues = new ArrayList<>();
            for (int i = 0; i < listTextField.size(); i++) {
                String field = listTextField.get(i).getText();
                if (!(field.matches("[0-9\\+]"))) {   // проверяем на наличие символов, кроме цифр
                    field = "'" + field + "'";
                }
                listValues.add(field);
            }

            String strValues = countRows + ", ";
            for (int i = 0; i < listValues.size(); i++) {
                strValues += listValues.get(i);
                if (i != listValues.size() - 1) {
                    strValues += ", ";
                }
            }

            String sql = "INSERT INTO " + current_table + " VALUES " + "(" + strValues + ");";
            System.out.println(sql);
            statement.executeUpdate(sql);

            // Закрываем соединение к базе данных.
            conn.close();

            handleCloseButton(event);
        } else {
            Node source = (Node) event.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            String strError = "Ошибка. Добавить запись не удалось.";
            ErrorPopup(currentStage, strError);
        }
    }

    public void handleCloseButton(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        currentStage.close();
    }

    private void ErrorPopup(Stage currentStage, String strError) {
        final Stage dialog = new Stage();
        dialog.setTitle("Ошибка");
        dialog.setResizable(false);
        dialog.getIcons().add(new Image(getClass().getResourceAsStream("lildesk.png")));
        dialog.initModality(Modality.NONE);
        dialog.initStyle(StageStyle.DECORATED);
        dialog.initOwner(currentStage);

        VBox root = new VBox();
        root.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        root.setStyle("-fx-background-color: #333;");
        root.setPadding(new Insets(16));

        HBox header = new HBox();
        header.setPrefSize(226, 51);
        Image image = new Image(getClass().getResourceAsStream("sad.png"));
        ImageView errorIcon = new ImageView();
        errorIcon.setImage(image);
        errorIcon.setFitWidth(52);
        errorIcon.setFitHeight(52);
        errorIcon.setPreserveRatio(true);
        errorIcon.setPickOnBounds(true);
        errorIcon.setEffect(new BoxBlur(1.5, 1.5, 1));
        Label errorLabel = new Label(strError);
        errorLabel.setWrapText(true);
        errorLabel.setPrefSize(172, 69);
        errorLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        errorLabel.setFont(Font.font(14));
        HBox.setMargin(errorLabel, new Insets(0, 0, 0, 12));
        header.getChildren().addAll(errorIcon, errorLabel);

        HBox buttonBox = new HBox();
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
        buttonBox.setPrefSize(250, 46);
        Button okButton = new Button("Хорошо");
        okButton.setPrefSize(70, 16);
        okButton.setAlignment(javafx.geometry.Pos.CENTER);
        okButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        okButton.setOnAction(event -> currentStage.close());
        buttonBox.getChildren().add(okButton);

        root.getChildren().addAll(header, buttonBox);

        Scene dialogScene = new Scene(root, 264, 118);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
