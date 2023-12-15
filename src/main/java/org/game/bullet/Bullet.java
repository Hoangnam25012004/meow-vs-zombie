package org.game.bullet;

public class Bullet {
    private int x, y;
    private int Dame;
 
 
    public int getDame() { return Dame; }
    public Bullet(int x, int y, int Dame){
        this.x = x;
        this.y = y;
        this.Dame = Dame;

    }
        
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY () {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void move () {
        if (this.x<1024) { 
            this.x += 8;
        }
    }
}
