package com.example.game2048;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ParallelCamera;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerMainMenu {
    @FXML
    private Label titleLabel;
    @FXML
    private VBox mainRoot;
    @FXML
    private Button loginButton, registerButton, gameStartButton, settingsButton, quitButton;
    @FXML
    private void initialize() {
        mainRoot.widthProperty().addListener((observable, oldValue, newValue) -> {
            titleLabel.setStyle("-fx-font-size: " + newValue.doubleValue() / 20);
//            loginButton.setScaleY();
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
    @FXML
    private void clickGameStart() throws IOException {
//        FXMLLoader loader = new FXMLLoader(ControllerMainMenu.class.getResource("game-view.fxml"));
//        Parent secondRoot = loader.load();
//        mainRoot.getChildren().setAll(secondRoot);
    }
    @FXML
    private void clickSetting() throws IOException {

    }
    @FXML
    private void clickLogin() throws IOException {

    }
    @FXML
    private void clickRegister() throws IOException {

    }
    @FXML
    private void clickQuit() throws IOException {

    }
}
