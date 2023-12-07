package com.example.game2048;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private final double Width = 1600.0;
    private final double Height = 900.0;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Width / 2, Height / 2);
        stage.setTitle("Game 2048");
        stage.setScene(scene);
        scene.getStylesheets().add(Main.class.getResource("main-bg.css").toExternalForm());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}