package com.example.game2048;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerMainGame {

    public ImageView main_bg;
    public BorderPane mainRoot;
    public ImageView cell00, cell01, cell02, cell03;
    public ImageView cell10, cell11, cell12, cell13;
    public ImageView cell20, cell21, cell22, cell23;
    public ImageView cell30, cell31, cell32, cell33;
    public ImageView[][] cells = new ImageView[4][4];
    private final int basicX = 200;
    private final int basicY = 25;
    private final int cellWidth = 95;
    public Button saveBack;

    public void initialize() {
        main_bg.setLayoutX(basicX);
        main_bg.setLayoutY(basicY);
        cells[0][0] = cell00; cells[0][1] = cell01; cells[0][2] = cell02; cells[0][3] = cell03;
        cells[1][0] = cell10; cells[1][1] = cell11; cells[1][2] = cell12; cells[1][3] = cell13;
        cells[2][0] = cell20; cells[2][1] = cell21; cells[2][2] = cell22; cells[2][3] = cell23;
        cells[3][0] = cell30; cells[3][1] = cell31; cells[3][2] = cell32; cells[3][3] = cell33;
        for (int i = 0 ; i < 4; ++i)
            for (int j = 0; j < 4; ++j) {
                int x = basicX + (j + 1) * 4 + j * cellWidth;
                int y = basicY + (i + 1) * 4 + i * cellWidth;
                cells[i][j].setLayoutX(x);
                cells[i][j].setLayoutY(y);
                clearCell(i, j);
            }
        setCell(0, 0, 2);
//        borderPane.requestFocus();
    }
    public void clearCell(int x, int y) {
        String imgPath = getClass().getResource("null_cell.png").toExternalForm();
        cells[x][y].setImage(new Image(imgPath));
    }
    public void setCell(int x, int y, int number) {
        String imgPath = getClass().getResource("number_" + number + ".png").toExternalForm();
        cells[x][y].setImage(new Image(imgPath));
    }
    public void moveDown() {
        System.out.println("Hello!");
    }
    public void onBackButtonClick() throws IOException {
        Scene newScene = MainMenu.build(800, 450);
        Scene currentScene = mainRoot.getScene();
        Stage currentStage = (Stage)currentScene.getWindow();
        currentStage.setScene(newScene);
    }
}
