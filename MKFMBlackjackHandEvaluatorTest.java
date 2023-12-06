import java.util.Scanner;   
   public class MKFMBlackjackHandEvaluatorTest {
   
      public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       String playAgainResponse;
       boolean playAgain = true;
       boolean Input = false;
       MKFMBlackjackHandEvaluator evaluator = new MKFMBlackjackHandEvaluator();
       evaluator.displayInstructions();
       
       do {
           
           evaluator.printHands(); // Print initial hands
           evaluator.playerTurn(); // Ask the player for additional cards
           evaluator.dealerTurn(); // Ask the dealer for additional cards
           evaluator.evaluateHands();  // Evaluate hands after player's turn
   
           System.out.println("Do you want to play another game? (yes/no)");
           
           
          while(!Input){
          playAgainResponse = scanner.nextLine().toLowerCase();
           switch (playAgainResponse) {
               case "yes":
               Input = true;
                   // Continue with a new game
                   break;
               case "no":
               Input = true;
                   System.out.println("Thank you for playing. Goodbye!");
                   playAgain = false;
                   break;
               default:
                   System.out.println("Invalid input. Please enter lower case 'yes' or 'no' in the next try.");
                   break;
         }
   }
       } while (playAgain);

    
    scanner.close(); // Close the scanner
    
} //end main method
 
 } // end of class