package com.example.game2048;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        // 添加一个按钮
        Button button = new Button("Click me!");
        root.getChildren().add(button);
        button.setFocusTraversable(false);

        Scene scene = new Scene(root, 300, 200);

        // 设置场景的键盘事件处理程序
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();

            if (keyCode == KeyCode.UP) {
                System.out.println("Up key pressed");
            } else if (keyCode == KeyCode.DOWN) {
                System.out.println("Down key pressed");
            } else if (keyCode == KeyCode.LEFT) {
                System.out.println("Left key pressed");
            } else if (keyCode == KeyCode.RIGHT) {
                System.out.println("Right key pressed");
            }

            // 在这里添加其他处理逻辑
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Direction Keys Example with Button");
        primaryStage.show();
    }
}
