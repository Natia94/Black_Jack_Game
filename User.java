package blackjack;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author Natia Khomasuridze
 */
public class User {
  private static int cash;// user's cash
  private static int bet;//users bet
  private static int Acecounter=0;//counts the Aces of the users hand
  private static ArrayList<Card> hand; //users hand
  private static String UserName; //users name
  private static int Handvalue;// users handvalue
  
    
    public static void main (String[] args){
     System.out.println("what is the user's name?");
     Scanner name=new Scanner (System.in); //variable scan is a Scanner, gets our input
     UserName=name.nextLine(); //scan name
     System.out.println("UsersName is "+UserName);
     System.out.println("how much money you have?");
     Scanner money=new Scanner(System.in); //scan money
     cash=money.nextInt(); //same like cin>> in c++
     System.out.println("you started with "+cash+"cash");
     while(cash>0){ //while the userr has the money
        Deck deck=new Deck();
        deck.shuffle();
        Dealer dealer=new Dealer(deck);
        
        List<Card> hand=new ArrayList <>(); // created an arraylist of type Card
        hand.add(deck.drawCard()); //draw a first card
        hand.add(deck.drawCard()); //draw a second card
        System.out.println("How much would you like to bet?");
        bet=Bet(cash);
        System.out.println("Your cash is:"+(cash-bet));
        System.out.println("Money on the table:"+bet);
        System.out.println("Your hand is: "+ hand);
        
        int handvalue=UsersHandValue(hand);
        System.out.println("Dealer's first card is: ");
        dealer.DealersFirstCard(); 
        if(hasBlackJack(handvalue) && dealer.DealerhasBlackJack()){
            Push();
        }
        else if(hasBlackJack(handvalue)){ //if user had blackjack
            System.out.println("You have BlackJack and will get more money!");
            cash=cash+bet;
            Win();
        }
        else if(dealer.DealerhasBlackJack()){//check if the dealer has blackjack.
            System.out.println("Here is the dealer's hand:");
            dealer.DealerShowsHand(); 
            Lose(); //user looses
        }
        
        else{
            if(2*bet<cash){ //for double down
                System.out.println("Do you want to double down?");
                Scanner doubledown = new Scanner(System.in);
                String doubled = doubledown.nextLine();
                while(!isyesorno(doubled)){
                    System.out.println("enter only yes or no.");
                    doubled = doubledown.nextLine();
                }
                if(doubled.equals("yes")){
                    System.out.println("You have opted to double down!");
                    bet=2*bet;
                    System.out.println("Your Cash is:"+(cash-bet));
                    System.out.println("remainig money is :"+bet);
                }
            }
       
            
            
            
            
        System.out.println("Would you like to hit or stand?");//ask if the user will hit or stand
            Scanner hitorstand = new Scanner(System.in);
            String hitter = hitorstand.nextLine();
            while(!isHitorStand(hitter)){
                System.out.println("Please enter 'hit' or 'stand'.");
                hitter = hitorstand.nextLine();
            }
            while(hitter.equals("hit")){
                Hit(deck, hand);
                System.out.println("Your hand is now:");
                System.out.println(hand);
                handvalue = UsersHandValue(hand);
                if(checkBust(handvalue)){
                    Lose();
                    break;
                }
                System.out.println("Do you want to hit or stand?");
                hitter = hitorstand.nextLine();
            }
            if(hitter.equals("stand"))
            {
                int dealerhand = dealer.takeTurn(deck);//takes the turn for the dealer.
                System.out.println("Here is the dealer's hand:");
                dealer.DealershowsHand();
                if(dealerhand>21)//if the dealer busted, user wins.{
                    Win();
                }
                else
                {
                    int you = 21-handvalue;//check who is closer to 21 and determine winner
                    int dealerr = 21-dealerhand;
                    if(you==dealerr)
                    {
                        Push();
                    }
                    if(you<dealerr)
                    {
                        Win();
                    }
                    if(dealerr<you)
                    {
                        Lose();
                    }
                }
            }
        }
     
     
            
            
          
     }
     
  
    
    
    
    
    public void DealersFirstCard(){
    Card[] DealersfirstCard = new Card[]{};
    DealersfirstCard = hand.toArray(DealersfirstCard);
    System.out.println("Dealers first Card is: "+ DealersfirstCard[0]);
    }
    
    public static boolean hasBlackJack(int handValue){
    if(handValue==21)
        return true;
        else 
        return false;
    }
    public static int UsersHandValue(List<Card> hand){ 
    Card[] aHand = new Card[]{}; //emtry array aHand
    aHand = hand.toArray(aHand); //convert hand to aHand
    int handvalue=0;      
    for(int i=0; i<aHand.length; i++){
        handvalue += aHand[i].getValue();
        if(aHand[i].getValue()==11)
        Acecounter++;
        while(Acecounter>0 && handvalue>21){
            handvalue-=10;
            Acecounter--;
        }
    }
    return handvalue;
    }
    
    public static int Bet(int cash){ 
        Scanner scann=new Scanner(System.in);
        int bet=scann.nextInt();
        while(bet>cash){ //does not let bet to be greater than cash
        System.out.println("You cannot bet more cash than you have! try again: " );
        bet=scann.nextInt(); //new amunt of money
       }
    return bet;
    }
    
    public static void Win(){
    System.out.println("You win!");
    cash=cash+bet;
    System.out.println("Your Cash is: "+cash);
    }

    public static void Lose(){
    System.out.println("You lose!");
    cash=cash-bet;
    System.out.println("Your Cash is : "+cash);
    }

    public static void Push(){
    System.out.println("push! getting your money back");
    System.out.println("Cash: "+cash);
    }
    
    public static void Hit(Deck deck, List<Card> hand){ //add new card to users hand and calculate the handvalue
    hand.add(deck.drawCard());
    Card[] aHand = new Card[]{};
    aHand = hand.toArray(aHand);
    Handvalue = 0;
    for(int i=0; i<aHand.length; i++){
        Handvalue += aHand[i].getValue();
        if(aHand[i].getValue()==11){
            Acecounter++;
        }
        while(Acecounter>0 && Handvalue>21){
            Handvalue-=10;
            Acecounter--;
        }
    }
    }
    
    public static boolean isHitorStand(String hitter){ //if user input hit or stand
    if(hitter.equals("hit") || hitter.equals("stand"))
    return true;
    else
    return false;
    }

    public static boolean checkBust(int handvalue){  //if user has busted
    if(handvalue>21){
    System.out.println("You have busted!");
    return true;
    }
    return false;
    }

    public static boolean isyesorno(String answer){ //is users answer is yes or no
    if(answer.equals("yes") || answer.equals("no"))
    return true;
    else
    return false;
    }

     
     
 }
  
  
  
  
    

