package org.game.Manager;

import org.game.MeowPack.Shooter;
import org.game.Zombie.Zombie;
import org.game.bullet.Bullet;
import org.game.Scenes.Playing;
import org.game.Component.Tile;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class ZombieManager {

    protected int HP;
    private int totalZom = 7;
    private static boolean zReachedEnd = false;
    private Playing playing;
    private Zombie zombie;

    private ArrayList<Zombie> zombieList;

    public ArrayList<Zombie> getZombies() {
        return zombieList;
    }

    BulletManager bulletManager;
    public BufferedImage[] normalZombieMove = new BufferedImage[3];
    public BufferedImage[] normalZombieEat = new BufferedImage[2];
    public BufferedImage[] CatEarZombieMove = new BufferedImage[3];
    public BufferedImage[] CatEarZombieEat = new BufferedImage[2];
    public BufferedImage[] HelmetZombieMove = new BufferedImage[3];
    public BufferedImage[] HelmetZombieEat = new BufferedImage[2];
    private static ZombieManager instance = null;
    private static int realTimeCounter = 0;


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


    public void takeDamage(int damageAmount) {
        HP -= damageAmount; // Reduce health by the bullet's damage

        if (HP <= 0) {
            // Death(); // Call a method to handle zombie death, remove zombies
        }
    }


//----------------------------------------------------------------------------
// spawn random type of zombies
// 0. Normal zombie 1. Zombie with cat ear 2. Zombie wearing helmet


//------------------------------------------------------------------------------
// spawn random zombies in random row order

    public int getTotalZom(){
        return totalZom;
    }
    public void spawnRandomZombiesIn5RandomRows(int totalRows,int numberZom) {
        Random random = new Random();
        int[] randomRows = new int[5]; // Store 5 unique random row indices
        // Select 5 unique random rows directly
        for (int i=0 ; i<5; i++){
            int row;
            do{
                row = random.nextInt(totalRows)+1;
            }
            while (contains(randomRows,row));
            randomRows[i] = row;
        }

        // Spawn zombies in the chosen rows
        synchronized (zombieList) {
            for (int row : randomRows) {
                int numZombies = random.nextInt(numberZom); // Randomly spawn 1-2 zombies
                totalZom -= numZombies;
                if (totalZom <0 || totalZom < numZombies){
                    break;}
                for (int i = 0; i < numZombies; i++) {
                    int zombieType = random.nextInt(3);
                    switch (zombieType) {
                        case 0:
                            zombieList.add(new Zombie((double)1070 + random.nextInt(100), (double) 86 * row - 27, 0)); // normal 102 is the starting y
                        case 1:
                            zombieList.add(new Zombie((double) 1070 + random.nextInt(100), (double) 86 * row - 27, 1)); // catear
                        case 2:
                            zombieList.add(new Zombie((double) 1070 + random.nextInt(100), (double) 86 * row - 27, 2)); // helmet
                    }

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
    public boolean allZombieDead() {
        for (Zombie z : zombieList) {
            if (z.isAlived()) {
                return false;
            }
        }
        return true;
    }
    public void createHorde(int count) {
        for (int i = 0; i < count; i++) {
            spawnRandomZombiesIn5RandomRows(5,count);
        }
    }
    public static boolean iszReachedEnd() {
        return zReachedEnd;
    }



//----------------------------------------------------------------------------
// graphics


    public void getNormalZombie(){
        try {
            for (int i = 1; i <= normalZombieMove.length; i++) { //move normal zome
                normalZombieMove[i-1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombieRes/normalZom/move/zommove_" + i + ".png")));
            }
            for (int i = 1; i <= normalZombieEat.length; i++) {
                normalZombieEat[i-1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombieRes/normalZom/eat/zomeat_"+i+".png")));
            }
        } catch (Exception e){e.printStackTrace();}
    }

    public void getCatEarZombie(){
        try {
            for (int i = 1; i <= CatEarZombieMove.length; i++) { //move normal zome
                CatEarZombieMove[i-1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombieRes/CatEarZom/move/zomcatear_"+i+".png")));
            }
            for (int i = 1; i <= CatEarZombieEat.length; i++) {
                CatEarZombieEat[i-1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombieRes/CatEarZom/eat/zomcateareat_"+i+".png")));
            }
        } catch (Exception e){e.printStackTrace();}

    }
    public void getHelmetZombie(){
        try {
            for (int i = 1; i <= HelmetZombieMove.length; i++) { //move normal zome
                HelmetZombieMove[i-1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombieRes/HelmetZom/move/zomhelmet_"+i+".png")));
            }
            for (int i = 1; i <= HelmetZombieEat.length; i++) {
                HelmetZombieEat[i-1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombieRes/HelmetZom/eat/zomhelmeteat_"+i+".png")));
            }
        } catch (Exception e){e.printStackTrace();}


    }
    public void move(Zombie z) {
        if(z.getType() == 0 || z.getType() == 1 || z.getType() == 2){
            z.updateFrameCountMove();
            z.updateFrameCountEat();
        }
        z.move();
    }
    public static void frameCount(){
        if(realTimeCounter<30){
            realTimeCounter++;
        }
    }
    public void updates() {
        frameCount();
        for (Zombie z : zombieList) {
            if (z.isAlived()) {
                // Cập nhật tọa độ di chuyển cho zombie còn sống
                if (z.X() <= 100) {
                    z.dead();
                    zReachedEnd = true;
                } else {
                    move(z);
                }
            }
        }
    }
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        synchronized (zombieList) {
            if (zombieList.size() > 0) {
                for (Zombie z : zombieList) {
                    if (z.isAlived()) {
                        if(z.getType() == 0){
                            if(!z.isCollided()){
                                g.drawImage(normalZombieMove[z.getFrameCountMove()],(int) z.X(), (int) z.Y(), z.getWidth()+30, z.getHeight()+25, null);
                            } else {
                                g.drawImage(normalZombieEat[z.getFrameCountEat()],(int) z.X(), (int) z.Y()+8, z.getWidth()+18, z.getHeight(), null);
                            }
                        } else if (z.getType() == 1) {
                            if(!z.isCollided()){
                                g.drawImage(CatEarZombieMove[z.getFrameCountMove()],(int) z.X(), (int) z.Y(), z.getWidth()+30, z.getHeight()+25, null);
                            } else {
                                g.drawImage(CatEarZombieEat[z.getFrameCountEat()],(int) z.X(), (int) z.Y()+8, z.getWidth()+18, z.getHeight(), null);
                            }
                        } else if(z.getType() == 2){
                            if(!z.isCollided()){
                                g.drawImage(HelmetZombieMove[z.getFrameCountMove()],(int) z.X(), (int) z.Y(), z.getWidth()+30, z.getHeight()+25, null);
                            } else {
                                g.drawImage(HelmetZombieEat[z.getFrameCountEat()],(int) z.X(), (int) z.Y()+8, z.getWidth()+18, z.getHeight(), null);
                            }
                        }
                    }
                }
            }
        }
    }
}