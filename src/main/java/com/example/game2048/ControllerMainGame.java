package com.example.game2048;

import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerMainGame {

    public ImageView main_bg, game_over_bg, game_over;
    public BorderPane mainRoot;
    public ImageView cell00, cell01, cell02, cell03;
    public ImageView cell10, cell11, cell12, cell13;
    public ImageView cell20, cell21, cell22, cell23;
    public ImageView cell30, cell31, cell32, cell33;
    public ImageView[][] cells = new ImageView[4][4];
    public Button back;
    public Button save;
    public Button load;
    public Button retry;
    public Button clear;
    public ImageView score_bg1;
    public ImageView score_bg2;
    public ImageView score_digit1, score_digit2, score_digit3, score_digit4, score_digit5;
    public ImageView max_score_digit1, max_score_digit2, max_score_digit3, max_score_digit4, max_score_digit5;
    public Text score;
    public Text max_score;

    public void initialize() {
        int basicX = 200;
        int basicY = 25;
        int cellWidth = 95;
        main_bg.setX(basicX);
        main_bg.setY(basicY);
        game_over_bg.setX(basicX);
        game_over_bg.setY(basicY);
        game_over.setX(basicX);
        game_over.setY(basicY);
        cells[0][0] = cell00; cells[0][1] = cell01; cells[0][2] = cell02; cells[0][3] = cell03;
        cells[1][0] = cell10; cells[1][1] = cell11; cells[1][2] = cell12; cells[1][3] = cell13;
        cells[2][0] = cell20; cells[2][1] = cell21; cells[2][2] = cell22; cells[2][3] = cell23;
        cells[3][0] = cell30; cells[3][1] = cell31; cells[3][2] = cell32; cells[3][3] = cell33;
        for (int i = 0 ; i < 4; ++i)
            for (int j = 0; j < 4; ++j) {
                int x = basicX + (j + 1) * 4 + j * cellWidth;
                int y = basicY + (i + 1) * 4 + i * cellWidth;
                cells[i][j].setX(x);
                cells[i][j].setY(y);
                clearCell(i, j);
            }

        game_over_bg.setOpacity(0);
        game_over.setOpacity(0);

        back.setFocusTraversable(false);
        save.setFocusTraversable(false);
        load.setFocusTraversable(false);
        retry.setFocusTraversable(false);
        clear.setFocusTraversable(false);

        score_digit1.setPreserveRatio(true);
        score_digit2.setPreserveRatio(true);
        score_digit3.setPreserveRatio(true);
        score_digit4.setPreserveRatio(true);
        score_digit5.setPreserveRatio(true);

        score_digit1.setFitWidth(30);
        score_digit2.setFitWidth(30);
        score_digit3.setFitWidth(30);
        score_digit4.setFitWidth(30);
        score_digit5.setFitWidth(30);

        double x0 = 625, width = 30, y0 = 100;

        score_digit1.setLayoutX(x0);
        score_digit2.setLayoutX(x0 + width);
        score_digit3.setLayoutX(x0 + width * 2);
        score_digit4.setLayoutX(x0 + width * 3);
        score_digit5.setLayoutX(x0 + width * 4);

        score_digit1.setY(y0);
        score_digit2.setY(y0);
        score_digit3.setY(y0);
        score_digit4.setY(y0);
        score_digit5.setY(y0);
        score.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        score.setX(x0 + 0.6 * width);
        score.setY(y0 - 20);

        score_bg1.setPreserveRatio(false);
        score_bg1.setFitWidth(165);
        score_bg1.setFitHeight(100);
        score_bg1.setX(x0 - 10);
        score_bg1.setY(y0 - 20 - 10 - 20);
        score_bg1.toBack();


        y0 = 300;

        max_score_digit1.setPreserveRatio(true);
        max_score_digit2.setPreserveRatio(true);
        max_score_digit3.setPreserveRatio(true);
        max_score_digit4.setPreserveRatio(true);
        max_score_digit5.setPreserveRatio(true);

        max_score_digit1.setFitWidth(30);
        max_score_digit2.setFitWidth(30);
        max_score_digit3.setFitWidth(30);
        max_score_digit4.setFitWidth(30);
        max_score_digit5.setFitWidth(30);

        max_score_digit1.setLayoutX(x0);
        max_score_digit2.setLayoutX(x0 + width);
        max_score_digit3.setLayoutX(x0 + width * 2);
        max_score_digit4.setLayoutX(x0 + width * 3);
        max_score_digit5.setLayoutX(x0 + width * 4);

        max_score_digit1.setY(y0);
        max_score_digit2.setY(y0);
        max_score_digit3.setY(y0);
        max_score_digit4.setY(y0);
        max_score_digit5.setY(y0);
        max_score.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        max_score.setX(x0 + width);
        max_score.setY(y0 - 20);

        score_bg2.setPreserveRatio(false);
        score_bg2.setFitWidth(165);
        score_bg2.setFitHeight(100);
        score_bg2.setX(x0 - 10);
        score_bg2.setY(y0 - 20 - 10 - 20);
        score_bg2.toBack();

    }
    public void clearCell(int x, int y) {
        String imgPath = getClass().getResource("null_cell.png").toExternalForm();
        cells[x][y].setImage(new Image(imgPath));
    }
    public void setCell(int x, int y, int number) {
        String imgPath = getClass().getResource("number_" + number + ".png").toExternalForm();
        cells[x][y].setImage(new Image(imgPath));
    }
    public void onBackButtonClick() throws IOException {
        Game.saveBestScore();
        Scene newScene = MainMenu.build(800, 450);
        Scene currentScene = mainRoot.getScene();
        Stage currentStage = (Stage)currentScene.getWindow();
        currentStage.setScene(newScene);
    }
    public void onSaveButtonClick() {
        Game.save();
    }
    public void onLoadButtonClick() {
        Game.load();
    }
    public void onRetryButtonClick() { Game.saveBestScore(); Game.board.initialize(); Game.recoveryBoard(); }
    public void setScore(int _score) {
        int d1, d2, d3, d4, d5;
        d5 = _score % 10; _score /= 10;
        d4 = _score % 10; _score /= 10;
        d3 = _score % 10; _score /= 10;
        d2 = _score % 10; _score /= 10;
        d1 = _score % 10; _score /= 10;
        score_digit5.setViewport(new Rectangle2D(d5 * 192, 0, 192, 257));
        score_digit4.setViewport(new Rectangle2D(d4 * 192, 0, 192, 257));
        score_digit3.setViewport(new Rectangle2D(d3 * 192, 0, 192, 257));
        score_digit2.setViewport(new Rectangle2D(d2 * 192, 0, 192, 257));
        score_digit1.setViewport(new Rectangle2D(d1 * 192, 0, 192, 257));
    }

    public void setMaxScore(int _score) {
        int d1, d2, d3, d4, d5;
        d5 = _score % 10; _score /= 10;
        d4 = _score % 10; _score /= 10;
        d3 = _score % 10; _score /= 10;
        d2 = _score % 10; _score /= 10;
        d1 = _score % 10; _score /= 10;
        max_score_digit5.setViewport(new Rectangle2D(d5 * 192, 0, 192, 257));
        max_score_digit4.setViewport(new Rectangle2D(d4 * 192, 0, 192, 257));
        max_score_digit3.setViewport(new Rectangle2D(d3 * 192, 0, 192, 257));
        max_score_digit2.setViewport(new Rectangle2D(d2 * 192, 0, 192, 257));
        max_score_digit1.setViewport(new Rectangle2D(d1 * 192, 0, 192, 257));
    }

    public void onClearButtonClick() {
        Game.bestScore = 0;
        setMaxScore(0); Game.board.initialize();
        Game.recoveryBoard();
    }
}