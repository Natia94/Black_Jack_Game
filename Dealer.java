package blackjack;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author Natia Khomasuridze
 */
public class Dealer {
    ArrayList <Card> hand; //its a dealers hand
    private int handvalue=0; //dealers hand's values equla 0
    private Card [] aHand; //helps us to convert Dealer's hand to an array
    private int AceCounter; //counts aces in dealers hands
    
    Dealer (Deck deck){ //constructor 
       hand= new ArrayList <>(); //allocated
       aHand=new Card[] {}; //created array 
       int AceCounter=0;
       for (int i=1; i<=2; i++){
           hand.add(deck.drawCard());
       }
       
       aHand=hand.toArray(aHand);//so I converted hand to an araay of aHand
       for (int i=1; i<aHand.length; i++){
           handvalue+=aHand[i].getValue(); //sumup all the values into the handvalue
           if(aHand[i].getValue()==11)
           {AceCounter++;}// counts how many Aces are in the aHand array
           while (AceCounter>0 && handvalue>21){
               handvalue-=10; //so Ace is 11 or 1 and by writing -10 it becomes 1;
               AceCounter--; //and I have -1 Ace in my array aHand
           }
          
       }
           
    }
    public void DealersFirstCard (){ //dealer shows the first card
        Card [] firstcard=new Card[]{}; //created an aray of first card
        firstcard=hand.toArray(firstcard); //hand's first card put to firstcard array
        System.out.println(firstcard[0]); 
        
    }
            
    public void Hit (Deck deck){ // Dealer gets the anonther card and updates the ACE values
        hand.add(deck.drawCard());
        aHand=hand.toArray(aHand);
        handvalue = 0;
        for(int i=0; i<aHand.length; i++){
        handvalue += aHand[i].getValue();
        if(aHand[i].getValue()==11)
        {AceCounter++;}
        while(AceCounter>0 && handvalue>21){
            handvalue-=10;
            AceCounter--;
        }
    }
        
    
    public boolean DealerWantsToHit(){
        if(handvalue<=16)
        return true;
        else
        return false;
    }
    
    public boolean DealerhasBlackJack(){
        if(hand.size()==2 && handvalue==21){
        System.out.println("Dealer got Blackjack!");
        return true;
        }
        else 
        return false;
    }
    
    public void DealerShowsHand(){ //Dealer shows his cards
        System.out.println(hand);
    }
    
    public int getHandValue(){ //Getter of the Dealer's handvalue
        return handvalue;
    }
    
    public boolean busted(int handvalue){
        if(handvalue>21){
        System.out.println("Dealer busted!");
        return true;
    }
    else
    return false;
    }
    
    public int takeTurn(Deck deck){
    while(DealerWantsToHit()){
        System.out.println("Dealer hits");
        Hit(deck);
        if(busted(handvalue)){
            break;
        }
    }
    if(handvalue<=21)
        System.out.print("Dealer stands.");
    
    return handvalue; 
    }  
    
}
