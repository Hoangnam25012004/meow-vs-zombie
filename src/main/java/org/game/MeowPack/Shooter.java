package org.game.MeowPack;

import org.game.graphic.Graphical;
import org.game.bullet.Bullet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Shooter extends Meow {

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
        getMeowImage();
        setInitial(x,y);
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
        if (isAbleToFreeze == false) {
            if (counterNum == 1) {
                g2.drawImage(meow_1, meowX, meowY, graphical.getmeowWidth(), graphical.getmeowHeight(), null);
            } else if (counterNum == 2) {
                g2.drawImage(meow_2, meowX, meowY, graphical.getmeowWidth(), graphical.getmeowHeight(), null);
            } else if (counterNum == 3) {
                g2.drawImage(meow_3, meowX, meowY, graphical.getmeowWidth(), graphical.getmeowHeight(), null);
            }
        }
        else {
            if (counterNum == 1) {
                g2.drawImage(meow_4, meowX, meowY, graphical.getmeowWidth(), graphical.getmeowHeight(), null);
            } else if (counterNum == 2) {
                g2.drawImage(meow_5, meowX, meowY, graphical.getmeowWidth(), graphical.getmeowHeight(), null);
            } else if (counterNum == 3) {
                g2.drawImage(meow_6, meowX, meowY, graphical.getmeowWidth(), graphical.getmeowHeight(), null);
            }

        }
    }

   @Override
    public void update() {
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
