/**
 * Created by Josh on 3/28/2017.
 */
class Validator {

    static String normalize(String s) {
        if (isValid(s)) {
            return s.toLowerCase();
        } else {
            throw new IllegalArgumentException("Sorry, I can't put that in the jar...");
        }
    }

    static int normalizeInt(String s) {
        if (!isValidInt(s)) {
            throw new IllegalArgumentException("Please guess a positive amount...");
        } else {
            return Integer.parseInt(s);
        }
    }

    private static boolean isValid(String s) {

        char[] cArray = s.toCharArray();
        for (char c : cArray) {
            if (!Character.isLetter(c) || Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidInt(String s) {
        try {
            int i = Integer.parseInt(s);
            if (i == 0) {
                return false;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }


        return true;
    }

    static int validateGuess(final Game g, final int i) {

        //best to use a set to avoid having to check for uniqueness
        if (g.getPreviousGuesses().contains(i)) {
            throw new IllegalArgumentException("You already guessed that!");
        } else if (i < 0) {
            throw new IllegalArgumentException("You can't negative guess!");
        } else if (i == 0) {
            throw new IllegalArgumentException("Zero items is not a guess either.");
        } else if (i > g.getGameJar().getMAX_CAPACITY()) {
            throw new IllegalArgumentException("Warning: your guess must be between 1 and " + g.getGameJar().getMAX_CAPACITY());
        }
        return i;


    }


}
