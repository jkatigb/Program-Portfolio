import model.SongBook;

/**
 * Created by Josh on 3/29/2017.
 */

public class Karaoke {

    public static void main(String[] args) {

        SongBook songBook = new SongBook();
        KaraokeMachine machine = new KaraokeMachine(songBook);
        machine.run();
    }
}
