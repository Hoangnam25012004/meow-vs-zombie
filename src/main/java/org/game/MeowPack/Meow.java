package org.game.MeowPack;

import org.game.Hitbox.Rect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Meow
{
    private int meowId;
    // 1 - ShooterMeow, 2 - SnowMeow, 3 - FishBucket, 4 - BombPate, 5 - StinkyPate
    private String meowName;
    private Rect rect;
    private int healthPoint;
    private int price;
    private boolean alive;

    public BufferedImage meow_1, meow_2, meow_3, meow_4,meow_5,meow_6,bucket,
            can_1,can_2,can_3,can_4,cattray1,cattray2,cattray3,cattray4;

    public Meow (int meowId, String meowName, int x, int y, int width, int height, int healthPoint, int price) {
        this.meowId = meowId;
        this.meowName = meowName;
        rect = new Rect(x, y, width, height);
        this.healthPoint = healthPoint;
        this.price = price;
        alive = true;
    }
    public Meow(){}
    public Meow(int x, int y, int width, int height){
        rect = new Rect(x,y,width,height);
    }


    public abstract void render(Graphics2D g2);

    public abstract void update();

    public int getMeowId() {
        return meowId;
    }

    public String getMeowName() {
        return meowName;
    }

    public Rect getRect() {
        return rect;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
        if (healthPoint <= 0) {
            // TODO: disappear or something
        }
    }

    public int getPrice() {
        return price;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getX(){
        return rect.getX();
    }

    public int getY(){
        return rect.getY();
    }




    public void getMeowImage(){
        try {
            meow_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/meow_1.png")));
            meow_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/meow_2.png")));
            meow_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/meow_3.png")));
            meow_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/Icecat.png")));
            meow_5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/Icecat.png")));
            meow_6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/Icecat.png")));
            bucket = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/bucket.png")));
            can_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/can_1.png")));
            can_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/can_2.png")));
            can_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/can_3.png")));
            can_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/can_4.png")));
            cattray1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/Cattray1.png")));
            cattray2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/Cattray2.png")));
            cattray3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/Cattray3.png")));
            cattray4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/Cattray4.png")));
        } catch (IOException e){e.printStackTrace();}
    }
}