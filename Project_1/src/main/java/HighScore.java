import java.io.*;

/**
 * Created by Josh on 3/28/2017.
 */
public class HighScore {

    public static void save(Game g) {
        try (FileOutputStream fos = new FileOutputStream("highscores.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(g);
        } catch (IOException ioe) {
            System.out.println("Problem saving file");
            ioe.printStackTrace();
        }

    }

    public static Game load() {
        try (FileInputStream fis = new FileInputStream("highscores.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Game g = (Game) ois.readObject();
            Integer[] scores = g.getGuessAttempts();
        } catch (IOException ioe) {
            System.out.println("Problem saving file");
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error loading file");
            cnfe.printStackTrace();
        }

    }
}
