
package blackjack;
import java.util.ArrayList;
/**
 *
 * @author Natia Khomasuridze
 */
public class Card {
private final int rank;
private final int suit;
 
private static String [] ranks = { "Joker", "Ace", "Two", "Three", "Four",
        "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen",
        "King" }; 
private static String [] suits = { "Clubs", "Diamons", "Hearts", "Spades" };
 
 @Override //overrides-returns the string version of a string
    public String toString() {
        return rank + " of " + suit;
    }
    
    Card(int suit, int values) {
    this.rank = values;
    this.suit = suit;
    }
    public int getRank () {
        return rank;
    }
    public int getSuit () {
        return suit;
    }
    
   public int getValue () {
       int value;
    if (rank > 10) { // for king, quueen and jack
        value = 10;
    } 
    else if (rank == 1) {
        value = 11; 
    } 
    else {
        value = rank; 
    }
    return value;

   }  
}
