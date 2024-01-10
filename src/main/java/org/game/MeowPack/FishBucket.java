package org.game.MeowPack;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class FishBucket extends Meow{
    private int fishReleasedSpeed;

    private int meowX,meowY;

    public FishBucket(int meowHP, int meowId, int ATK, int frameCountIdleLimit,int frameCountAttackLimit,int x, int y,int width, int height , int price) {
        super(meowHP, meowId, ATK, frameCountIdleLimit, frameCountAttackLimit,x, y, width,height ,price);
    }
    public FishBucket(){
        super();
    } // for User , QA told me to do this


    private void setInitial(int x, int y){
        this.meowX = x;
        this.meowY = y;
    }


    public void setFishReleasedSpeed(){
        this.fishReleasedSpeed = fishReleasedSpeed;
    }
    private void generateFish() {

    }
    public void update() {

    }
}
