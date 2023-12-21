package org.game.Zom;

import org.game.Collision.Collision;
import org.game.MeowPack.Shooter;
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
        private int originalX;
        private int originalY;
    //    public boolean collide = false;
        private Graphical graphical;
        private Collision collision;
        private Shooter shooter;

        public BufferedImage zom_1,zom_2;

        public Zombie(int HP, int speed, int attackPower) {
            this.HP = HP;
            this.speed = speed;
            this.attackPower = attackPower; 
        }
        public Zombie(Graphical graphical,int x, int y){
            this.graphical = graphical;
            this.originalX = x;
            this.originalY = y;
            setPosition(x,y);
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

       /* public void setCollide(boolean c){
            this.collide = c;
        }*/

        public void zom_update(Shooter shooter){
            move(/*0.3*/ 1);
            if (this.x == 0){
                this.x = originalX;
                this.y = originalY;
            }

           if (this.getX() < (shooter.getMeowX() + (graphical.getmeowWidth() / 2)) &
                    this.getX() > (shooter.getMeowX() - (graphical.getmeowWidth() / 2))){
                this.x = originalX;
                this.y = originalY;
           }

        }

        public void getZom1Image(){
        try {
            zom_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombieRes/zom_1.png")));
            zom_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombieRes/zom_1.png")));
        } catch (IOException e){e.printStackTrace();}
    }
        public void render(Graphics2D g2) {
            BufferedImage image1 = zom_1;
            BufferedImage image2 = zom_2;
            g2.drawImage(image1, (int) this.x, this.y,graphical.getZomWidth(), graphical.getZomHeight(), null);
            g2.drawImage(image2, (int) this.x, this.y,graphical.getZomWidth(), graphical.getZomHeight(), null);

        }


        // private void checkHealth() {
        //     if (HP <= 0) {
        //         getWorld().removeObject(this);
        //     }
        // }


    
    }
