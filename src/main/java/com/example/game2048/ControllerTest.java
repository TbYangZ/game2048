package com.example.game2048;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerTest {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}