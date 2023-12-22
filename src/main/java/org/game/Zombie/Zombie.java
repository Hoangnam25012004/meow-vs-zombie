package org.game.Zombie;

import org.game.Collision.Collision;
import org.game.MeowPack.Shooter;
import org.game.bullet.Bullet;
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
        private int x;
        private int y;
        private int originalX;
        private int originalY;
        private ArrayList<Zombie> zombieList = new ArrayList<>();
    
        public Graphical graphical;

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
            getZomImage();}
        
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

        public void takeDamage(int damageAmount) {
            HP -= damageAmount; // Reduce health by the bullet's damage
        
            if (HP <= 0) {
                // Death(); // Call a method to handle zombie death
            } 
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
                    return new catEarZombie(150, 0.5, 3);
                case 2: 
                    return new helmetZombie(200, 0.5, 3);
                default:
                    return null; // never happends
            }
        }

        public void spawnRandomZombiesIn5RandomRows(int totalRows) {
            Random random = new Random();
            int[] randomRows = new int[5]; // Store 5 unique random row indices
        
            // Select 5 unique random rows directly
            for (int i = 0; i < 5; i++) {
                int row;
                do {
                    row = random.nextInt(totalRows);
                } while (contains(randomRows, row)); 
                randomRows[i] = row;
            }
        
            // Spawn zombies in the chosen rows
            for (int row : randomRows) {
                int numZombies = random.nextInt(2) + random.nextInt(2); // Randomly spawn 1-2 zombies
                for (int i = 0; i < numZombies; i++) {
                    Zombie zombie = createRandomZombie();
                    // Add zombie to zombieList
                    if(zombie!=null){
                        zombieList.add(zombie);
                    }
                }
            }
        }
        
        private boolean contains(int[] array, int value) {
            for (int element : array) {
                if (element == value) {
                    return true;
                }
            }
            return false;
        }


        /*public void spawnZombies(){
             Random random = new Random();
            // clear the existing zombie list
            zombieList.clear();
            // create random number of zombie for every row
            for (int row = 0; row < 5; row ++){
                int zombieCount = random.nextInt(2) + random.nextInt(2);
                for( int i = 0; i < zombieCount; i++){
                    Zombie zombie = createRandomZombie();
                    zombieList.add(zombie);
                }
            }
        }
        */


        public void zom_update(Shooter shooter){
            move(4);
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

        public void getZomImage(){
        try {
            zom_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombieRes/zom_1.png")));
            zom_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombieRes/zom_2.png")));
            zom_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombieRes/zom_3.png")));
        } catch (IOException e){e.printStackTrace();}
    }
        public void render(Graphics2D g2) {
        }



        // private void checkHealth() {
        //     if (HP <= 0) {
        //         getWorld().removeObject(this);
        //     }
        // }

// -------------------------------------------------------------------------------
// check collision and hit box

    public Rectangle getBoundary(){
        return new Rectangle((int) this.getX(), this.getY(), 14 , 22);
    }


    public boolean isColliding(Bullet bullet, Zombie zombie) {
        //Rectangle bulletRectangle = bullet.getBoundary();
        Rectangle zombieRectangle = zombie.getBoundary();

        return bulletRectangle.intersects(zombieRectangle);
    }

    
    public void checkBulletCollisions() {
        //ArrayList<Bullet> bullets = bulletList; // My neeeds to create bullet list for bullet collision dectection
        ArrayList<Zombie> zombies = zombieList;
    
        for (Bullet bullet : bullets) {
            for (Zombie zombie : zombies) {
                if (isColliding(bullet, zombie)) {
                    zombie.takeDamage(bullet.getDame());
                    bullets.remove(bullet);
                    break;
                }
            }
        }
    }


}
