module com.example.game2048 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.web;
    requires java.desktop;


    opens com.example.game2048 to javafx.fxml;
    exports com.example.game2048;
}