package org.example.higherorlowergame;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author      Abdelrahmane, Bekhli, abdelrahmane001@gmail.com
 */
public class Deck {
    private final ArrayList<Card> deck = new ArrayList<>();
    private int pointer = 0;

    /**
     * Constructor for the deck of cards
     * @param joker boolean True or False whether to include Joker cards or not.
     */
    public Deck(Boolean joker){
        String[] suitType = {"hearts", "diamonds", "clubs", "spades"};
        int[] rankType = new int[13];

        // create a list of valid rank types. i.e. 2 - 13 (2 - Ace)
        int start = 2;
        for (int i = 0; i < 13; i++){
            rankType[i] = start;
            start ++;
        }

        // initializing the cards with their corresponding images
        String cardImgFolder = "Playing Cards/";
        for (String suit : suitType) {
            for(int i = 0; i < rankType.length; i++){
                if(i == 9){
                    deck.add(new Card(rankType[i], suit, "Jack", cardImgFolder + "jack_of_" + suit + ".png"));
                }
                else if(i == 10){
                    deck.add(new Card(rankType[i], suit, "Queen", cardImgFolder + "queen_of_" + suit + ".png"));
                }
                else if(i == 11){
                    deck.add(new Card(rankType[i], suit, "King", cardImgFolder + "king_of_" + suit + ".png"));
                }
                else if(i == 12){
                    deck.add(new Card(rankType[i], suit, "Ace", cardImgFolder +"ace_of_" + suit + ".png"));
                }
                else{
                    deck.add(new Card(rankType[i], suit, Integer.toString(rankType[i]), cardImgFolder + rankType[i] + "_of_" + suit + ".png"));

                }
            }
        }

        // add joker to deck if enabled
        if (joker){
            deck.add(new Card(15,"Junk", "Joker", cardImgFolder + "red_joker.png"));
            deck.add(new Card(15,"Junk", "Joker", cardImgFolder + "black_joker.png"));
        }
    }

    /**
     * shuffles the deck creating a random order of cards.
     */
    public void shuffle(){
        Collections.shuffle(deck);
        pointer = 0;
    }

    /**
     * @return returns the next card to be accessed.
     */
    public Card getNextCard(){
        if(pointer > deck.size() - 1){
            return null;
        } else {
            int tempPointer = pointer;
            pointer++;
            return deck.get(tempPointer);
        }
    }
}
