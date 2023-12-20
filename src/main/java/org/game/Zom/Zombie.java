package org.game.Zom;

import org.game.graphic.Graphical;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Zombie {
    
        private int HP;
        private double speed;
        private int attackPower;
        private double x;
        private int y;
        public boolean collide = false;
        private Graphical graphical;

        public BufferedImage zom_1;

        public Zombie(int HP, int speed, int attackPower) {
            this.HP = HP;
            this.speed = speed;
            this.attackPower = attackPower; 
        }
        public Zombie(Graphical graphical){
            this.graphical = graphical;
            setPosition(540,90);
            getZom1Image();}
        
        public double getX(){
            return x;
        }

        public int getY(){
            return y;
        }

        public void setPosition(int x, int y){
            this.x = x;
            this.y = y;
        }
    
        public void act() {
            move(speed);
            //turnTowardsPlayer();
            //attackPlayer();
            //checkHealth();
        }
    
        private void move(double speed) {

            setLocation(this.x - speed, this.y);
        }
    
        

        private void setLocation(double i, int y2) {
            this.x = i;
            this.y = y2;
        }

        public void zom_update(){
            move(0.3);
            if (this.x == 0){
                this.x = 540;
                this.y = 90;
            }
            if (this.x<= 192 & this.x >= 52){ this.x = 540;
            this.y = 90;}

        }

        public void getZom1Image(){
        try {
            zom_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombieRes/zom_1.png")));
        } catch (IOException e){e.printStackTrace();}
    }
        public void render(Graphics2D g2) {
            BufferedImage image = zom_1;
            g2.drawImage(image, (int) this.x, this.y,graphical.getZomWidth(), graphical.getZomHeight(), null);
    }


        // private void checkHealth() {
        //     if (HP <= 0) {
        //         getWorld().removeObject(this);
        //     }
        // }


    
    }
