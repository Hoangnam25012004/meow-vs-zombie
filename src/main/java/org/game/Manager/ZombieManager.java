package org.game.Manager;

import org.game.MeowPack.Shooter;
import org.game.Zombie.Zombie;
import org.game.bullet.Bullet;
import org.game.Scenes.Playing;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class ZombieManager {

    protected int HP;
    protected double speed;
    protected int attackPower;
    private int originalX;
    private int originalY;
    private Playing playing;
    private ArrayList<Zombie> zombieList;

    public ArrayList<Zombie> getZombies() {
        return zombieList;
    }

    BulletManager bulletManager;

    public BufferedImage zom_1,zom_2,zom_3;
    private static ZombieManager instance = null;


    private ZombieManager(Playing playing) {
        this.playing = playing;
        zombieList = new ArrayList<>();
        getNormalZombie();
        getHelmetZombie();
        getCatEarZombie();
    }
    public static ZombieManager createZombieManager(Playing playing) {
        if(instance == null) {
            instance = new ZombieManager(playing);
        } else {
            System.out.println("Cannot create another ZombieManager");
        }
        return instance;
    }



//--------------------------------------------------------------------------
// Actions of the zombies

    private void move(double speed) {
        for (Zombie z : zombieList){
            //z.X() = z.X() - speed; đang fix
        }
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
                return new Zombie(originalX,originalY,0); // đang để tạm thg originX,Y
            case 1:
                return new Zombie(originalX,originalY,1);
            case 2:
                return new Zombie(originalX,originalY,2);
            default:
                return null; // never happends
        }
    }

//------------------------------------------------------------------------------
// spawn random zombies in random row order

    public void spawnRandomZombiesIn5RandomRows(int totalRows) {
        Random random = new Random();
        int[] randomRows = new int[5]; // Store 5 unique random row indices
        // Select 5 unique random rows directly
        for (int i = 0; i < 5; i++) {
            int row;
            do {
                row = random.nextInt(totalRows);
                System.out.println("is loop");
            } while (contains(randomRows, row));
            randomRows[i] = row;
        }

        // Spawn zombies in the chosen rows
        for (int row : randomRows) {
            int numZombies = random.nextInt(3); // Randomly spawn 1-2 zombies
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



// -------------------------------------------------------------------------------
// check collision and hit box

    public boolean isColliding(Bullet bullet, Zombie zombie) {
        Rectangle bulletRectangle = bulletManager.getBoundary(bullet);
        Rectangle zombieRectangle = zombie.getBound();

        return bulletRectangle.intersects(zombieRectangle);
    }


    public void checkBulletCollisions() {
        ArrayList<Bullet> bullets = bulletManager.bulletList;
        ArrayList<Zombie> zombies = zombieList;

        for (Bullet bullet : bullets) {
            for (Zombie zombie : zombies) {
                if (isColliding(bullet, zombie)) {
                    takeDamage(bullet.getDame());
                    bullets.remove(bullet);
                    break;
                }
            }
        }
    }



//----------------------------------------------------------------------------
// graphics



    public void update(Shooter shooter){
        move(2);
        //spawnRandomZombiesIn5RandomRows(5);

    }

    public void getZomImage(){
        try {
            zom_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombieRes/zom_1.png")));
            zom_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombieRes/zom_2.png")));
            zom_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombieRes/zom_3.png")));
        } catch (IOException e){e.printStackTrace();}
    }

    public void getNormalZombie(){

    }
    public void getCatEarZombie(){

    }
    public void getHelmetZombie(){

    }
    public void render(Graphics2D g2) {

    }
}