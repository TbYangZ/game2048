package com.example.game2048;

import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.IOException;

public class ControllerMainMenu {
    public Label titleLabel;
    public StackPane mainRoot;
    public Button gameStartButton, settingsButton, quitButton;
    public void initialize() {
        mainRoot.widthProperty().addListener((observable, oldValue, newValue) -> {
            titleLabel.setStyle("-fx-font-size: " + newValue.doubleValue() / 20);
            gameStartButton.setPrefWidth(newValue.doubleValue() / 10 + newValue.doubleValue() / 10 + 10);
            settingsButton.setPrefWidth(newValue.doubleValue() / 10 + newValue.doubleValue() / 10 + 10);
            quitButton.setPrefWidth(newValue.doubleValue() / 10 + newValue.doubleValue() / 10 + 10);

            gameStartButton.setStyle("-fx-font-size: " + gameStartButton.fontProperty().get().getSize() * newValue.doubleValue() / oldValue.doubleValue());
            settingsButton.setStyle("-fx-font-size: " + settingsButton.fontProperty().get().getSize() * newValue.doubleValue() / oldValue.doubleValue());
            quitButton.setStyle("-fx-font-size: " + quitButton.fontProperty().get().getSize() * newValue.doubleValue() / oldValue.doubleValue());
        });
        mainRoot.widthProperty().addListener((observable, oldValue, newValue) ->{
            titleLabel.setStyle("-fx-font-size: " + newValue.doubleValue() / 20);
            gameStartButton.setPrefHeight(newValue.doubleValue() / 20);
            settingsButton.setPrefHeight(newValue.doubleValue() / 20);
            quitButton.setPrefHeight(newValue.doubleValue() / 20);
        });
    }

    public void clickGameStart() throws IOException {
        Scene newScene = Game.build(800, 450);
        Scene currentScene = mainRoot.getScene();
        Stage currentStage = (Stage) currentScene.getWindow();
        currentStage.setScene(newScene);
    }

    public void clickSetting() throws IOException {
        Scene newScene = Settings.build(800, 450);
        Scene currentScene = mainRoot.getScene();
        Stage currentStage = (Stage) currentScene.getWindow();
        currentStage.setScene(newScene);
    }

    public void clickQuit() { Platform.exit(); }
}
