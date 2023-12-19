package com.example.game2048;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.ParallelCamera;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;

import java.io.IOException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import java.net.URI;
import java.io.File;

public class Settings {
    public static ControllerSettings controller;
    public static Scene scene;
    public static Scene build(double width, double height) throws IOException {
        FXMLLoader loader = new FXMLLoader(Settings.class.getResource("settings.fxml"));
        Parent root = loader.load();
        scene = new Scene(root, width, height);
        controller = loader.getController();
        return scene;
    }

}
