package org.game.bullet;

import org.game.Scenes.Playing;
import org.game.Zombie.Zombie;
import org.game.Audio.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class BulletLogic {

    private List<Bullet> bulletList = new ArrayList<>();
    private static int realTimeCounter = 0;

    public List<Bullet> getBulletList() {
        return bulletList;
    }
    public static void frameCount() {
        if (realTimeCounter < 90) {
            realTimeCounter++;
        }
    }
    public abstract void drawBullet(Graphics g);
    public abstract void bulletCreated(Shooting shooting);
    public void update(Playing playing) {
        frameCount();
        synchronized (bulletList) {
            Iterator<Bullet> iterator = bulletList.iterator();
            while ((iterator.hasNext())) {
                Bullet bullet = iterator.next();
                bullet.move();
                if (bullet.getX() >= 1070) {
                    iterator.remove();
                }
            }
        }
        bulletCollideZombie(playing);
    }

    public void bulletCollideZombie(Playing playing) {
        synchronized (playing.getZombieManager().getZombies()){
            Iterator<Zombie> iterator = playing.getZombieManager().getZombies().iterator();
            while ((iterator.hasNext())){
                Zombie zombie = iterator.next();
                Rectangle r = new Rectangle();
                r.setBounds((int) zombie.X(),(int) zombie.Y(),zombie.getWidth(),zombie.getHeight());
                synchronized (bulletList){
                    Iterator<Bullet> iterator2 = bulletList.iterator();
                    while (iterator2.hasNext()){
                        Bullet bullet = iterator2.next();
                        if(!bullet.getisFrozen()){
                            bulletDealDamage(30,r,bullet,zombie,iterator2);
                        } else if(bullet.getisFrozen()){
                            bulletDealDamage(50,r,bullet,zombie,iterator2);
                        }
                    }
                }
            }
        }
    }


    public void bulletDealDamage(int distance,Rectangle r, Bullet bullet, Zombie zombie, Iterator iterator2){
        if(r.contains(bullet.getX()+distance,bullet.getY()) && zombie.isAlived()){
            Audio.splat();
            zombie.setHp(zombie.getHp()-bullet.getDame());
            if(bullet.getisFrozen() && !zombie.isSlowed()){
                zombie.setSpd(zombie.getSpd()/2);
                zombie.setSlowed(true);
            }
            iterator2.remove();
            if(zombie.getHp() <= 0){
                Audio.zombieDeath();
                zombie.setDead(true);
                zombie.dead();
            }
        }
    }

}
