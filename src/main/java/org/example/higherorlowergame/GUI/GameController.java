package org.example.higherorlowergame.GUI;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.example.higherorlowergame.Card;
import org.example.higherorlowergame.Deck;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author      Abdelrahmane, Bekhli, abdelrahmane001@gmail.com
 */
public class GameController implements Initializable {

    @FXML
    private Text gameScore, gameTimer, status, timeTaken, finalScore;

    @FXML
    private ImageView previousCardImg, currentCardImg, nextCardImg;

    @FXML
    private Pane winScreen;

    private final Deck deck = new Deck(true);
    private Card currentCard;
    private Card nextCard;

    private int elapsedTime = 0; // Elapsed time in seconds
    private Timeline timer; // Timer for updating the game time
    private int counter = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startGame();
    }

    /**
     * initialize the screen to default values.
     */
    private void startGame(){
        winScreen.setVisible(false);
        elapsedTime = 0;
        deck.shuffle();

        // get the starting card
        currentCard = deck.getNextCard();
        currentCardImg.setImage(currentCard.getImagePath());

        // get the first card to be guessed
        nextCard = deck.getNextCard();

        previousCardImg.setImage(null);

        gameTimer.setText("Time: 00:00:00" );
        startTimer();
    }

    /**
     * starts the game timer.
     */
    private void startTimer() {
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
        timer.setCycleCount(Timeline.INDEFINITE); // Run indefinitely
        timer.play(); // Start the timer
    }

    /**
     * updates the timer display.
     */
    private void updateTimer() {
        elapsedTime++;
        gameTimer.setText("Time: " + formatTime(elapsedTime)); // update timer text with "Time: " prefix
    }

    /**
     * formats the elapsed time as HH:MM:SS.
     *
     * @param seconds total seconds elapsed.
     * @return formatted time string.
     * */
    private String formatTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }

    /**
     * Stops the timer.
     */
    private void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }

    /**
     * triggers the action of higher button.
     */
    @FXML
    void triggerHigher(ActionEvent event) {
        if(counter < 52) {
            checkAnswer(currentCard.getRank() <= nextCard.getRank());
        }
    }

    /**
     * triggers the action of lower button.
     */
    @FXML
    void triggerLower(ActionEvent event) {
        if(counter < 52) {
            checkAnswer(currentCard.getRank() >= nextCard.getRank());
        }
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
     * @param incrementPoints whether to add points or not.
     */
    private void correctAnswer(boolean incrementPoints) {
        if (counter < 52) {
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

                // game ended
                if (counter == 51) {
                    stopTimer();
                    winScreen.setVisible(true);
                    setWinDetails();
                }
            }
            counter++;
        }
    }

    /**
     * set all details on the winning screen.
     */
    public void setWinDetails(){
        String score = gameScore.getText();
        int currentScore = Integer.parseInt(score.split(": ")[1]);
        finalScore.setText("score: " + currentScore);

        timeTaken.setText("Time: " + finalFormatTime(elapsedTime));
    }

    /**
     * converts the time into x hours y minutes z seconds format.
     * @param seconds time passed in seconds.
     */
    private String finalFormatTime(int seconds) {
        int hours = seconds / 3600; // 3600 seconds in an hour
        int minutes = (seconds % 3600) / 60; // Get remaining minutes after hours
        int remainingSeconds = seconds % 60; // Get the remaining seconds after minutes

        StringBuilder timeString = new StringBuilder();

        if (hours > 0) {
            timeString.append(hours).append(" hour");
            if (hours > 1) {
                timeString.append("s");
            }
            timeString.append(" ");
        }

        if (minutes > 0 || hours > 0) { // Only show minutes if hours are shown or minutes are > 0
            timeString.append(minutes).append(" minute");
            if (minutes > 1) {
                timeString.append("s");
            }
            timeString.append(" ");
        }

        if (remainingSeconds > 0 || hours == 0 && minutes == 0) { // If there are seconds or no hours/minutes, show seconds
            timeString.append(remainingSeconds).append(" second");
            if (remainingSeconds > 1) {
                timeString.append("s");
            }
        }

        return timeString.toString();
    }

    /**
     * triggers the action of play again button.
     */
    @FXML
    void triggerPlayAgain(ActionEvent event) {
        startGame();
        winScreen.setVisible(false);
        gameScore.setText("score: " + 0);
        counter = 0;
    }
    /**
     * triggers the action of exit button.
     */
    @FXML
    void triggerExit(ActionEvent event) {
        System.exit(0);
    }

}
