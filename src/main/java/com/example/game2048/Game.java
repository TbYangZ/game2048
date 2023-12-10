package com.example.game2048;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Game {
    public static Scene scene;
    public static ControllerMainGame controller;
    public static Scene build(double width, double height) throws IOException {
        FXMLLoader loader = new FXMLLoader(Game.class.getResource("game-view.fxml"));
        Parent root = loader.load();
        scene = new Scene(root, width, height);
        controller = loader.getController();
        scene.setOnKeyPressed(keyEvent -> {
            onKeyPressed(keyEvent);
        });
        return scene;
    }
    private static void onKeyPressed(KeyEvent event) {
        String s = event.getText();
        System.out.println(s);
    }
}
