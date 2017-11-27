package main.util.timing;

/**
 * Created by Sphiinx on 6/15/2017.
 */
public class Timing {

    /**
     * Waits for the current condition to be active.
     *
     * @param condition The condition.
     * @param timeout   How many milliseconds to wait before giving up.
     * @return True if the condition was true before the timeout; false otherwise.
     */
    public static boolean waitCondition(Condition condition, long timeout) {
        final long time = System.currentTimeMillis() + timeout;
        while (!condition.active()) {
            if (System.currentTimeMillis() >= time)
                return false;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}

