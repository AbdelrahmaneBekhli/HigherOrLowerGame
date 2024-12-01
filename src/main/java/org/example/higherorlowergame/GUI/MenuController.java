package org.example.higherorlowergame.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleButton;

import java.io.IOException;

/**
 * @author      Abdelrahmane, Bekhli, abdelrahmane001@gmail.com
 */
public class MenuController {

    private static boolean joker;

    @FXML
    private ToggleButton toggleSwitch;

    /**
     * sets whether a joker is needed for the round.
     */
    @FXML
    private void handleToggle() {
        joker = toggleSwitch.isSelected();
    }

    /**
     * exits the application.
     */
    @FXML
    void toggleExit(ActionEvent event) {
        System.exit(0);
    }

    /**
     * starts the game.
     */
    @FXML
    void toggleStart(ActionEvent event) {
        try {
            GameApplication.setGamePage();
        } catch (IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Start Game Error");
            alert.setHeaderText("Cannot start the game");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * @return has the joker been added to the deck.
     */
    public static boolean isJoker() {
        return joker;
    }
}
