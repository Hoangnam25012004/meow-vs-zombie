package org.game.MeowPack;

import java.awt.*;

public class FishBucket extends Meow{
    private int fishReleasedSpeed;

    public FishBucket(int meowId, String meowName, int x, int y, int width, int height, int healthPoint, int price, int fishReleasedSpeed) {
        super(meowId, meowName, x, y, width, height, healthPoint, price);
        this.fishReleasedSpeed = fishReleasedSpeed;
    }



    public FishBucket(){
    }



    private void generateFish() {

    }

    @Override
    public void render(Graphics2D g2) {

    }

    @Override
    public void update() {

    }
}
