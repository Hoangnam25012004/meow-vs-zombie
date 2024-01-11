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
    World w;

    Tile tile;
    public BufferedImage[] normalZombieMove = new BufferedImage[3];
    public BufferedImage[] normalZombieEat = new BufferedImage[2];
    public BufferedImage[] CatEarZombieMove = new BufferedImage[3];
    public BufferedImage[] CatEarZombieEat = new BufferedImage[2];
    public BufferedImage[] HelmetZombieMove = new BufferedImage[3];
    public BufferedImage[] HelmetZombieEat = new BufferedImage[2];
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

    public Zombie createRandomZombie(int i){
        Random random = new Random();
        int zombieType = random.nextInt(3);
        switch (zombieType) {
            case 0:
                return new Zombie(w.getScreenWidth() + random.nextInt(100), 102 + tile.gethTile()*i,0); // normal 102 is the starting y
            case 1:
                return new Zombie(w.getScreenWidth() + random.nextInt(100),102 + tile.gethTile()*i,1); // catear
            case 2:
                return new Zombie(w.getScreenWidth() + random.nextInt(100),102 + tile.gethTile()*i,2); // helmet
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
                Zombie zombie = createRandomZombie(row);
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


    public void getNormalZombie(){
        try {
            for (int i = 0; i < normalZombieMove.length; i++) { //move normal zome
                normalZombieMove[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("zombieRes/normalZom/move/zommove_"+i+".png")));
            }
            for (int i = 0; i < normalZombieEat.length; i++) {
                normalZombieEat[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("zombieRes/normalZom/move/zommeat_"+i+".png")));
            }
        } catch (Exception e){e.printStackTrace();}
    }

    public void getCatEarZombie(){
        try {
            for (int i = 0; i < CatEarZombieMove.length; i++) { //move normal zome
                CatEarZombieMove[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("zombieRes/normalZom/move/zomcatear_"+i+".png")));
            }
            for (int i = 0; i < CatEarZombieEat.length; i++) {
                CatEarZombieEat[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("zombieRes/normalZom/move/zomcateareat_"+i+".png")));
            }
        } catch (Exception e){e.printStackTrace();}

    }
    public void getHelmetZombie(){
        try {
            for (int i = 0; i < HelmetZombieMove.length; i++) { //move normal zome
                HelmetZombieMove[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("zombieRes/normalZom/move/zomhelmet_"+i+".png")));
            }
            for (int i = 0; i < HelmetZombieEat.length; i++) {
                HelmetZombieEat[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("zombieRes/normalZom/move/zomhelmeteat_"+i+".png")));
            }
        } catch (Exception e){e.printStackTrace();}


    }
    public void move(Zombie z) {
        if(z.getType() == 0 || z.getType() == 1 || z.getType() == 2){
            z.updateFrameCountMove();
        } else {
            z.move();
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