package com.example.game2048;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.ParallelCamera;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MainMenu {
    public static Scene scene;
    public static ControllerMainMenu controller;
    public static Scene build(double width, double height) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainMenu.class.getResource("main_menu.fxml"));
        Parent root = loader.load();
        scene = new Scene(root, width, height);
        scene.getStylesheets().add(MainMenu.class.getResource("main_bg.css").toExternalForm());
        controller = loader.getController();
        return scene;
    }
}
