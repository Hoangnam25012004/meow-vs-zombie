package org.game.Component;

import org.game.Manager.MeowManager;
import org.game.Scenes.Playing;

import java.awt.*;

public class Tile {
    private final int ROWS = 5, COLS = 9;
    private int wTile = 87, hTile = 87;
    private Rectangle bound;
    private Boolean occupied = false;
    private int curX, curY;
    private int wTileOfHouseOwner = 160, hTileOfHouseOwner = 90;

    public boolean isPlanted() {
        return isPlanted;
    }

    public void setPlanted(boolean planted) {
        isPlanted = planted;
    }

    private boolean isPlanted = false;
    public int getwTile() {
        return wTile;
    }

    public int gethTile() {
        return hTile;
    }

    private MeowManager meowManager;

    public Tile(Rectangle bound) {
        this.bound = bound;
    }
    public Rectangle getBound() {
        return bound;
    }
    public void setOccupied(Boolean b) {
        occupied = b;
    }
    public boolean isOccupied() {
        return occupied;
    }

    public int getCurX() {
        return curX;
    }

    public void setCurX(int curX) {
        this.curX = curX;
    }

    public int getCurY() {
        return curY;
    }

    public void setCurY(int curY) {
        this.curY = curY;
    }

    public int getWTileOfHouseOwner() {
        return wTileOfHouseOwner;
    }

    public int getHTileOfHouseOwner() {
        return hTileOfHouseOwner;
    }

    public Rectangle getBounds() {
        return bound;
    }
}

