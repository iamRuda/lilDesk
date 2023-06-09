package com.iamruda.lildesk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 300);
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("lildesk.png")));
        stage.setTitle("Добро пожаловать");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}