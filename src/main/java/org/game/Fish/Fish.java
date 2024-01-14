package org.game.Fish;

import java.awt.*;

public class Fish {
    private int x, y;
    private int width, height;
    private int boundaryDrop;
    private boolean isFishCLicked = false;
    private boolean isCollected = false;

    public int getX() {
        return x;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    public void setFishCLicked(boolean fishCLicked) {
        isFishCLicked = fishCLicked;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    private Rectangle bound;
    private boolean isThere = false;

    public boolean isThere() {
        return isThere;
    }

    public Rectangle getBounds() {
        return bound;
    }

    public void setThere(boolean there) {
        isThere = there;
    }

    public Fish(int x, int y, int width, int height, int boundaryDrop){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.boundaryDrop = boundaryDrop;
        this.bound = new Rectangle((int)x,(int)y,width,height);
    }
    public void move(){
        if(y<boundaryDrop && !isFishCLicked){
            y++;
            bound.y = (int)y;
        }
    }
    public double calculateDistanceMoveToStorage(){
        double width = x - 375;
        double height = y;
        double ratio = width/height;
        return ratio;
    }

    public double getDistanceTOMoveToStorage() {
        return distanceTOMoveToStorage;
    }

    public void setDistanceTOMoveToStorage(double distanceTOMoveToStorage) {
        this.distanceTOMoveToStorage = distanceTOMoveToStorage;
    }

    public boolean isFishCLicked() {
        return isFishCLicked;
    }
    private double distanceTOMoveToStorage;

    public void setBound(Rectangle bound) {
        this.bound = bound;
    }

    public void setBoundaryDrop(int boundaryDrop) {
        this.boundaryDrop = boundaryDrop;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveToStorage(){
        if(isFishCLicked){
            for(int i = -1;i<8;i++){
                if(distanceTOMoveToStorage> i && distanceTOMoveToStorage<i+1){
                    for(int j = 0;j<8-i;j++){
                        y--;
                        x = (int) (x-distanceTOMoveToStorage);
                        //TODO need more make code clearer (maybe increase step instead of loop)
                    }
                }
            }
        }
    }
}
