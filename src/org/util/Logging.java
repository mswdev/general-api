package org.util;

/**
 * Created by Sphiinx on 3/24/17.
 */
public class Logging {

    /**
     * Prints the specified message with the header "ERROR: ".
     *
     * */
    public static void error(String message) {
        System.out.println("[ERROR]: " + message);
    }

    /**
     * Prints the specified message with the header "WARNING: ".
     *
     * */
    public static void warning(String message) {
        System.out.println("[WARNING]: " + message);
    }

    /**
     * Prints the specified message with the header "STATUS: ".
     *
     * */
    public static void status(String message) {
        System.out.println("[STATUS]: " + message);
    }

}

