package org.game.Manager;

import org.game.Audio.Audio;
import org.game.Fish.Shark;
import org.game.Scenes.Playing;
import org.game.Zombie.Zombie;
import org.game.bullet.Bullet;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class SharkManager {
    private Playing playing;
    private int sharkCount=0;
    private int frameCounter=0;
    private ArrayList<Shark> sharkRow;
    private static int realTimeCounter = 0;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Image[] sharkImage = new Image[5];
    public SharkManager(Playing playing){
        this.playing = playing;
        sharkRow = new ArrayList<>();
        getImg();
    }
    public void updateSharkCount(){
        frameCounter++;
        if (frameCounter == 10 && sharkCount<4){
            frameCounter =0;
            sharkCount++;
        } else if (frameCounter == 10 && sharkCount==4){
            frameCounter = 0;
            sharkCount = 3;
        }
    }
    public static void frameCount() {
        if (realTimeCounter < 90) {
            realTimeCounter++;
        }
    }
    public void appear(int row){
        sharkRow.add( new Shark(90, 86 * row, 80,80));
    }

    public void getImg(){
        for (int i = 1; i<=sharkImage.length ; i++){
            sharkImage[i-1] = t.getImage(getClass().getResource("/Shark/Shark"+i+".png"));
        }
    }

    public void SharkCollideZombie(Playing playing){
        synchronized (playing.getZombieManager().getZombies()){
            Iterator<Zombie> iterator = playing.getZombieManager().getZombies().iterator();
            while ((iterator.hasNext())){
                Zombie zombie = iterator.next();
                Rectangle r = new Rectangle();
                r.setBounds((int) zombie.X(),(int) zombie.Y(),zombie.getWidth(),zombie.getHeight());
                synchronized (sharkRow){
                    Iterator<Shark> iterator2 = sharkRow.iterator();
                    while (iterator2.hasNext()){
                        Shark shark = iterator2.next();
                        sharkDealDamage(0,r,shark,zombie);
                    }
                }
            }
        }
    }
    public void sharkDealDamage(int distance,Rectangle r, Shark shark, Zombie zombie){
        if(r.contains(shark.getX()+distance,shark.getY()+80/2) && zombie.isAlived()){
            Audio.splat();
            zombie.setHp(zombie.getHp()-shark.getDame());
            if(zombie.getHp() <= 0){
                Audio.zombieDeath();
                zombie.setDead(true);
                zombie.dead();
            }
        }
    }

    public void move(Shark s) {
        updateSharkCount();
        s.move();
    }
    public ArrayList<Shark> getSharkList(){
        return sharkRow;
    }
    public void update(Playing playing) {
        frameCount();
        synchronized (sharkRow) {
            Iterator<Shark> iterator = sharkRow.iterator();
            while ((iterator.hasNext())) {
                Shark shark = iterator.next();
                move(shark);
                if (shark.getX() >= 1070) {
                    iterator.remove();
                }
            }
        }
        SharkCollideZombie(playing);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        synchronized (sharkRow) {
            if (sharkRow.size() > 0) {
                for (Shark s : sharkRow) {
                    if (s != null) {
                        g2d.drawImage(sharkImage[sharkCount], s.getX(), s.getY(), s.getWidth(), s.getHeight(), null);
                    }
                }
            }
        }
    }

}
