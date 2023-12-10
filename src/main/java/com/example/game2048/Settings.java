package com.example.game2048;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;

import java.io.IOException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import java.net.URI;
import java.io.File;

public class Settings {
    private static MediaPlayer mediaPlayer;

    public static Scene build() throws IOException {
        Parent root = FXMLLoader.load(Settings.class.getResource("settings.fxml"));

        // 创建按钮
        Button bgm1Button = new Button("BGM 1");
        Button bgm2Button = new Button("BGM 2");
        Button bgm3Button = new Button("BGM 3");
        Button bgm4Button = new Button("BGM 4");
        Button tutorialButton = new Button("View Tutorial");

        // 创建超链接按钮
        Hyperlink githubLink = new Hyperlink("Visit our GitHub repository");

        // 设置按钮点击事件
        bgm1Button.setOnAction(event -> handleBGMButtonClicked("STARTLINER.mp3"));
        bgm2Button.setOnAction(event -> handleBGMButtonClicked("Sympathy.mp3"));
        bgm3Button.setOnAction(event -> handleBGMButtonClicked("Invisible Frenzy.mp3"));
        bgm4Button.setOnAction(event -> handleBGMButtonClicked("Last丨Eternity.mp3"));

        // 设置超链接按钮点击事件
        githubLink.setOnAction(event -> handleGitHubLinkClicked());
        // 设置教程点击事件
        tutorialButton.setOnAction(event -> handleTutorialButtonClicked());

        // 布局按钮和超链接
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(bgm1Button, bgm2Button, bgm3Button, bgm4Button, tutorialButton, githubLink);

        // 创建场景
        return new Scene(layout, 400, 300);
    }

    private static void handleBGMButtonClicked(String bgmFileName) {
        // 处理按钮点击事件，可以在这里设置播放指定的BGM
        System.out.println("Selected BGM: " + bgmFileName);

        // 如果之前有正在播放的BGM，停止它
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        // 创建新的MediaPlayer并播放选择的BGM
        Media media = new Media(Settings.class.getResource(bgmFileName).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // 设置循环播放
        mediaPlayer.play();
    }

    private static void handleGitHubLinkClicked() {
        // 处理GitHub超链接按钮点击事件，可以在这里打开浏览器访问GitHub项目页面
        String githubURL = "https://github.com/TbYangZ/game2048";
        // 在默认浏览器中打开GitHub页面
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI(githubURL));
        } catch (java.io.IOException | java.net.URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static void handleTutorialButtonClicked() {
        // 在这里处理查看教程按钮的点击事件
        System.out.println("View Tutorial Button Clicked");

        // 你可以在这里弹出教程窗口，或者执行其他相关操作
        // 创建新舞台用于显示教程界面
        Stage tutorialStage = new Stage();
        tutorialStage.initModality(Modality.APPLICATION_MODAL);
        tutorialStage.setTitle("Game Tutorial");

        // 创建 WebView
        WebView webView = new WebView();
        // 在这里加载教程内容，可以是本地HTML文件或在线URL
        String localHtmlFilePath = "src/main/resources/com/example/game2048/tutorial.html";

        // 将文件路径转换为 URI
        URI uri = new File(localHtmlFilePath).toURI();

        // 使用 WebView 加载本地 HTML 文件
        webView.getEngine().load(uri.toString());

        // 创建返回按钮
        Button backButton = new Button("Back to Settings");
        backButton.setOnAction(event -> tutorialStage.close());

        // 布局
        VBox layout = new VBox(10);
        layout.getChildren().addAll(webView, backButton);

        // 创建场景
        Scene tutorialScene = new Scene(layout, 600, 400);
        tutorialStage.setScene(tutorialScene);

        // 显示教程界面
        tutorialStage.show();
    }
}
