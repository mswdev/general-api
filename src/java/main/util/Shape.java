package main.util;

import java.awt.*;

/**
 * Created by Sphiinx on 3/16/17.
 */
public class Shape {

    /**
     * Gets a random point within a rectangle.
     *
     * @param r Rectangle.
     * @return Random point in the rectangle.
     */
    public static Point getRandomPointInRectangle(Rectangle r) {
        if (r == null)
            return null;

        final int randomX = Random.getRandomInt(r.x, r.x + r.width);
        final int randomY = Random.getRandomInt(r.y, r.y + r.height);

        return new Point(randomX, randomY);
    }

}

