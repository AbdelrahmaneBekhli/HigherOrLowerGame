package org.example.higherorlowergame.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * @author      Abdelrahmane, Bekhli, abdelrahmane001@gmail.com
 */
public class GameApplication extends Application {
    /**
     * initialize method to launch the GUI.
     * @param stage the window used for the application.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("/org/example/higherorlowergame/CardGameScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        String css = Objects.requireNonNull(getClass().getResource("/org/example/higherorlowergame/stylesheet.css")).toExternalForm();
        String winCss = Objects.requireNonNull(getClass().getResource("/org/example/higherorlowergame/winStylesheet.css")).toExternalForm();
        scene.getStylesheets().add(css);
        scene.getStylesheets().add(winCss);
        stage.setTitle("Higher or Lower Game!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}