package org.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Sphiinx on 3/16/17.
 */
public class Images {

    /**
     * Gets the image from the url.
     *
     * @param url The url of the image.
     * @return The image.
     */
    public static Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

