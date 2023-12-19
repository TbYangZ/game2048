package com.example.game2048;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.io.*;

public class Game {
    public static Scene scene;
    public static ControllerMainGame controller;
    public static Board board;
    public static boolean keyPressed;
    public static boolean haveMoved;
    public static boolean keyEnable;
    public static int bestScore;
    public static String imagePath;
    public static Scene build(double width, double height) throws IOException {
        FXMLLoader loader = new FXMLLoader(Game.class.getResource("game_page.fxml"));
        Parent root = loader.load();
        scene = new Scene(root, width, height);
        controller = loader.getController();
        scene.setOnKeyPressed(Game::onKeyPressed);
        scene.setOnKeyReleased(Game::onKeyReleased);
        board = new Board();
        board.initialize();
        bestScore = importBestScore();
        System.out.println(bestScore);
        controller.setMaxScore(bestScore);
        keyEnable = true;
        showBoard();
        if (imagePath != null) {
            System.out.println("Image Path = " + imagePath);
            root.setStyle("-fx-background-image: url('" + imagePath + "');" +
                    "-fx-background-size: contain;" +
                    "-fx-background-position: center center;");
        }
        return scene;
    }

    private static void showBoard() {
        ParallelTransition ptr = new ParallelTransition();
        for (Board.Moving mov: board.moving) {
            int x1 = mov.x1, y1 = mov.y1;
            int x2 = mov.x2, y2 = mov.y2;
            TranslateTransition ttr = new TranslateTransition(Duration.millis(50), controller.cells[x1][y1]);
            controller.cells[x1][y1].toFront();
            ttr.setToX(controller.cells[x2][y2].getX() - controller.cells[x1][y1].getX());
            ttr.setToY(controller.cells[x2][y2].getY() - controller.cells[x1][y1].getY());
            ptr.getChildren().add(ttr);
        }
        ptr.setAutoReverse(false);
        ptr.setCycleCount(1);
        ptr.setOnFinished(actionEvent -> {
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 4; ++j) {
                    if (board.at(i, j) == 0) controller.clearCell(i, j);
                    else controller.setCell(i, j, board.at(i, j));
                }
            }
            for (Board.Moving mov: board.moving) {
                int x1 = mov.x1, y1 = mov.y1;
                controller.cells[x1][y1].setTranslateX(0);
                controller.cells[x1][y1].setTranslateY(0);
            }
            controller.setScore(board.getScore());
            bestScore = Math.max(bestScore, board.getScore());
            controller.setMaxScore(bestScore);
        });
        ptr.play();
    }
    private enum keyCode { UP, DOWN, LEFT, RIGHT, NULL }
    private static keyCode getCode(KeyCode key) {
        switch (key) {
            case W, UP -> {
                return keyCode.UP;
            }
            case S, DOWN -> {
                return keyCode.DOWN;
            }
            case A, LEFT -> {
                return keyCode.LEFT;
            }
            case D, RIGHT -> {
                return keyCode.RIGHT;
            }
            default -> {
                return keyCode.NULL;
            }
        }
    }
    public static void gameOver() {
        controller.game_over_bg.toFront();
        controller.game_over.toFront();
        FadeTransition ft1 = new FadeTransition(Duration.seconds(1), controller.game_over_bg);
        ft1.setFromValue(0); ft1.setToValue(0.7);
        FadeTransition ft2 = new FadeTransition(Duration.seconds(1), controller.game_over);
        ft2.setFromValue(0); ft2.setToValue(1);
        ParallelTransition pt = new ParallelTransition(ft1, ft2);
        pt.play();
        keyEnable = false;
        exportBestScore(bestScore);
    }
    public static void saveBestScore() {
        exportBestScore(bestScore);
    }

    private static void exportBestScore(int bestScore) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("maxNumber.dat"))) {
            System.out.println("Export!");
            oos.writeObject(bestScore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static int importBestScore() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("maxNumber.dat"))) {
            System.out.println("Import!");
            return (int) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No Max Number");
            return 0;
        }
    }


    private static void onKeyPressed(KeyEvent event) {
        if (keyPressed) return;
        if (!keyEnable) return;
        keyPressed = true;
        boolean isValidInput = true;
        switch (getCode(event.getCode())) {
            case UP -> {
                haveMoved = board.sinkUp();
            }
            case DOWN -> {
                haveMoved = board.sinkDown();
            }
            case LEFT -> {
                haveMoved = board.sinkLeft();
            }
            case RIGHT -> {
                haveMoved = board.sinkRight();
            }
            default -> {
                isValidInput = false;
            }
        }
        if (isValidInput && haveMoved) {
            board.genRandomCell();
            showBoard();
        } else if (!board.existMoving()) {
            gameOver();
        }
    }
    private static void onKeyReleased(KeyEvent keyEvent) {
        keyPressed = false;
    }
    public static void recoveryBoard() {
        controller.game_over_bg.toBack();
        controller.game_over.toBack();
        keyEnable = true;
        showBoard();
    }
    public static void save() {
        String userHome = System.getProperty("user.home");
        String saveFilePath = "save.dat";//userHome + File.separator + "YourAppName" + File.separator + "save.dat";
//        saveFilePath = "save.dat";
        Board.saveData(saveFilePath, board);
        saveBestScore();
    }
    public static void load() {
        String saveFilePath = "save.dat";
        board = Board.loadData(saveFilePath);
        if (board == null) {
            System.out.println("Load error!");
        } else {
            recoveryBoard();
        }
    }
}
