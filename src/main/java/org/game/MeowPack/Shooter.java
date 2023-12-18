package org.game.MeowPack;

import org.game.graphic.Graphical;
import org.game.bullet.Bullet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Shooter extends Meow {

    private BufferedImage image;
    private Graphical graphical;
    private Bullet[] bullets;
    private boolean isAbleToFreeze;
    // true - SnowMeow, false - ShooterMeow

    public Shooter(int meowId, String meowName, float x, float y, int width, int height, int healthPoint, int price) {
        super(meowId, meowName, x, y, width, height, healthPoint, price);

    }
    public Shooter(Graphical graphical) {
        this.graphical = graphical;
        getMeowShooterImage();
    }

    public void getMeowShooterImage(){
        try {
            meow_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/meow_shooter.png")));
        } catch (IOException e){e.printStackTrace();}
    }

    public void shoot() {

    }

    @Override
    public void render(Graphics2D g2) {
        BufferedImage image = meow_1;


        g2.drawImage(image,140,110,graphical.getmeowWidth(),graphical.getmeowHeight(),null);





    }

    @Override
    public void update() {

    }
}
