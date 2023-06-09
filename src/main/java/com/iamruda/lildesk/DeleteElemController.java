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

public class DeleteElemController {
    @FXML private SplitMenuButton chooser_table;
    @FXML private TextField chooser_id;
    private String current_table;

    @FXML
    public void initialize() {
        ;
    }

    public void chooseTable(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String menuItemText = ((MenuItem)event.getSource()).getText();
        this.current_table = ((MenuItem)event.getSource()).getId();
        chooser_table.setText(menuItemText);
        System.out.println(this.current_table);
    }

    public void handleDeleteButton(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        if ((current_table != null) & !(chooser_id.getText().equals("")) & (chooser_id.getText().matches("[-+]?\\d+"))) {
            // Получаем соединение к базе данных MySQL.
            Connection conn = MySQLConnUtils.getMySQLConnection();

            // Создаем объект Statement для выполнения запросов.
            Statement statement = conn.createStatement();

            // Выполняем запрос SELECT
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + current_table);
            ResultSetMetaData metaData = resultSet.getMetaData();
            String idColumn = metaData.getColumnName(1);

            String delsql = "DELETE FROM " + current_table + " WHERE " + idColumn + " = " + chooser_id.getText();
            statement.executeUpdate(delsql);
            System.out.println(delsql);
            // Закрываем соединение к базе данных.
            conn.close();

            handleCloseButton(event);
        } else {
            Node source = (Node) event.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            String strError = "Ошибка. Удалить запись не удалось.";
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
