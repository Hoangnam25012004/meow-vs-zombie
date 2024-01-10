package org.game.graphic;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    private Rectangle bound;

    public BufferedImage image;
    public int grassTile =87;

    public Tile(Rectangle bound){this.bound = bound;}
    public int getGrassTile(){
        return grassTile;
    }

    public Rectangle getBound() {
        return bound;
    }
}
