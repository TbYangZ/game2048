package com.example.game2048;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private final double Width = 1600.0;
    private final double Height = 900.0;
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Game 2048");
        stage.setScene(MainMenu.build(Width / 2, Height / 2));
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) { launch(); }
}