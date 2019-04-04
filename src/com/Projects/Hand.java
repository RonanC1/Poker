//This class has an array of type Card that holds up to 5 cards

package com.Projects;

public class Hand {
    private int handSize;
    private Card[] handArray;

    public Hand(Card[] deck) {
        this.handSize = 5;
        this.handArray = new Card[handSize];
        //the for loop is used to add every Card from the array passed to the constructor to the array handArray
        for(int i = 0; i < handArray.length; i++){
            handArray[i] = deck[i];
        }
    }

    //method checks if the cards in the hand array equal a flush
    public boolean isFlush(){
        //first we get the suit of the first card and store it in a String
        String suit = handArray[0].getSuit();
        boolean isFlush = true;

        //now we check each element in the array. If the suit isn't the same as the first, the boolean isFlush will be false
        for(int i = 0; i < handArray.length; i++){
            if(!(handArray[i].getSuit().equals(suit))){
                isFlush = false;
            }
        }
        return isFlush;
    }
    //check for pairs, triple and quads

    //This method checks if a hand is a straight or not
    public boolean isStraight(){
        //first put the handArray in order in a new array
        Card[] hand = inAscendingOrder();
        Card card = hand[0];
        boolean straight = true;

        //i starts at 1 because card is already = hand[0]
        for(int i = 1; i < hand.length; i++){
            //if the Card at [i] == card value +1, it must be the next highest card
            if(hand[i].getValue() == card.getValue()+1){
                card = hand[i];
            }
            //otherwise return false because it can't be a straight without all values being 1 higher than the last
            else{
                straight = false;
                return straight;
            }
        }
        return straight;
    }


    //This method puts the hand in ascending order in accordance of value
    public Card[] inAscendingOrder(){
        //first we create a new Card array the same size as our hand
        Card[] orderedArray = handArray;
        //Create a Card object for swapping
        Card swap = orderedArray[0];

        for(int i = 0; i < orderedArray.length; i++){
            //With the second for loop we can compare orderedArray[j] with [k] and swap if necessary
            for(int j = 0, k = 1; j < orderedArray.length; j++, k++){
                //this stops the array index going out of bounds
                if(k == orderedArray.length){
                    k--;
                }
                //if [j] > [k] then swap them
                if(orderedArray[j].getValue() > orderedArray[k].getValue()){
                    swap = orderedArray[j];//[j] is the bigger of the two
                    orderedArray[j] = orderedArray[k];//put [k] in [j]
                    orderedArray[k] = swap;//put [j] in [k]
                }
            }
        }
        return orderedArray;
    }

    public String isPair(){
        String set1, set2;

        for(int i = 0; i < handArray.length; i++){
            set1 = handArray[i].getCardValue();
            for(int j = i+1; j < handArray.length; j++){
                if(j == handArray.length){
                    j--;
                }
                set2 = handArray[j].getCardValue();
                if(set1.equals(set2) && i < j){
                    return set1;
                }
            }
        }
        return "";
    }

	//this method checks for a pair against a string that is passed as a parameter.
	//This is to check for a new pair against an already existing pair
	public String isPair(String reject){
        String set1, set2;

		//the first for loop is used to find the first element in the array that does not have the same String value 
		//as the reject string
        for(int i = 0; i < handArray.length; i++){
            set1 = handArray[i].getCardValue();
			
			//if we have found an element that is not the same as reject and [i] is not the last element, then search
			//the rest of the array for a matching pair
			if(!set1.equals(reject) && i < (handArray.length-1)){
            for(int j = i+1; j < handArray.length; j++){
                set2 = handArray[j].getCardValue();
				//if we have found a match then return it
                if(set1.equals(set2) && i < j){
                    return set2;
                }
            }
			//if we get this far then there is no match
			return "";
			}
        }
        return "";
    }


    public String isThreeOfAKind() {
        String set1, set3;
        int count = 0;

        set1 = isPair();
        if (!set1.equals("")) {
            for (int i = 0; i < handArray.length; i++) {
                if(handArray[i].getCardValue().equals(set1)){
                    count++;
                }
                if(count == 3){
                    return set1;
                }
            }
        }
        return "";
    }

    //check for straight

    //this method gets the high card in the hand
    public String getHighCardString(){
        //assume the first card is the highest in value
        int highCard = handArray[0].getValue();
        String highCardValue = handArray[0].getCardValue();

        //now we check each element in the array. If the current card value at element i is higher than int highCard,
        //set highCard to the new value, and set the String highCardValue to the suit and card value of that card
        for(int i = 0; i < handArray.length; i++){
            if(handArray[i].getValue() > highCard){
                highCard = handArray[i].getValue();
                highCardValue = handArray[i].getCardValue();
            }
        }

        return highCardValue;
    }

    //gets the index of a String in handArray
    public int indexOf(String cardValue){
        int index;
        for(int i = 0; i < handArray.length; i++){
            if(handArray[i].getCardValue().equals(cardValue)){
                return i;
            }
        }
        return -1;
    }

    //gets the integer value of a Card from handArray, by using indexOf method
    public int getValueOf(String cardValue){
        int index = indexOf(cardValue);
        if(index < 0){
            return -1;
        }
        else {
            return handArray[index].getValue();
        }
    }

    //this method returns a visual representation of the Card array handArray as a String
    @Override
    public String toString(){
        String handToString = "{";
        for(int i = 0; i < handArray.length; i++){
            if(i == handArray.length-1) {
                handToString += handArray[i] + "}";
            }
            else {
                handToString += handArray[i] + ",";
            }
        }
        return handToString;
    }

    public int getHandSize() {
        return this.handSize;
    }

    public Card[] getHandArray() {
        return this.handArray;
    }

}
