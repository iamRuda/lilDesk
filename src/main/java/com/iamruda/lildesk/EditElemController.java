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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class EditElemController {
    @FXML private SplitMenuButton chooser_table;
    @FXML private TextField chooser_id;
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

    public void checkId() throws IOException, SQLException, ClassNotFoundException {
        if ((current_table != null) & !(chooser_id.getText().equals("")) & (chooser_id.getText().matches("[-+]?\\d+"))) {
            // Получаем соединение к базе данных MySQL.
            Connection conn = MySQLConnUtils.getMySQLConnection();

            // Создаем объект Statement для выполнения запросов.
            Statement statement = conn.createStatement();

            ResultSet resultIdSet = statement.executeQuery("SELECT * FROM " + current_table);
            ResultSetMetaData metaIdData = resultIdSet.getMetaData();
            String idColumn = metaIdData.getColumnName(1);

            // Выполняем запрос SELECT
            String sql = "SELECT * FROM " + current_table + " WHERE " + idColumn + " = " + chooser_id.getText();
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();

            int columnCount = metaData.getColumnCount();
            if (resultSet.next()) {
                for (int i = 1; i <= columnCount - 1; i++) {
                    String columnName = metaData.getColumnName(i+1);
                    int columnType = metaData.getColumnType(i+1);
                    Object value = resultSet.getObject(columnName); // Получение значения в виде объекта
                    // Проверка и приведение типа в соответствии с фактическим типом данных
                    System.out.println(columnType);
                    if (value instanceof Integer) {
                        int intValue = (int) value;
                        listTextField.get(i-1).setText(Integer.toString(intValue));
                    } else if (columnType == 4) {
                        long intValue = (long) value;
                        listTextField.get(i-1).setText(Long.toString(intValue));
                        // Обработка значения типа int
                    } else if (value instanceof String) {
                        String stringValue = (String) value;
                        listTextField.get(i-1).setText(stringValue);
                        // Обработка значения типа String
                    } else if (value instanceof Double) {
                        double doubleValue = (double) value;
                        listTextField.get(i-1).setText(Double.toString(doubleValue));
                        // Обработка значения типа double
                    } else if (columnType == 93) {
                        LocalDateTime dateValue = (LocalDateTime) value;
                        Date sqlDate = Date.valueOf(dateValue.toLocalDate());
                        listTextField.get(i-1).setText(String.valueOf(sqlDate));
                        // Обработка значения типа double
                    }
                }
            }

            // Закрываем соединение к базе данных.
            conn.close();
        }
    }

    public void chooseTable(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String menuItemText = ((MenuItem)event.getSource()).getText();
        this.current_table = ((MenuItem)event.getSource()).getId();
        chooser_table.setText(menuItemText);
        fieldSetter();
        System.out.println(this.current_table);
    }

    public void handleEditButton(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        if ((current_table != null) & !(chooser_id.getText().equals("")) & (chooser_id.getText().matches("[-+]?\\d+"))) {
            // Получаем соединение к базе данных MySQL.
            Connection conn = MySQLConnUtils.getMySQLConnection();

            // Создаем объект Statement для выполнения запросов.
            Statement statement = conn.createStatement();

            // Выполняем запрос SELECT
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + current_table);
            ResultSetMetaData metaData = resultSet.getMetaData();
            String idColumn = metaData.getColumnName(1);

            // Выполняем запрос SELECT

            ArrayList<String> listFields = new ArrayList<>();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                listFields.add(columnName);
            }

            ArrayList<String> listValues = new ArrayList<>();
            for (int i = 0; i < listTextField.size(); i++) {
                String field = listTextField.get(i).getText();
                if (!(field.matches("[0-9\\+]"))) {   // проверяем на наличие символов, кроме цифр
                    field = "'" + field + "'";
                }
                listValues.add(field);
            }

            String strValues = "";
            for (int i = 0; i < listValues.size(); i++) {
                strValues = strValues + listFields.get(i + 1) + " = " + listValues.get(i);
                if (i != listValues.size() - 1) {
                    strValues += ", ";
                }
            }

            String sql = "UPDATE " + current_table + " SET "  + strValues + " WHERE " + idColumn + " = " + chooser_id.getText() + ";";
            System.out.println(sql);
            statement.executeUpdate(sql);

            // Закрываем соединение к базе данных.
            conn.close();

            handleCloseButton(event);
        } else {
            Node source = (Node) event.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            String strError = "Ошибка. Отредактировать запись не удалось.";
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
        dialog.setResizable(false);
        dialog.setTitle("Ошибка");
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

        Scene dialogScene = new Scene(root, 276, 118);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
