package org.game.MeowPack;

import org.game.graphic.Graphical;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class FishBucket extends Meow{
    private int fishReleasedSpeed;

    private Graphical graphical;
    private int meowX,meowY;

    public FishBucket(int meowId, String meowName, int x, int y, int width, int height, int healthPoint, int price, int fishReleasedSpeed) {
        super(meowId, meowName, x, y, width, height, healthPoint, price);
        this.fishReleasedSpeed = fishReleasedSpeed;
    }



    public FishBucket(){
    }

    public FishBucket(Graphical graphical, int x, int y) {
        this.graphical = graphical;
        getMeowImage();
        setInitial(x,y);
    }

    private void setInitial(int x, int y){
        this.meowX = x;
        this.meowY = y;
    }


    private void generateFish() {

    }

    @Override
    public void render(Graphics2D g2) {
        g2.drawImage(bucket, meowX, meowY, graphical.getmeowWidth(), graphical.getmeowHeight(), null);

    }

    @Override
    public void update() {

    }
}
