package org.game.Manager;

import org.game.Fish.Shark;
import org.game.MeowPack.Meow;
import org.game.MeowPack.Shooter;
import org.game.Zombie.Zombie;
import org.game.bullet.Bullet;
import org.game.Scenes.Playing;
import org.game.Component.Tile;
import org.game.Audio.*;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;

public class ZombieManager {

    protected int HP;
    private int totalZom = 7;
    private static boolean zReachedEnd = false;
    private int[] countShark = new int[5];
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
    private static boolean isReset = false;
    private int counter = 0;


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


//------------------------------------------------------------------------------
// spawn random zombies in random row order

    public int getTotalZom(){
        return totalZom;
    }
    public static void isResetTime(){
        if(isReset){
            realTimeCounter = 0;
            isReset = false;
        }
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
    public void createSingleZombie(){
        if (totalZom >= 0) {
            Random random = new Random();
            int Zomtype = random.nextInt(3);
            int row = random.nextInt(5)+1;
            zombieList.add(new Zombie((double) 1070 + random.nextInt(100), (double) 86 * row - 27, Zomtype));
            totalZom -= 1;
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
    public void ZombieCollideMeow(){
        synchronized (zombieList){
            Iterator<Zombie> iterator = zombieList.iterator();
            while (iterator.hasNext()){
                Zombie zombie = iterator.next();
                Rectangle r = new Rectangle();
                r.setBounds((int) zombie.X(),(int) zombie.Y(),zombie.getWidth(),zombie.getHeight());
                synchronized (playing.getMeowManager().getMeowList()){
                    Iterator<Meow> iterator1 = playing.getMeowManager().getMeowList().iterator();
                    while (iterator1.hasNext()){
                        Meow meow = iterator1.next();
                        if(meow.isAlive()){
                            if(r.contains(meow.getX()+meow.getWidth()-zombie.getWidth()+30,meow.getY()) && zombie.isAlived()){
                                zombie.setCollided(true);
                                zombie.updateFrameCountEat();
                                if(realTimeCounter >= 30){
                                    Audio.zombieEat();
                                    zombie.attackMeow(meow);
                                    isReset = true;
                                    meow.removeMeow(meow,iterator1,playing.getTileManager(),playing.getMeowManager());
                                    zombie.defeatMeow(meow);
                                }
                                for(Zombie zombie1:zombieList){
                                    Rectangle rZombie = new Rectangle((int)zombie1.X()-50,(int)zombie1.Y(),zombie1.getWidth()+100,zombie1.getHeight());
                                    if(r.intersects(rZombie)){
                                        zombie1.defeatMeow(meow);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            isResetTime();
            counter++;
        }
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
                } else if (z.X()> 100 && z.X() <=150){

                    if (z.Y()>=104 && z.Y()<189){
                        if (countShark[0] <1){
                            playing.getSharkManager().appear(1);
                            countShark[0] = 10;
                        }
                        else {move(z);}
                    }
                    if (z.Y()>=188 && z.Y()< 274){
                        if (countShark[1] <1){
                            playing.getSharkManager().appear(2);
                            countShark[1] = 10;
                        }
                        else {move(z);}
                    }
                    if (z.Y()>=274 && z.Y()< 360){
                        if (countShark[2] <1){
                            playing.getSharkManager().appear(3);
                            countShark[2] = 10;
                        }
                        else {move(z);}
                    }
                    if (z.Y()>=360 && z.Y()< 446){
                        if (countShark[3] <1){
                            playing.getSharkManager().appear(4);
                            countShark[3] = 10;
                        }
                        else {move(z);}
                    }
                    if (z.Y()>=446 && z.Y()<=532){
                        if (countShark[4] <1){
                            playing.getSharkManager().appear(5);
                            countShark[4] = 10;
                        }else {move(z);}
                    }
                }
                else {
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
                                g.drawImage(normalZombieMove[z.getFrameCountMove()],(int) z.X(), (int) z.Y(), z.getWidth()+7, z.getHeight()+10, null);
                            } else {
                                g.drawImage(normalZombieEat[z.getFrameCountEat()],(int) z.X(), (int) z.Y()+8, z.getWidth()+7, z.getHeight()+10, null);
                            }
                        } else if (z.getType() == 1) {
                            if(!z.isCollided()){
                                g.drawImage(CatEarZombieMove[z.getFrameCountMove()],(int) z.X(), (int) z.Y(), z.getWidth()-5, z.getHeight()-6, null);
                            } else {
                                g.drawImage(CatEarZombieEat[z.getFrameCountEat()],(int) z.X(), (int) z.Y()+8, z.getWidth()-5, z.getHeight()-6, null);
                            }
                        } else if(z.getType() == 2){
                            if(!z.isCollided()){
                                g.drawImage(HelmetZombieMove[z.getFrameCountMove()],(int) z.X(), (int) z.Y(), z.getWidth()+2, z.getHeight(), null);
                            } else {
                                g.drawImage(HelmetZombieEat[z.getFrameCountEat()],(int) z.X(), (int) z.Y()+8, z.getWidth()+2, z.getHeight(), null);
                            }
                        }
                    }
                }
            }
        }
    }
}