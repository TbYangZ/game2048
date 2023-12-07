package com.example.game2048;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ControllerTest {
    @FXML
    private Label welcomeText;
    @FXML
    private VBox secondRoot;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}