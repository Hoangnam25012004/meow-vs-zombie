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
    private int meowX,meowY;

    public Shooter(int meowId, String meowName, double x, double y, int width, int height, int healthPoint, int price) {
        super(meowId, meowName, x, y, width, height, healthPoint, price);
    }
    public Shooter(){
        setInitial();
    };
    public Shooter(Graphical graphical) {
        this.graphical = graphical;
        getMeowShooterImage();
        setInitial();
    }

    public void getMeowShooterImage(){
        try {
            meow_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/meow_shooter.png")));
        } catch (IOException e){e.printStackTrace();}
    }

    public void shoot() {

    }
    private void setInitial(){
        this.meowX = 140;
        this.meowY = 110;
    }

    @Override
    public void render(Graphics2D g2) {
        BufferedImage image = meow_1;


        g2.drawImage(image,meowX,meowY,graphical.getmeowWidth(),graphical.getmeowHeight(),null);





    }

    @Override
    public void update() {

    }
}
