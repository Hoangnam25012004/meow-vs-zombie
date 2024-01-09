package org.game.MeowPack;

import org.game.Component.Tile;
import org.game.Hitbox.Rect;
import org.game.Manager.*;
import org.game.bullet.Shooter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;

public class Meow implements Shooter
{
    private int meowId;
    // 1 - ShooterMeow, 2 - SnowMeow, 3 - FishBucket, 4 - BombPate, 5 - StinkyPate
    private String meowName;
    private Rect rect;
    private int healthPoint;
    private int meowATK;
    private int price;
    private boolean alive = false;

    private int frameCountIdleLimit;
    private int frameCountAttackLimit;
    private int frameCountIdle = 0;
    private int frameCountAttack = 0;
    private int frameCountFish = 0;
    private int frameCountFishLimit = 600;
    private int frameCDIdle = 0;
    private int frameCDAttack = 0;
    private Random random = new Random();
    private int explodeCD;
    private boolean isDangered = false;

    public int getATK() {
        return meowATK;
    }
    public int getFrameCountAttackLimit() {
        return frameCountAttackLimit;
    }
    public int getFrameCountIdle() {
        return frameCountIdle;
    }
    public int getFrameCountAttack() {
        return frameCountAttack;
    }
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

    public void updateFrameCountIdle(){
        frameCDIdle++;
        if(frameCDIdle%3 == 0){
            frameCountIdle++;
            if(frameCountIdle == frameCountIdleLimit){
                frameCountIdle = 0;
            }
        }
    }

    public void updateFrameCountAttack(){
        frameCDAttack++;
        if(frameCDAttack%4 == 0){
            frameCountAttack++;
            if (frameCountAttack == frameCountAttackLimit) {
                frameCountAttack = 0;
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


    public Meow(int meowHP, int meowId, int ATK, int frameCountIdleLimit,int frameCountAttackLimit,int x, int y,int width, int height){
        this.frameCountIdleLimit = frameCountIdleLimit;
        this.frameCountAttackLimit = frameCountAttackLimit;
        this.healthPoint = meowHP;
        this.meowId = meowId;
        this.meowATK = ATK;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public String getMeowName() {
        return meowName;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public int getPrice() {
        return price;
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


//    public void getMeowImage(){
//        try {
//            meow_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/meow_1.png")));
//            meow_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/meow_2.png")));
//            meow_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/meow_3.png")));
//            meow_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/Icecat.png")));
//            meow_5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/Icecat.png")));
//            meow_6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/Icecat.png")));
//            bucket = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/bucket.png")));
//            can_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/can_1.png")));
//            can_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/can_2.png")));
//            can_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/can_3.png")));
//            can_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/can_4.png")));
//            cattray1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/Cattray1.png")));
//            cattray2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/Cattray2.png")));
//            cattray3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/Cattray3.png")));
//            cattray4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/Cattray4.png")));
//        } catch (IOException e){e.printStackTrace();}
//    }


    public void removeMeow(Meow meow, Iterator<Meow> iterator, TileManager tileManager, MeowManager meowManager){
        if(meow.getHealthPoint() <= 0){
            for(Tile tile:tileManager.getTiles()){
                Rectangle r = tile.getBound();
                if(r.contains(meow.getX()+1,meow.getY()+1)){
                    tile.setOccupied(false);
                    tile.setPlanted(false);
                }
            }
            alive = false;
        }
    }
}