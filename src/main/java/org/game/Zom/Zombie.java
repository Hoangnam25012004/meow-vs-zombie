package org.game.Zom;

import org.game.Collision.Collision;
import org.game.MeowPack.Shooter;
import org.game.graphic.Graphical;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Zombie {
    
        protected int HP;
        protected double speed;
        protected int attackPower;
        private double x;
        private int y;
        private int originalX;
        private int originalY;
        private ArrayList<Zombie> zombieList = new ArrayList<>();
    
        private Graphical graphical;

        public BufferedImage zom_1,zom_2,zom_3;

        public Zombie(int HP, double speed2, int attackPower) {
            this.HP = HP;
            this.speed = speed2;
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

        public Zombie createRandomZombie(){
        Random random = new Random();
        int zombieType = random.nextInt(3);
            switch (zombieType) {
                case 0:
                    return new normalZombie(100, 0.5, 3);
                case 1: 
                    return new helmetZombie(150, 0.5, 3);
                case 2: 
                    return new helmetZombie(200, 0.5, 3);
                default:
                    return null; // never happends
            }
        }











       /* public void setCollide(boolean c){
            this.collide = c;
        }*/

        public void zom_update(Shooter shooter){
            move(0.5);
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
            zom_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombieRes/zom_2.png")));
            zom_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombieRes/zom_3.png")));
        } catch (IOException e){e.printStackTrace();}
    }
        public void render1(Graphics2D g2) {
            g2.drawImage(zom_1, (int) this.x, this.y,graphical.getZomWidth(), graphical.getZomHeight(), null);
        }

        public void render2(Graphics2D g2){
            g2.drawImage(zom_2, (int) this.x, this.y,graphical.getZomWidth(), graphical.getZomHeight(), null);

        }
        public void render3(Graphics2D g2){
            g2.drawImage(zom_3, (int) this.x, this.y,graphical.getZomWidth(), graphical.getZomHeight(), null);
        }


        // private void checkHealth() {
        //     if (HP <= 0) {
        //         getWorld().removeObject(this);
        //     }
        // }


    
    }
