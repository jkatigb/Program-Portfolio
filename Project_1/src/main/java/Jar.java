import java.util.Random;

/**
 * Created by Josh on 3/27/2017.
 */
public class Jar {

    private final int MAX_CAPACITY, actualInJar;
    private final String name;

    Jar(String name, int MAX_CAPACITY) {
        if (MAX_CAPACITY < 2) {
            this.MAX_CAPACITY = 1;
            this.actualInJar = 1;
            this.name = name;
        } else {
            int i = randomize(MAX_CAPACITY);
            this.MAX_CAPACITY = MAX_CAPACITY;
            this.actualInJar = i + 1;
            this.name = name;
        }

    }

    int getMAX_CAPACITY() {
        return MAX_CAPACITY;
    }

    int getActualInJar() {
        return actualInJar;
    }

    String getName() {
        return name;
    }

    private int randomize(int seed) {
        Random s = new Random();
        return s.nextInt(seed);

    }
}
