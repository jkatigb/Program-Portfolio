import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Josh on 3/28/2017.
 */
public class Game implements Serializable {

    private final Jar gameJar;
    private boolean isWon = false;
    private int guessAttempts = 0;
    private Set<Integer> previousGuesses = new HashSet<>();
    private static ArrayList<Integer> Scores =

            Game(Jar jar)

    {
        this.gameJar = jar;
    }

    boolean applyGuess(final int i) {

        guessAttempts++;
        previousGuesses.add(i);
        if (i == gameJar.getActualInJar()) {
            this.isWon = true;
        } else if (i > gameJar.getActualInJar()) {
            System.out.println(i + " is too high.");
        } else {
            System.out.println(i + " is too low.");
        }

        return isWon;

    }


    Jar getGameJar() {
        return gameJar;
    }

    boolean getIsWon() {
        return isWon;
    }

    int getGuessAttempts() {
        return guessAttempts;
    }

    int getRemainingGuess() {
        return gameJar.getMAX_CAPACITY() - getGuessAttempts();
    }

    Set<Integer> getPreviousGuesses() {
        return previousGuesses;
    }
}
