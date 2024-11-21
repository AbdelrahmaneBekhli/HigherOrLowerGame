package org.example.higherorlowergame.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.example.higherorlowergame.Card;
import org.example.higherorlowergame.Deck;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author      Abdelrahmane, Bekhli, abdelrahmane001@gmail.com
 */
public class GameController implements Initializable {

    @FXML
    private Text gameScore;

    @FXML
    private Text gameTimer;

    @FXML
    private Text status;

    @FXML
    private Button higherButton;

    @FXML
    private Button lowerButton;

    @FXML
    private ImageView nextCardImg;

    @FXML
    private ImageView previousCardImg;

    @FXML
    private ImageView currentCardImg;

    private final Deck deck = new Deck(true);
    private Card currentCard;
    private Card nextCard;

    /**
     * initialize the screen to default values.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deck.shuffle();

        // get the starting card
        currentCard = deck.getNextCard();
        currentCardImg.setImage(currentCard.getImagePath());

        // get the first card to be guessed
        nextCard = deck.getNextCard();

        previousCardImg.setImage(null);
    }

    /**
     * triggers the action of higher button.
     */
    @FXML
    void triggerHigher(ActionEvent event) {
        checkAnswer(currentCard.getRank() <= nextCard.getRank());
    }

    /**
     * triggers the action of lower button.
     */
    @FXML
    void triggerLower(ActionEvent event) {
        checkAnswer(currentCard.getRank() >= nextCard.getRank());
    }

    /**
     * answer handler
     * @param isCorrect is the answer from user correct.
     */
    private void checkAnswer(boolean isCorrect) {
        if(nextCard.getLabel().equals("Joker")) {
            correctAnswer(false);
            status.setText("Push! Joker card, no points awarded.");
        } else if (currentCard.getRank() == nextCard.getRank()) {
            correctAnswer(false);
            status.setText("Push! same rank, no points awarded.");
        } else if(isCorrect) {
            correctAnswer(true);
        } else {
            status.setText("Incorrect answer!");
        }
    }

    /**
     * updates the cards to the relevant positions.
     */
    private void correctAnswer(boolean incrementPoints) {
        status.setText("Correct!");
        previousCardImg.setImage(currentCard.getImagePath());
        // reveal the guessed card
        currentCard = nextCard;
        currentCardImg.setImage(currentCard.getImagePath());
        // set the next card
        nextCard = deck.getNextCard();

        if (incrementPoints) {
            // extract the numerical part of the score string
            String score = gameScore.getText();
            int currentScore = Integer.parseInt(score.split(": ")[1]);
            currentScore += 5;

            // update score
            gameScore.setText("score: " + currentScore);
        }
    }

}
