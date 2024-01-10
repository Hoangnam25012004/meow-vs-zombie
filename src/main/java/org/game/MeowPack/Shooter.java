package org.game.MeowPack;
import org.game.bullet.Bullet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Shooter extends Meow {

    private Bullet[] bullets;
    private boolean isAbleToFreeze;
    // true - SnowMeow, false - ShooterMeow
  //  private boolean collide = false;
    private int meowX,meowY;
    private int counter = 0, counterNum = 1;

    public Shooter(int meowHP, int meowId, int ATK, int frameCountIdleLimit,int frameCountAttackLimit,int x, int y,int width, int height , int price) {
        super(meowHP, meowId, ATK, frameCountIdleLimit, frameCountAttackLimit,x, y, width,height ,price);
    }

    public void shoot() {

    }
    private void setInitial(int x, int y){
        this.meowX = x;
        this.meowY = y;
    }
    public int getMeowX(){
        return this.meowX;
    }
    public int getMeowY(){
        return this.meowY;
    }
    /*public void setCollide(boolean c){
        this.collide = c;
    }*/


}
