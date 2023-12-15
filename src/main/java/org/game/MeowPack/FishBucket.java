package org.game.MeowPack;

public class FishBucket extends Meow{
    private int fishReleasedSpeed;

    public FishBucket(int meowId, String meowName, float x, float y, int width, int height, int healthPoint, int price, int fishReleasedSpeed) {
        super(meowId, meowName, x, y, width, height, healthPoint, price);
        this.fishReleasedSpeed = fishReleasedSpeed;
    }



    public FishBucket() {
    }



    private void generateFish() {

    }

    @Override
    public void render() {

    }

    @Override
    public void update() {

    }
}
