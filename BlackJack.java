import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Creates the card game BlackJack by using ArrayLists
 *
 * @author Pharez Varlock
 */
public class BlackJack {

  private static final int DECK_SIZE   = 52;
  private static final int Black_Jack  = 21;


  /**
   * Creates and returns an arrayList to be able to remove items from the deck
   *
   * @return ArrayList?
   */
  public static ArrayList<Integer> createDeck() {

    ArrayList<Integer> deck = new ArrayList<Integer>();

    // fills an arrayList with the numbers 1 - 10 representing card valus from 1 - 10

    for (int i = 0; i < 4; i++) {

      for (int j = 1; j < 11; j++ ){

          deck.add(j);

      }
    }

    // fills the rest of the arrayList with the number 10 representing all the royal card values

    for (int i = 0; i < 12; i++) {

      deck.add(10);
    }

    return deck;
  }

//--------------------------------------------------------------

/**
 *puts the arrayList into a random order
 *Iterates through the arrayList and randomly chooses another location within the arrayList to switch
 * @param deck  Integer ArrayList
 */
public static void shuffle(ArrayList<Integer> deck) {

  Random rand = new Random();

    int r;
    int temp;
    int temp2;

  for(int i = 0; i < 200; i++) {

    for (int j = 0; j < DECK_SIZE - 1; j++) {

        r = j + rand.nextInt(DECK_SIZE - 1 - j);

        temp  = deck.remove(r);
        temp2 = deck.remove(j);

      deck.add(r, temp);
      deck.add(j, temp2);
    }

  }
}

//-------------------------------------------------------------
/*
 *Displays an arrayList
 *iterates through the ArrayList and prints out each index
 *the integer paramater allows certain cards to be hidden such as the dealers first card
 *@param ArrayList, int
 */
public static void display(ArrayList<Integer> deck, int start) {

  for (int i = start; i < deck.size(); i++) {

    System.out.println(deck.get(i) + "\t" );
  }
}

//---------------------------------------------------------------

public static void hit(ArrayList<Integer> player, int card) {

  player.add(card);
}

//-------------------------------------------------------------
/*
 *Creates the dealers hand
 *Checks to see if the dealer gets BlackJack and ends the program if it does
 *Stops the progrma if the dealer gets blackJack  
 *@param dekc Integer ArrayList, dealer Integer ArrayList
 */
public static void dealerHand(ArrayList<Integer> dealer, ArrayList<Integer> deck) {

  hit(dealer, deck.remove(deck.size() - 1));
  hit(dealer, deck.remove(deck.size() - 1));

  if(checkTotal(dealer) == Black_Jack) {
    System.out.println("Dealer got Black Jack");
    display(dealer, 0);
    System.exit(0);
  }
//Stops hitting the dealer when the dealer lands a soft 17
  while (checkTotal(dealer) < 17) {

    hit(dealer, deck.remove(deck.size() - 1));

  }
}
//-----------------------------------------------------------

/**
 *Gets the total
 * @param hand Integer ArrayList
 * @return int  sum of the numbers in the arrayList
 */
public static int checkTotal(ArrayList<Integer> hand) {

  int total = 0;

  for (int i = 0; i < hand.size(); i++) {

    total += hand.get(i);
  }

  return total;

}

//----------------------------------------------------------
/*
 *Checks to see the winner of the game Black_Jack by comparing the total value in each Hand
 * @param player Intger ArrayList, dealer Integer ArrayList
 */
public static String checkWinner(ArrayList<Integer> player, ArrayList<Integer> dealer) {

  if (checkBust(player) && checkBust(dealer))
    return  "Its a tie!!!";

  else if (checkBust(player) && !checkBust(dealer))
      return "The Dealer won";

  else if (!checkBust(player) && checkBust(dealer))
      return  "You won!";

  else if (checkTotal(player) > checkTotal(dealer))
      return  "You won!";

  else if (checkTotal(player) < checkTotal(dealer))
      return "The Dealer won";

  else
    return "Its a tie";
}
//------------------------------------------------------------
public static boolean checkBust(ArrayList<Integer> hand) {

   return checkTotal(hand) > Black_Jack;
}

//-----------------------------------------------------------

  public static void main (String args []){

    ArrayList<Integer> deck   = new ArrayList <>();
    ArrayList<Integer> dealer = new ArrayList <>();
    ArrayList<Integer> player = new ArrayList <>();

    Scanner in = new Scanner(System.in);

    deck = createDeck();

    shuffle(deck);

    System.out.println("Starting game of BlackJack\n");

    dealerHand(dealer, deck);

    System.out.println("The dealer is showing: ");
    display(dealer, 1);

    System.out.println("\n");

    hit(player, deck.remove(deck.size() - 1));
    hit(player, deck.remove(deck.size() - 1));

    System.out.println("Player's Hand: ");
    display(player, 0);

    System.out.println("\nPress H if you want a hit other wise press any key and hit enter for the final result");

    //Allows the player to keep hitting until they desire not to
    while(in.next().equalsIgnoreCase("H")){

      hit(player, deck.remove(deck.size() - 1));
      //stops letting the player hit when the player's hand total is greater than 21
      if(checkTotal(player) > Black_Jack) {
          break;
      }

      System.out.println("Your hand now is: ");
      display(player, 0);
      System.out.println("\nPress H if you want a hit other wise press any key for the final result");

    }

    System.out.println(checkWinner(player, dealer));
    System.out.println("The dealer had a total of: " + checkTotal(dealer));
    display(dealer, 0);
    System.out.println("You had a total of: " + checkTotal(player));
    display(player, 0);
  }
}
