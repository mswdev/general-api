package org.web;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Sphiinx on 3/16/17.
 */
public class URL {

    /**
     * Opens the specified URI.
     *
     * @param uri The URI to open.
     * @return True if successful; false otherwise.
     * */
    public static boolean openURL(String uri) {
        if (!Desktop.isDesktopSupported())
            return false;

        final Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI(uri));
            return true;
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return false;
    }

}

