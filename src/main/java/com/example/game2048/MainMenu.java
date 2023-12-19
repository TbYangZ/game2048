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
    public static String imagePath;
    public static Scene build(double width, double height) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainMenu.class.getResource("main_menu.fxml"));
        Parent root = loader.load();
        if (imagePath != null) {
            System.out.println("Image Path = " + imagePath);
            root.setStyle("-fx-background-image: url('" + imagePath + "');" +
                    "-fx-background-size: contain;" +
                    "-fx-background-position: center center;");
        }
        scene = new Scene(root, width, height);
        controller = loader.getController();
        return scene;
    }
}
