package org.game.MeowPack;

import org.game.bullet.Bullet;

import java.awt.image.BufferedImage;

public class Shooter extends Meow {

    private BufferedImage image;
    private Bullet[] bullets;
    private boolean isAbleToFreeze;
    // true - SnowMeow, false - ShooterMeow

    public Shooter(int meowId, String meowName, float x, float y, int width, int height, int healthPoint, int price) {
        super(meowId, meowName, x, y, width, height, healthPoint, price);

    }

    public void shoot() {

    }

    @Override
    public void render() {

    }

    @Override
    public void update() {

    }
}
