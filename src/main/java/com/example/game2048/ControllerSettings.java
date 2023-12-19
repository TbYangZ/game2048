package com.example.game2048;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class ControllerSettings {

    public BorderPane mainRoot;
    public Button BGM1, BGM2, BGM3, BGM4;
    public Button Back;
    public Hyperlink github;
    public MediaPlayer player;
    public Slider volumeSlider;
    public Button chooseImageButton;
    public double volume;
    public void initialize() {
        BGM1.setOnAction(event -> onClickBGMButton("STARTLINER"));
        BGM2.setOnAction(event -> onClickBGMButton("Sympathy"));
        BGM3.setOnAction(event -> onClickBGMButton("Invisible Frenzy"));
        BGM4.setOnAction(event -> onClickBGMButton("Last丨Eternity"));
        Back.setOnAction(event -> {
            try {
                onClickBackButton();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        chooseImageButton.setOnAction(actionEvent -> {
            try {
                browseForImage();
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        });
        github.setOnAction(event -> handleGitHubLinkClicked());
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setShowTickMarks(true);
        volume = 0.5;
        volumeSlider.setValue(volume);
//        volumeLabel = new Label("当前音量: " + volumeSlider.getValue());
        volumeSlider.valueProperty().addListener(((observableValue, number, t1) -> {
//            volumeLabel.setText("当前音量: " + t1.intValue());
            handleVolumeChange(t1.intValue());
        }));
    }

    private void onClickBGMButton(String name) {
        String s = ControllerSettings.class.getResource(name + ".mp3").toExternalForm();
        if (player != null) player.stop();
        Media media = new Media(s);
        player = new MediaPlayer(media);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.setVolume(volume);
        player.play();
    }
    private void onClickBackButton() throws IOException {
        Scene newScene = MainMenu.build(800, 450);
        Scene currentScene = mainRoot.getScene();
        Stage currentStage = (Stage) currentScene.getWindow();
        currentStage.setScene(newScene);
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
    private void handleVolumeChange(int newVolume) {
        // 在这里执行当音量变化时的操作
        volume = newVolume / 100.0; // 将音量百分比转换为范围在0到1之间的值
        if (player != null) {
            player.setVolume(volume);
            player.play();
        }
    }
    private void browseForImage() throws UnsupportedEncodingException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Background Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.bmp"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            setImage(selectedFile);
        }
    }

    private void setImage(File file) throws UnsupportedEncodingException {
        String filePath = file.getAbsolutePath();
        Image image = new Image("file:" + file.getAbsolutePath());
        filePath = URLEncoder.encode(filePath, StandardCharsets.UTF_8);
        System.out.println(filePath);
        filePath = filePath.replace("+", "%20");
        MainMenu.imagePath = "file:" + filePath;
        Game.imagePath = "file:" + filePath;
        Settings.imagePath = "file:" + filePath;
        mainRoot.setStyle("-fx-background-image: url('" + Settings.imagePath + "');" +
                "-fx-background-size: contain;" +
                "-fx-background-position: center center;");
    }
}
