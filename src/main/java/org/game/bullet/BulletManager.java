package org.game.bullet;

public class BulletManager {
    public double x;
    public int y;
    private final int Dame;
    private boolean isFrozen = false;

    public BulletManager(double x, int y, int Dame){
        this.x = x;
        this.y = y;
        this.Dame = Dame;
    }

    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }

    public int getY () {
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
    public void setisFrozen(boolean isFrozen) {
        this.isFrozen = isFrozen;
    }


    public void move () {
        if (this.x<1024) {
            this.x += 8;
        }
    }
}
