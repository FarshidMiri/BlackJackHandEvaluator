// Programmer: Farshid Miri; Medet Karl
// Class: CS &145
// Date: 10/16/2023
// Assignment: Deck of Card, Lab#4
// Source is ChatGPT

// Purpose: This Java program uses the methods developed in exercise 7.16 to write an application that deals with two five-card blackjack hands, 
// evaluates each hand and determines which is better and immediately stops game when someone exceeds 21 or have exact 21 points 

// For extra points we added try/catch for choice of player and dealer, so programm should not give any error messages, it repeats when there are no "yes" or "no" answers,
// same for play again, but it does not have try/catch.


import java.util.Random;
import java.util.Scanner;

public class MKFMBlackjackHandEvaluator {

    private int[] dealerHand;
    private int[] playerHand;
    private int dealerIndex;
    private int playerIndex;
    boolean continueTurn = true;
    public MKFMBlackjackHandEvaluator() {
        dealerHand = new int[5];
        playerHand = new int[5];
        dealerIndex = 2;
        playerIndex = 2;
        generateInitialHands();
        
    } //end of BlackjackHandEvaluator
    
        public void displayInstructions() {
        
        System.out.println("Welcome to Blackjack!");
        System.out.println("Instructions:");
        System.out.println("1. The goal is to get a hand total as close to 21 as possible without exceeding 21.");
        System.out.println("2. Number cards are worth their face value. Face cards (Jack, Queen, King) are worth 10 points.");
        System.out.println("3. An Ace can be worth 1 or 11 points, whichever is more favorable.");
        System.out.println("4. The dealer will deal two cards to you and two to themselves.");
        System.out.println("5. You can choose to 'hit' (take another card) or 'stand' (keep your current hand).");
        System.out.println("6. If your total exceeds 21, you lose. If the dealer's total exceeds 21, you win.");
        System.out.println("7. Type 'yes' to take another card, or 'no' to end your turn.");
        
    } // end of instruction

    public void generateInitialHands() {
        Random rand = new Random();
        dealerHand[0] = rand.nextInt(13) + 1; // Random card value of dealer first card (1-13)
        dealerHand[1] = rand.nextInt(13) + 1; // Random card value of dealer second card (1-13)
        playerHand[0] = rand.nextInt(13) + 1; // Random card value of player first card (1-13)
        playerHand[1] = rand.nextInt(13) + 1; // Random card value of player second card (1-13)
        
    } // end of generateInitialHands

    public void printHands() {
        System.out.println("Dealer's Hand: ");
        for (int i = 0; i < dealerIndex; i++) {
            System.out.print(getCardName(dealerHand[i]) + " ");
        }
        
        System.out.println();
        System.out.print("Player's Hand: ");
        for (int i = 0; i < playerIndex; i++) {
            System.out.print(getCardName(playerHand[i]) + " ");
        }
        
        System.out.println();
    } // end of printHands

public void playerTurn() {
       Scanner scanner = new Scanner(System.in);

    while (playerIndex < 5 && continueTurn) { // Allow up to 5 extra cards
        try {
            System.out.println("Does player want another card? (yes/no)");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("yes")) {
                playerHand[playerIndex] = new Random().nextInt(13) + 1; // Deal a new card
                playerIndex++;
                printHands(); // Print the updated hands

                int totalPlayer = getTotalValue(playerHand);
                if (totalPlayer == 21) {
                    System.out.println("Player's total value is 21. Player wins");
                    continueTurn = false;
                } else if (totalPlayer > 21) {
                    System.out.println("Player's total value is over 21. Player loses.");
                    continueTurn = false;
                }
            } else if (response.equalsIgnoreCase("no")) {
                break; // Exit the loop and end the player's turn
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid answer.");
        }
    } // end of while loop
    
} // end of playerTurn

    public void dealerTurn() {
 Scanner scanner = new Scanner(System.in);

    while (dealerIndex < 5 && continueTurn) { // Allow up to 5 extra cards
        try {
            System.out.println("Does dealer want another card? (yes/no)");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("yes")) {
                dealerHand[dealerIndex] = new Random().nextInt(13) + 1; // Deal a new card
                dealerIndex++;
                printHands(); // Print the updated hands

                int totalDealer = getTotalValue(dealerHand);
                if (totalDealer == 21) {
                    System.out.println("Dealer's total value is 21. Dealer wins");
                    continueTurn = false;
                } else if (totalDealer > 21) {
                    System.out.println("Dealer's total value is over 21. Player wins.");
                    continueTurn = false;
                }
            } else if (response.equalsIgnoreCase("no")) {
                break; // Exit the loop and end the dealer's turn
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid answer.");
        }
    } // end of while loop
    
 } // end of dealerTurn

    private String getCardName(int cardValue) {
        if (cardValue == 1) {
            return "Ace";
        } else if (cardValue == 11) {
            return "Jack";
        } else if (cardValue == 12) {
            return "Queen";
        } else if (cardValue == 13) {
            return "King";
        } else {
            return Integer.toString(cardValue);
        }
    } // end of getCardName

    private int getTotalValue(int[] hand) {
        int total = 0;
        int numAces = 0;

        for (int cardValue : hand) {
            if (cardValue == 1) {
                numAces++;
                total += 11; // Assume Ace as 11 initially
            } else if (cardValue >= 11 && cardValue <= 13) {
                total += 10; // Face cards worth 10
            } else {
                total += cardValue;
            }
        }

        while (total > 21 && numAces > 0) {
            total -= 10; // Change Ace value to 1 if bust
            numAces--;
        }

        return total;
    } // end of getTotalValue

    public void evaluateHands() {
        System.out.println("Final Hands:");
        System.out.print("Dealer's Hand: ");
        for (int i = 0; i < dealerIndex; i++) {
            System.out.print(getCardName(dealerHand[i]) + " ");
        }
        System.out.println("Total Value: " + getTotalValue(dealerHand));

        System.out.print("Player's Hand: ");
        for (int i = 0; i < playerIndex; i++) {
            System.out.print(getCardName(playerHand[i]) + " ");
        }
        System.out.println("Total Value: " + getTotalValue(playerHand));

        int totalDealer = getTotalValue(dealerHand);
        int totalPlayer = getTotalValue(playerHand);

        if (totalPlayer > 21 || (totalDealer <= 21 && totalDealer >= totalPlayer)) {
            System.out.println("\nDealer wins!");
        } else {
            System.out.println("\nPlayer wins!");
        }
    } // end of evaluateHands
    

}//end class