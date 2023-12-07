package com.example.game2048;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private final double Width = 1600.0;
    private final double Height = 900.0;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Width / 2, Height / 2);
        stage.setTitle("Game 2048");
        stage.setScene(scene);
        stage.setResizable(false);
//        scene.getStylesheets().add(Main.class.getResource("main-bg.css").toExternalForm());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.GridPane;
//import javafx.stage.Stage;
//
//public class Main extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        GridPane gridPane = new GridPane();
//
//        // 创建几个按钮，并设置它们在 GridPane 中的位置
//        Button button1 = new Button("Button 1");
//        gridPane.add(button1, 0, 0);  // 第一列，第一行
//
//        Button button2 = new Button("Button 2");
//        gridPane.add(button2, 1, 0);  // 第二列，第一行
//
//        Button button3 = new Button("Button 3");
//        gridPane.add(button3, 0, 1);  // 第一列，第二行
//
//        Button button4 = new Button("Button 4");
//        gridPane.add(button4, 1, 1);  // 第二列，第二行
//
//        Scene scene = new Scene(gridPane, 300, 200);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("GridPane Example");
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}