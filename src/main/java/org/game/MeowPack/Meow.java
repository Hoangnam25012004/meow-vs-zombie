package org.game.MeowPack;

public abstract class Meow
{
    private int meowId;
    // 1 - ShooterMeow, 2 - SnowMeow, 3 - FishBucket, 4 - BombPate, 5 - StinkyPate
    private String meowName;
    private Rect rect;
    private int healthPoint;
    private int price;
    private boolean alive;

    public Meow (int meowId, String meowName, float x, float y, int width, int height, int healthPoint, int price) {
        this.meowId = meowId;
        this.meowName = meowName;
        rect = new Rect(x, y, width, height);
        this.healthPoint = healthPoint;
        this.price = price;
        alive = true;
    }
    public Meow(){}

    public abstract void render();

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
            // TODO: disappear
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
}