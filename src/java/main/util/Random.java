package main.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Sphiinx on 3/16/17.
 */
public class Random {

    /**
     * Gets a random int between the two parameters.
     *
     * @param min The minimum number to return an int.
     * @param max The maximum number to return an int.
     * @return The returned randomized int.
     * */
    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}

