package util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Sphiinx on 3/16/17.
 */
public class Random {

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}

