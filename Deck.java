package blackjack;
import java.security.SecureRandom;
/**
 *
 * @author Natia Khomasuridze
 */
public class Deck {
    private Card[] deck;
    private int currentCard;
    private static final int NUMBER_OF_CARDS = 52;
    
    private static final SecureRandom random = new SecureRandom();
    
    public Deck() {
        
        deck = new Card[NUMBER_OF_CARDS];
        
        for(int count = 0; count < deck.length; count++)
            deck[count] = new Card(rank[count % 13], suit[count / 13]);
    }
    
      public void shuffle() {
        currentCard = 0;
        for(int first = 0; first < deck.length; first++) {
            int second = random.nextInt(NUMBER_OF_CARDS);
            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        }
    }
    
    public Card dealCard() {
        if(currentCard < deck.length)
            return deck[currentCard++];
        return null;
    }
  
    public Card drawCard(){
    return deck.remove(0);
    }
}
