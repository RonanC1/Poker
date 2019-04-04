//This class represents a deck of Card objects. Each new Card instance is passed a String from the suit array,
// a String from the cardValue array, and an int from the value array. By copying Cards that have been dealt
// to a hand, to the ArrayList used cards, we can keep track of cards that have been dealt already, avoiding
//dealing the same card twice

package com.Projects;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private static Card[] deck;
    private int deckSize = 52;
    private int deckPosition = 0;//to keep track of which deck element a new Card object is being placed in
    private static ArrayList<Card> usedCards;
    private String[] suit = new String[]{"Hearts","Diamonds", "Clubs", "Spades"};
    private String[] cardValue = new String[]{"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
    private int[] value = new int[]{2,3,4,5,6,7,8,9,10,11,12,13,14};

    //in this constructor, two nested for loops are used to initialize a new card object into each element of the deck array.
    //The suit for each card object is determined by int i, while each cardValue is determined by int j. This allows for the first
    //13 thirteen cards to be Hearts, followed by each other suit until the end of the suit array is reached
    public Deck() {
        this.deck = new Card[deckSize];
        for(int i = 0; i < suit.length; i++){
            for (int j = 0; j < cardValue.length; j++){
                deck[deckPosition] = new Card(cardValue[j], suit[i], value[j]);
                deckPosition++;
            }
        }
        this.usedCards = new ArrayList<Card>();
    }

    //This method builds the array to be passed to the hand object by picking a random Card from the Deck array
    //and inserting it into a new array of size 5. That array will later be passed to the hand constructor
    public Card[] createNewHand() {
        //5 will be the max amount of Card objects
        int handSize = 5;
        Random random = new Random();

        //an array of type Card will hold each new instance of Card
        Card[] dealtCards = new Card[handSize];
        int i = 0;
        int randomNum;

        //while i is less than the new array of size 5, a random number between 0 and 51 inclusive will be generated and
        //assigned to randomNum. A new card object is picked from the deck at a random number, and that Card objects is assigned
        //to the variable card. If that card is not in the burnt deck array, it will be added to the array to be returned.
        //Once added, the card object will be added to the burnt deck and i will be incremented
        while (i < dealtCards.length) {
            randomNum = random.nextInt(deck.length);
            Card card = deck[randomNum];
            if (usedCards.indexOf(card) < 0) {
                dealtCards[i] = deck[randomNum];
                usedCards.add(card);
                i++;
            }
        }
        return dealtCards;
    }

    public Card[] getDeck() {
        return this.deck;
    }

    public ArrayList<Card> getUsedCards() {
        return this.usedCards;
    }
}
