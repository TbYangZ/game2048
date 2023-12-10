package com.example.game2048;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.IOException;
import java.util.Collection;

public class ControllerMainMenu {
    public Label titleLabel;
    public StackPane mainRoot;
    public Button loginButton, registerButton, gameStartButton, settingsButton, quitButton;
    public void initialize() {
        mainRoot.widthProperty().addListener((observable, oldValue, newValue) -> {
            titleLabel.setStyle("-fx-font-size: " + newValue.doubleValue() / 20);
            loginButton.setPrefWidth(newValue.doubleValue() / 10);
            registerButton.setPrefWidth(newValue.doubleValue() / 10);
            gameStartButton.setPrefWidth(loginButton.getPrefWidth() + registerButton.getPrefWidth() + 10);
            settingsButton.setPrefWidth(loginButton.getPrefWidth() + registerButton.getPrefWidth() + 10);
            quitButton.setPrefWidth(loginButton.getPrefWidth() + registerButton.getPrefWidth() + 10);

            loginButton.setStyle("-fx-font-size: " + loginButton.fontProperty().get().getSize() * newValue.doubleValue() / oldValue.doubleValue());
            registerButton.setStyle("-fx-font-size: " + registerButton.fontProperty().get().getSize() * newValue.doubleValue() / oldValue.doubleValue());
            gameStartButton.setStyle("-fx-font-size: " + gameStartButton.fontProperty().get().getSize() * newValue.doubleValue() / oldValue.doubleValue());
            settingsButton.setStyle("-fx-font-size: " + settingsButton.fontProperty().get().getSize() * newValue.doubleValue() / oldValue.doubleValue());
            quitButton.setStyle("-fx-font-size: " + quitButton.fontProperty().get().getSize() * newValue.doubleValue() / oldValue.doubleValue());
        });
        mainRoot.widthProperty().addListener((observable, oldValue, newValue) ->{
            titleLabel.setStyle("-fx-font-size: " + newValue.doubleValue() / 20);
            loginButton.setPrefHeight(newValue.doubleValue() / 20);
            registerButton.setPrefHeight(newValue.doubleValue() / 20);
            gameStartButton.setPrefHeight(newValue.doubleValue() / 20);
            settingsButton.setPrefHeight(newValue.doubleValue() / 20);
            quitButton.setPrefHeight(newValue.doubleValue() / 20);
        });
    }

    public void clickGameStart() throws IOException {
        Scene newScene = Game.build(800, 450);
        Scene currentScene = mainRoot.getScene();
        Stage currentStage = (Stage)currentScene.getWindow();
        currentStage.setScene(newScene);
    }

    public void clickSetting() throws IOException {

    }

    public void clickLogin() throws IOException {

    }

    public void clickRegister() throws IOException {

    }

    public void clickQuit() { Platform.exit(); }
}
