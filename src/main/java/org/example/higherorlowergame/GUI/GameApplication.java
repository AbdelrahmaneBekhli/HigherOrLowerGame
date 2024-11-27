package org.example.higherorlowergame.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * @author      Abdelrahmane, Bekhli, abdelrahmane001@gmail.com
 */
public class GameApplication extends Application {
    private static Stage stage;
    /**
     * initialize method to launch the GUI.
     * @param stage the window used for the application.
     * @throws IOException if there is an error loading the FXML or CSS files.
     */
    @Override
    public void start(Stage stage) throws IOException {
        GameApplication.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("/org/example/higherorlowergame/fxmlFiles/CardGameMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

        String menuCss = Objects.requireNonNull(getClass().getResource("/org/example/higherorlowergame/cssFiles/menuStylesheet.css")).toExternalForm();
        scene.getStylesheets().add(menuCss);

        stage.setTitle("Higher or Lower Game!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * method to set the screen to the game.
     * @throws IOException if there is an error loading the FXML or CSS files.
     */
    public static void setGamePage() throws IOException {
        Parent loginFxml = new FXMLLoader(GameApplication.class.getResource("/org/example/higherorlowergame/fxmlFiles/CardGameScreen.fxml")).load();
        Scene loginScene = new Scene(loginFxml, 1280, 720);
        loginScene.getStylesheets().add(Objects.requireNonNull(GameApplication.class.getResource("/org/example/higherorlowergame/cssFiles/stylesheet.css")).toExternalForm());
        loginScene.getStylesheets().add(Objects.requireNonNull(GameApplication.class.getResource("/org/example/higherorlowergame/cssFiles/winStylesheet.css")).toExternalForm());
        stage.setScene(loginScene);
    }

    public static void main(String[] args) {
        launch();
    }
}