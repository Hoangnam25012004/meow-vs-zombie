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
  //  private boolean collide = false;
    private int meowX,meowY;

    public Shooter(int meowId, String meowName, double x, double y, int width, int height, int healthPoint, int price) {
        super(meowId, meowName, x, y, width, height, healthPoint, price);
    }
    public Shooter(int x,int y){
        setInitial(x,y);
    };
    public Shooter(Graphical graphical,int x,int y) {
        this.graphical = graphical;
        getMeowShooterImage();
        setInitial(x,y);
    }

    public void getMeowShooterImage(){
        try {
            meow_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/meow_shooter.png")));
        } catch (IOException e){e.printStackTrace();}
    }

    public void shoot() {

    }
    private void setInitial(int x, int y){
        this.meowX = x;
        this.meowY = y;
    }
    public int getMeowX(){
        return this.meowX;
    }
    public int getMeowY(){
        return this.meowY;
    }
    /*public void setCollide(boolean c){
        this.collide = c;
    }*/

    @Override
    public void render(Graphics2D g2) {
        BufferedImage image = meow_1;


        g2.drawImage(image,meowX,meowY,graphical.getmeowWidth(),graphical.getmeowHeight(),null);





    }

    @Override
    public void update() {

    }
}
