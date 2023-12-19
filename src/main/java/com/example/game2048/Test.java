package com.example.game2048;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Close Event Example");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> {
            // 在程序关闭时执行相关动作
            onClose();
            // 关闭应用程序
            Platform.exit();
        });

        StackPane root = new StackPane();
        root.getChildren().add(closeButton);

        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();

        // 添加关闭事件监听器
        primaryStage.setOnCloseRequest(event -> {
            // 在程序关闭时执行相关动作
            onClose();
        });
    }

    private void onClose() {
        System.out.println("Closing application...");
        // 在这里执行你的关闭动作
        // 例如，保存数据、关闭连接等
    }

    public static void main(String[] args) {
        launch(args);
    }
}