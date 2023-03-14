package com.iamruda.lildesk;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    public void handleOpenShowWindow(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("show-view.fxml"));
        String buttonId = ((Button)event.getSource()).getId();
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("lildesk.png")));

        stage.setTitle("Li'l Desk >> Показ базы данных " + buttonId);
        stage.setScene(new Scene(root));
        stage.show();
    }

}