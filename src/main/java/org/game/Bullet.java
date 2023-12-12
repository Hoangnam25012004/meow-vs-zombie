package org.game;

public class Bullet {
    private int x, y;
    private int Dame;
    private int ID;

    public int getID() { return ID; }
    public int getDame() { return Dame; }
    public Bullet(int x, int y, int ATK, int ID){
        this.x = x;
        this.y = y;
        this.Dame = Dame;
        this.ID = ID;
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
