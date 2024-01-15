package org.game.Fish;

import org.game.Scenes.Playing;

import java.awt.*;
import java.util.Iterator;

public class Shark {

    private Playing playing;
    private int x;
    private int y;
    private int width;
    private int height;
    private int Dame = 10000;
    private int sharkCount=0;
    private int[] sharkRow = new int[5];
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Image[] sharkImage = new Image[5];

    public Shark(int x, int y, int width, int height){
        this.x=x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getDame() {
        return Dame;
    }

    public void setX(int x){
        this.x = x;
    }

    public void move() {
        if (x<1070){
            x += 1;
        }
    }


}
