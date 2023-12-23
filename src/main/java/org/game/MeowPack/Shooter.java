package org.game.MeowPack;

import org.game.graphic.Graphical;
import org.game.bullet.Bullet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Shooter extends Meow {

    private BufferedImage meow_1, meow_2, meow_3;
    private Graphical graphical;
    private Bullet[] bullets;
    private boolean isAbleToFreeze;
    // true - SnowMeow, false - ShooterMeow
  //  private boolean collide = false;
    private int meowX,meowY;
    private int counter = 0, counterNum = 1;

    public Shooter(int meowId, String meowName, int x, int y, int width, int height, int healthPoint, int price) {
        super(meowId, meowName, x, y, width, height, healthPoint, price);
    }
    public Shooter(int x,int y){
        setInitial(x,y);
    }
    public Shooter(Graphical graphical,int x,int y) {
        this.graphical = graphical;
        getMeowShooterImage();
        setInitial(x,y);
    }

    public void getMeowShooterImage(){
        try {
            meow_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/meow_1.png")));
            meow_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/meow_2.png")));
            meow_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/meowRes/meow_3.png")));
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
        if (counterNum == 1) {
            g2.drawImage(meow_1,meowX,meowY,graphical.getmeowWidth(),graphical.getmeowHeight(),null);}
        else if (counterNum == 2){
            g2.drawImage(meow_2,meowX,meowY,graphical.getmeowWidth(),graphical.getmeowHeight(),null);
            } else if (counterNum == 3) {
                g2.drawImage(meow_3,meowX,meowY,graphical.getmeowWidth(),graphical.getmeowHeight(),null);}
    }

   @Override
    public void update() {
    }

    public void meow_update(){
        counter++;
        if (counter >20) {
            if ( counterNum == 1){
                counterNum = 2;
            } else if (counterNum == 2){
                counterNum = 3;
            }else {counterNum =1;}
            counter =0;
        }

    }
}
