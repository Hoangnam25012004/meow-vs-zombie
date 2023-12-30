package org.game.Zombie;

import org.game.MeowPack.Shooter;
import org.game.bullet.Bullet;
import org.game.Manager.BulletManager;
import org.game.graphic.Graphical;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class Zombie {
    
        protected int HP;
        protected double speed;
        protected int attackPower;
        private double x;
        private int y;
        private int originalX;
        private int originalY;
        private ArrayList<Zombie> zombieList = new ArrayList<>();
    
        public Graphical graphical;

        BulletManager bulletManager;

        public BufferedImage zom_1,zom_2,zom_3;


        public Zombie(Graphical graphical,int HP, double speed, int attackPower) {
            this.graphical = graphical;
            this.HP = HP;
            this.speed = speed;
            this.attackPower = attackPower; 
        }
        
        public double getX(){
            return x;
        }
        public int getY(){
            return y;
        }
        public void setPosition(double x, int y){
            this.x = x;
            this.y = y;
        }

//--------------------------------------------------------------------------
// Actions of the zombies

        private void move(double speed) {
            setLocation(this.x - speed, this.y);
        }

        public void takeDamage(int damageAmount) {
            HP -= damageAmount; // Reduce health by the bullet's damage
           
             if (HP <= 0) {
                // Death(); // Call a method to handle zombie death, remove zombies
            } 
        }
    
        
//----------------------------------------------------------------------------
// spawn random type of zombies 
// 0. Normal zombie 1. Zombie with cat ear 2. Zombie wearing helmet

        public Zombie createRandomZombie(){
        Random random = new Random();
        int zombieType = random.nextInt(3);
            switch (zombieType) {
                case 0:
                    return new normalZombie(graphical,100 , 0.5, 3);
                case 1: 
                    return new catEarZombie(graphical ,150, 0.5, 3);
                case 2: 
                    return new helmetZombie(graphical,200, 0.5, 3);
                default:
                    return null; // never happends
            }
        }

//------------------------------------------------------------------------------
// spawn random zombies in random row order

            public void spawnRandomZombiesIn5RandomRows(int totalRows) {
            Random random = new Random();
            int[] randomRows = new int[5]; 

            // track generated rows
            Set<Integer> generatedRows = new HashSet<>();

            // Generate 5 unique random rows
            for (int i = 0; i < 5; i++) {
                int row;
                do {
                    row = (random.nextInt(totalRows)+1);
                } while (contains(randomRows, row)); 

                randomRows[i] = row;
                System.out.println("ROW: " + row);
                generatedRows.add(row); // Add the row to the set for future checks
            }

        
            // Spawn zombies in the chosen rows
            for (int row : randomRows) {
                int numZombies = random.nextInt(3); // Randomly spawn 1-2 zombies
                for (int i = 0; i < numZombies; i++) {
                    System.out.println(numZombies);
                    Zombie zombie = createRandomZombie();
                    // Add zombie to zombieList
                    if(zombie!=null){
                        zombieList.add(zombie);
                        System.out.println(zombieList.size());
                    }

                }
                zombieList.clear();
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


// -------------------------------------------------------------------------------
// check collision and hit box

    public Rectangle getBoundary(){
        return new Rectangle((int) this.getX(), this.getY(), 14* graphical.scale , 22 * graphical.scale);
    }


    public boolean isColliding(Bullet bullet, Zombie zombie) {
        Rectangle bulletRectangle = bulletManager.getBoundary();
        Rectangle zombieRectangle = zombie.getBoundary();

        return bulletRectangle.intersects(zombieRectangle);
    }


    public void checkBulletCollisions() {
        ArrayList<Bullet> bullets = bulletManager.bulletList; // "My" neeeds to create bullet list for bullet collision dectection
        ArrayList<Zombie> zombies = zombieList;

        for (Bullet bullet : bullets) {
            for (Zombie zombie : zombies) {
                if (isColliding(bullet, zombie)) {
                    zombie.takeDamage(bullet.getDame());
                    //bullets.remove(bullet);
                    break;
                }
            }
        }
    }



//----------------------------------------------------------------------------
// graphics

 public Zombie(Graphical graphical,int x, int y){
            this.graphical = graphical;
            this.originalX = x;
            this.originalY = y;
            setPosition(x,y);
            getZomImage();
        }

        private void setLocation(double x , int y) {
            this.x = x;
            this.y = y;
        }

        
        public void update(Shooter shooter){
            move(2);

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
}