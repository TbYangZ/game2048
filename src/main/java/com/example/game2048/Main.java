package com.example.game2048;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private final double Width = 1600.0;
    private final double Height = 900.0;
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Game 2048");
        stage.setScene(MainMenu.build(Width / 2, Height / 2));
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) { launch(); }
}

//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//
//public class Main extends Application {
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        // 第一个页面
//        StackPane firstPage = new StackPane();
//        Button switchButton = new Button("Switch to Second Page");
//        switchButton.setOnAction(e -> switchToSecondPage(primaryStage));
//        firstPage.getChildren().add(switchButton);
//
//        // 将第一个页面设置为场景
//        Scene firstScene = new Scene(firstPage, 300, 200);
//        primaryStage.setScene(firstScene);
//        primaryStage.setTitle("First Page");
//        primaryStage.show();
//    }
//
//    private void switchToSecondPage(Stage primaryStage) {
//        // 第二个页面
//        StackPane secondPage = new StackPane();
//        Button backButton = new Button("Back to First Page");
//        backButton.setOnAction(e -> switchToFirstPage(primaryStage));
//        secondPage.getChildren().add(backButton);
//
//        // 将第二个页面设置为场景
//        Scene secondScene = new Scene(secondPage, 300, 200);
//        primaryStage.setScene(secondScene);
//        primaryStage.setTitle("Second Page");
//    }
//
//    private void switchToFirstPage(Stage primaryStage) {
//        // 切换回第一个页面
//        Scene firstScene = primaryStage.getScene();
//        primaryStage.setScene(firstScene);
//        primaryStage.setTitle("First Page");
//    }
//}
