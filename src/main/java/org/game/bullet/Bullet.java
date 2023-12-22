package org.game.bullet;

import org.game.MeowPack.Shooter;
import org.game.Zombie.Zombie;
import org.game.Zombie.Zombie;
import org.game.graphic.Graphical;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class Bullet {
    private int  y;
    private double x;
    private int Dame;
    private boolean isFrozen = false;
    private Graphical graphical;
    private double speed;
    private int originalX;
    private int originalY;
    public BufferedImage bullet_1;
    private ArrayList<Bullet> bulletLists = new ArrayList<>();


    Zombie zombie;
 
    public int getDame() { 
        return Dame; 
    }

    public Bullet(double x, int y, int Dame){
        this.x = x;
        this.y = y;
        this.Dame = Dame;
    }
    public boolean getisFrozen() {
        return isFrozen;
    }
    public void setisFrozen(boolean isFrozen) {
        this.isFrozen = isFrozen;
    }

    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }

    public int getY () {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    
    public void move () {
        if (this.x<1024) { 
            this.x += 8;
        }
    }
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Bullet(Graphical graphical, int x, int y){
        this.graphical = graphical;
        this.originalX = x;
        this.originalY = y;
        setPosition(x,y);
        getBulletImage();}

    public void getBulletImage() {
        try {
            bullet_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bullet/wool.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void render(Graphics2D g2) {
        g2.drawImage(bullet_1, (int) this.x, this.y,graphical.getwoolWidth(), graphical.getwoolHeight(), null);
    }

    private void setLocation(double i, int y2) {
        this.x = i;
        this.y = y2;
    }
    private void move(double speed) {

        setLocation(this.x + speed, this.y);
    }

    public void bullet_update(Zombie zombie) {
        move(5);
        if (this.x >= graphical.getScreenWidth()) {
            this.x = originalX;
            this.y = originalY;
        }
        if (this.x < (zombie.getX()+graphical.getZomWidth()) & this.x > (zombie.getX() - graphical.getZomWidth())){
            this.x = originalX;
            this.y = originalY;
        }
    }
    public Rectangle getBoundary (){
        return new Rectangle((int) this.getX(), this.getY(), 5*graphical.scale, 5*graphical.scale) ;}
}
