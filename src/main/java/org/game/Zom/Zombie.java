package org.game.Zom;

import org.game.graphic.Graphical;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Zombie {
    
        private int HP;
        private int speed;
        private int attackPower;
        private int x;
        private int y;
        private BufferedImage image;
        private Graphical graphical;

        public BufferedImage zom_1;

        public Zombie(int HP, int speed, int attackPower) {
            this.HP = HP;
            this.speed = speed;
            this.attackPower = attackPower; 
        }

        public Zombie(Graphical graphical){
            this.graphical = graphical;
            getZom1Image();}
        
        public int getX(){
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
    
        private void move(int speed) {
            setLocation(getX() - speed, getY());
        }
    
        

        private void setLocation(int i, int y2) {
        }

        public void getZom1Image(){
        try {
            zom_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombieRes/zom_1.png")));
        } catch (IOException e){e.printStackTrace();}
    }
        public void render(Graphics2D g2) {
            BufferedImage image = zom_1;
            g2.drawImage(image,540,100,graphical.tileSize,graphical.tileSize,null);
    }


        // private void checkHealth() {
        //     if (HP <= 0) {
        //         getWorld().removeObject(this);
        //     }
        // }


    
    }
