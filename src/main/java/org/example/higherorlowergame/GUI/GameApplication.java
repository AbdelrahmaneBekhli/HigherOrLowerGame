package org.example.higherorlowergame.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("/org/example/higherorlowergame/fxmlFiles/CardGameScreen.fxml"));

            Parent loginFxml = fxmlLoader.load();
            Scene loginScene = new Scene(loginFxml, 1280, 720);

            // Verify CSS resource paths
            URL stylesheetUrl = GameApplication.class.getResource("/org/example/higherorlowergame/cssFiles/stylesheet.css");
            URL winStylesheetUrl = GameApplication.class.getResource("/org/example/higherorlowergame/cssFiles/winStylesheet.css");

            if (stylesheetUrl != null) {
                loginScene.getStylesheets().add(stylesheetUrl.toExternalForm());
            } else {
                System.err.println("Stylesheet not found!");
            }

            if (winStylesheetUrl != null) {
                loginScene.getStylesheets().add(winStylesheetUrl.toExternalForm());
            } else {
                System.err.println("Win Stylesheet not found!");
            }

            stage.setScene(loginScene);
        } catch (Exception e) {
            // print full stack trace
            e.printStackTrace();

            // show error dialog
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Screen Change Error");
            alert.setHeaderText("Failed to change screen");
            alert.setContentText(e.toString());
            alert.showAndWait();

            // rethrow to maintain method signature
            throw new IOException("Failed to set game page", e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}