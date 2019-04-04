package com.Projects;

import javax.swing.*;

public class Main {

    //private static Deck deck = new Deck();
    private static int handSize = 5;
    public static void main(String[] args) {

        
            Deck deck = new Deck();
            //printDeck method is called by passing an instance of the Deck class
            //The method prints each Card object in the deck class.
            //printDeck(deck);

            //The method createNewHand builds the array to be passed to the hand object by picking a random Card from the Deck array
            //and inserting it into a new array of size 5. That array will later be passed to the hand constructor
            Card[] dealtCards = deck.createNewHand();
            Hand hand = new Hand(dealtCards);
            //calling the show hand method to show what Card objects are in the Hand instance
            showHand(hand);

			
            dealtCards = deck.createNewHand();
            Hand hand2 = new Hand(dealtCards);
            showHand(hand2);

            //create an array of hands and it is passed to the checkWinner() method 
			Hand[] allHands = new Hand[]{hand, hand2};
			checkWinner(allHands);

    }

    public static void checkWinner(Hand[] allHands){
        
        int highScore = 0;
        int playerScore;
        int winner = 0;
        String handRank = "";
        String result = "";
        
        for(int i = 0; i < allHands.length; i++) {
            Hand hand = allHands[i];
            playerScore = 0;
			
            //check for a flush using hand.isFlush which returns a boolean
            if(hand.isFlush()){
                playerScore = hand.getValueOf(hand.getHighCardString())+13*10;
                handRank = "Flush";
                //if straight then straight flush
                if(hand.isStraight()){
                    playerScore = hand.getValueOf(hand.getHighCardString())+13*10;
                    handRank = "Straight Flush";
                }
            }
            //check for a straight
            else if(hand.isStraight()){
                playerScore = hand.getValueOf(hand.getHighCardString())+13*8;
                handRank = "Straight";
            }
//            else if(hand.isFourOfAKind()){
//
//            }
            else if(!hand.isThreeOfAKind().equals("")){
                String value = hand.isThreeOfAKind();
                if(!hand.isPair(value).equals("")){
                    playerScore = hand.getValueOf(hand.getHighCardString()) + 13*5;
                    handRank = "Full house with 3" + hand.isThreeOfAKind() + "s" + " and 2 " + hand.isPair(value) + "s";
                }
                else {
                    playerScore = hand.getValueOf(hand.isThreeOfAKind()) + 13*3;
                    handRank = "Three of a kind with " + hand.isThreeOfAKind() + "s";
                }

            }
            else if(!hand.isPair().equals("")){
				String reject = hand.isPair();
				//check for a second pair using the overloaded isPair() method
                if(!hand.isPair(reject).equals("")){
					String secondPair = hand.isPair(reject);
					playerScore = hand.getValueOf(hand.isPair())+13*2;
                    handRank = "Pair of " + reject + "s" + " and a pair of " + secondPair + "s";
                }
                playerScore = hand.getValueOf(hand.isPair())+13;
                handRank = "Pair of " + hand.isPair() + "s";
            }

            //if not a good hand, then it must have a high card
            else{
                //create a string which has the high card suit and value
                handRank = "high card of " + hand.getHighCardString();
                //add to player score the integer value of the card. This is achieved by using the getValueOf() method
                //in the hand class, which returns the integer value of the highest card in the hand
                playerScore = hand.getValueOf(hand.getHighCardString());
            }

            //if the current player score is greater than the highscore, the highscore is updated and
            //the winner variable is updated to the location of the current hand in the array+1
            if(playerScore > highScore){
                winner = i+1;
                highScore = playerScore;
            }
//            else if(playerScore == highScore){
//                int higher =
//            }

            result += "Hand " + (i+1) + " has a " + handRank + "\n";
        }
        //announce winner 
        result += "Hand " + winner + " wins";
        JOptionPane.showConfirmDialog(null, result);
        
    }


    //this method prints all cards in the deck
    //The method is passed an instance of the Deck class. The array within that instance is
    //then placed in a placeholder array of type Card. Each element of that array is accessed
    //with a for loop and printed using the toString() of the Card class
    public static void printDeck(Deck deck){
        Card[] cardDeck = deck.getDeck();
        for(int i = 0; i < cardDeck.length; i++) {
            System.out.println(i+1 + ". " + cardDeck[i].toString());
        }
    }

    //this method prints to the screen what Card objects the hand instance has
    private static int count = 1;
    public static void showHand(Hand hand){
        Card[] handArray = hand.getHandArray();
        String result = "";
        result = "Hand " + count + "\n";

        for(int i = 0; i < handArray.length; i++){
            result += (i+1) + ". " + handArray[i].toString() + "\n";
        }

        JOptionPane.showConfirmDialog(null, result);
        count++;
    }
}
