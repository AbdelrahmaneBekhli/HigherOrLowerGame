package org.example.higherorlowergame.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

import java.io.IOException;

/**
 * @author      Abdelrahmane, Bekhli, abdelrahmane001@gmail.com
 */
public class MenuController {

    private static boolean joker;

    @FXML
    private ToggleButton toggleSwitch;

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
    void toggleStart(ActionEvent event) throws IOException {
        GameApplication.setGamePage();
    }

    /**
     * @return has the joker been added to the deck.
     */
    public static boolean isJoker() {
        return joker;
    }
}
