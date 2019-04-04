//This class represents a card and has data fields of type String for suit and card value(ten, king, queen),
//and an int for the cards numerical value(1-13)

package com.Projects;

public class Card {
    private String suit;
    private String cardValue;
    private int value;

    public Card(String cardValue, String suit, int value){
        this.cardValue = cardValue;
        this.suit = suit;
        this.value = value;
    }

    @Override
    public String toString() {
        return cardValue + " of " + suit;
    }

    public String getSuit() {
        return suit;
    }

    public String getCardValue() {//String 2 3 4 etc.
        return cardValue;
    }

    public int getValue(){//int parse
        return this.value;
    }

}
