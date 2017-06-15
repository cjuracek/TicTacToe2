import java.util.Scanner;

/**
 * Created by cole.juracek on 6/13/2017.
 */
public class Runner {

    private static Game game = new Game();

    public static void main(String[] args) {

        // Continue to play games until user quits
        while(true) {
            startGame();
            resetOrEndGame();
        }
    }

    /**
     * Get input from the user until the game is finished, then
     * call the relevant game ending methods
     */
    private static void startGame() {
        Scanner input = new Scanner(System.in);

        do {
            game.switchPlayers();

            System.out.println("Please enter a row (1-3): ");
            int row = input.nextInt();
            System.out.println("Please enter a column (1-3): ");
            int col = input.nextInt();

            if(game.isValidMove(row - 1, col - 1)) {
                game.addPiece(row - 1, col - 1);
                System.out.println(game.getBoard());
            }
            else {
                System.out.println("Error: Invalid move!");
            }
        } while(!game.isGameOver());

        game.displayGameResult();
    }

    /**
     * Get input from the user to either reset or end the game
     */
    private static void resetOrEndGame() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Play again? (y/n)");
        char response = keyboard.next().charAt(0);

        switch(Character.toUpperCase(response)) {
            case 'Y' : game = new Game();
                       break;
            case 'N' :
                System.out.println("Thanks for playing!");
                System.exit(0);
                break;
        }
    }
}
