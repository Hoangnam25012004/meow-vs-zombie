package org.game.MeowPack;

import org.game.Component.Tile;
import org.game.Hitbox.Rect;
import org.game.Manager.*;
import org.game.bullet.Shooting;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Random;

public class Meow implements Shooting
{
    private int meowId;
    // 1 - ShooterMeow, 2 - SnowMeow, 3 - FishBucket, 4 - BombPate, 5 - StinkyPate
    private String meowName;
    private Rect rect;
    private int healthPoint;
    private int meowATK;
    private int price;
    private boolean alive = false;
    private boolean isAbleToFreeze = false;

    private int frameCountIdleLimit = 1;
    private int frameCountIdle = 0;
    private int frameCountStinkyPate=0;
    private int frameCountAttack = 0;
    private int frameCountFish = 0;
    private int frameCountFishLimit = 600;
    private int frameCDIdle = 0;
    private int frameCDAttack = 0;
    private Random random = new Random();
    private int explodeCD;
    private boolean isDangered = false;
    public BufferedImage[] image = new BufferedImage[5];

    public int getATK() {
        return meowATK;
    }
    public int getFrameCDAttack(){
        return frameCDAttack;
    }
    public int getFrameCountIdle() {
        return frameCountIdle;
    }
    public int getFrameCountAttack() {
        return frameCountAttack;
    }
    public int getFrameCountStinkyPate() {return  frameCountStinkyPate;}
    public void setFrameCountAttack(int frameCountAttack) {
        this.frameCountAttack = frameCountAttack;
    }
    public void setFrameCountIdle(int frameCountIdle) {
        this.frameCountIdle = frameCountIdle;
    }
    public void setFrameCDIdle(int frameCDIdle) {
        this.frameCDIdle = frameCDIdle;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void fishCreatedByFishBucket(FishManager fishManager){
        if(x > 0 && y > 0){
            frameCountFish++;
            if(frameCountFish == frameCountFishLimit){
                fishManager.fishCreatedByFishBucket(this);
                frameCountFishLimit = random.nextInt(600)+900;
                frameCountFish = 0;
            }
        }
    }


    public void updateFrameCountAttack(){
        frameCDAttack++;
        if (frameCDAttack == 20 && frameCountAttack !=2) {
            frameCDAttack = 0;
            frameCountAttack++ ;
        }
        else if (frameCDAttack == 20 && frameCountAttack ==2){
            frameCountAttack = 0;
            frameCDAttack = 0;
        }
    }

    public void updateFrameCountStinkyPate(Meow meow){
        if(meow.getID() == 2) {
            if (meow.getHealthPoint() <= 1000) {
                frameCountStinkyPate = 0;
            }
            if (meow.getHealthPoint() <= 750) {
                frameCountStinkyPate = 1;
            }
            if (meow.getHealthPoint() <= 500) {
                frameCountStinkyPate = 2;
            }
            if (meow.getHealthPoint() <= 250) {
                frameCountStinkyPate = 3;
            }
        }
    }

    public boolean isDangered() {
        return isDangered;
    }
    public void setDangered(boolean dangered) {
        isDangered = dangered;
    }

    @Override
    public int getID() {
        return meowId;
    }


    public Meow(int meowHP, int meowId, int ATK,int x, int y,int width, int height , int price , boolean isAbleToFreeze){
        this.healthPoint = meowHP;
        this.meowId = meowId;
        this.meowATK = ATK;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.price = price;
        this.isAbleToFreeze = isAbleToFreeze;
    }
    public Meow(){}

    public String getMeowName() {
        return meowName;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }


    private int tileHold;
    private int x, y;
    private int width, height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setTileHold(int tileHold) {
        this.tileHold = tileHold;
    }

    public int getTileHold() {
        return tileHold;
    }



    public void removeMeow(Meow meow, Iterator<Meow> iterator, TileManager tileManager, MeowManager meowManager){
        if(meow.getHealthPoint() <= 0){
            for(Tile tile:tileManager.getTiles()){
                Rectangle r = tile.getBound();
                if(r.contains(meow.getX()+1,meow.getY()+1)){
                    tile.setOccupied(false);
                    tile.setPlaced(false);
                }
            }
            alive = false;
        }
    }
}