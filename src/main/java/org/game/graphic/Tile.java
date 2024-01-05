package org.game.graphic;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    private Rectangle bound;

    public BufferedImage image;
    private Graphical graphical;

    public Tile(Rectangle bound){this.bound = bound;}
    public int getGrassTile(){
        return graphical.grassTile;
    }

    public Rectangle getBound() {
        return bound;
    }
}
