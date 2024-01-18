package org.game.bullet;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Bullet {
    private double x;
    private int y;
    private final int Dame;
    private boolean isFrozen = false;
    private final int bulletSize = 6;

    public Bullet(double x, int y, int Dame, boolean isFrozen) {

        this.x = x;
        this.y = y;
        this.Dame = Dame;
        this.isFrozen = isFrozen;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDame() {
        return Dame;
    }

    public boolean getisFrozen() {
        return isFrozen;
    }


    public void move() {
        if (this.x < 1070) {
            this.x += 8;
        }
    }
}
