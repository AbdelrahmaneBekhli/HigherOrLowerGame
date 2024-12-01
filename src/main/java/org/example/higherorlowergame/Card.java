package org.example.higherorlowergame;

import javafx.scene.image.Image;

/**
 * @author      Abdelrahmane, Bekhli, abdelrahmane001@gmail.com
 */
public class Card {

    private final int rank;

    private final String suit;

    private final String label;

    private final Image imagePath;

    /**
     * Constructor for the card.
     * front cards pictures reference: <a href="https://opengameart.org/content/playing-cards-vector-png">Front of cards</a>
     * back cards picture reference : <a href="https://opengameart.org/content/colorful-poker-card-back">Back of cards</a>
     * @param rank rank of the card (i.e. 1 - 15).
     * @param suit suit of the card (i.e. Hearts, Spades, Diamonds, Clubs).
     * @param label label (can be number or string e.g. 3 or Queen).
     * @param imagePath image file location of the card.
     */
    public Card(int rank, String suit, String label, String imagePath) {
        this.rank = rank;
        this.suit = suit;
        this.label = label;
        this.imagePath = new Image(imagePath);
    }

    /**
     * @return The rank of the card.
     */
    public int getRank() {
        return rank;
    }
    /**
     * @return The suit of the card.
     */
    public String getSuit() {
        return suit;
    }
    /**
     * @return The label of the card.
     */
    public String getLabel() {
        return label;
    }
    /**
     * @return The image file path of the card.
     */
    public Image getImagePath() {
        return imagePath;
    }
}
