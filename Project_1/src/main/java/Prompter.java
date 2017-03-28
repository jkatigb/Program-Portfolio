import java.util.Scanner;

/**
 * Created by Josh on 3/27/2017.
 */
class Prompter {

    private Game currentGame;

    public Prompter(Game g) {
        this.currentGame = g;
    }

    public boolean promptForGuess() {
        boolean hit = false;
        Scanner input = new Scanner(System.in);

        //validation class contains most of the normalization and validation logic
        //if any of these steps are not executed before apply guess, we print the error
        //continue while loop in main()

        try {
            int normalizeGuess = Validator.normalizeInt(input.nextLine());
            int validatedGuess = Validator.validateGuess(currentGame, normalizeGuess);
            hit = currentGame.applyGuess(validatedGuess);

        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
        return hit;
    }

    public void displayOutcome() {
        if (!currentGame.getIsWon()) {
            System.out.println("Aw too bad, maybe next jar.");
        } else if (currentGame.getGuessAttempts() == 1) { //check if singular usage
            System.out.println("Whow, it only took you 1 attempt to win "
                    + currentGame.getGameJar().getActualInJar()
                    + " "
                    + currentGame.getGameJar().getName()
                    + ". Congratulations!");
        } else {
            System.out.printf("Whow, %d attempts to win %d %s! Congratulations! %n",
                    currentGame.getGuessAttempts(),
                    currentGame.getGameJar().getActualInJar(),
                    currentGame.getGameJar().getName());
        }
    }
}


