import java.util.Scanner;

/**
 * Created by Josh on 3/27/2017.
 */

public class GuessingGame {

    public static void main(String[] args) {
        String itemType = "";
        int fillAmount = 0;
        Scanner input = new Scanner(System.in);

        //if no arguments are supplied, will throw npe to initiate administrator setup
        try {
            if (args.length == 2) {
                itemType = Validator.normalize(args[0]);
                fillAmount = Validator.normalizeInt((args[1]));
            } else {
                System.out.println("Usage: GuessingGame.java <itemName> <maxItem> \n");
                System.err.print("Item Name and Max Capacity required \n");
                throw new NullPointerException();
            }
        } catch (NullPointerException npe) {

            //if itemType and fill amount have not been supplied inputs
            while (itemType.equals("") && fillAmount == 0) {
                try {
                    System.out.println("What type of item do you want to place in the jar?");
                    itemType = Validator.normalize(input.nextLine());
                    System.out.println("What is the maximum amount of " + itemType + " in the jar? (ex. 42)");
                    fillAmount = Validator.normalizeInt(input.nextLine());
                } catch (IllegalArgumentException iae) {

                    //catching an error resets itemType and fill amount to the true condition)
                    System.out.println(iae.getMessage());
                    itemType = "";
                    fillAmount = 0;
                }
            }

            Jar jar = new Jar(itemType, fillAmount);
            Game game = new Game(jar);
            Prompter prompter = new Prompter(game);

            System.out.println("How many "
                    + game.getGameJar().getName()
                    + " are in the jar? (between 1 and "
                    + game.getGameJar().getMAX_CAPACITY()
                    + ").");

            do {
                prompter.promptForGuess();
            } while (game.getRemainingGuess()
                    > 0 && !game.getIsWon());

            prompter.displayOutcome();

            HighScore.save(game);


        }
    }
}










