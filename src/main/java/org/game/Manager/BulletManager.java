package org.game.Manager;
import org.game.Zombie.Zombie;
import org.game.bullet.Bullet;
import org.game.bullet.BulletLogic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
public class BulletManager extends BulletLogic {
    private Image[] bulletImage = new Image[2];
    private int originalX;
    private int originalY;
    public BufferedImage bullet_1, bullet_2;
    public ArrayList<Bullet> bulletList = new ArrayList<>();
    Zombie zombie;



    //___________________________________________________________________________
    //

    @Override
    public void drawProjectile(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        try {
            bulletImage[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bullet/wool.png")));
            bulletImage[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bullet/Icewool.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        synchronized (getBullet()){
            Iterator<Bullet> iterator = getBullet().iterator();
            while ((iterator.hasNext())) {
                Bullet bullet = iterator.next();
                if(!bullet.getisFrozen()){
                    g2d.drawImage(bulletImage[0],(int) bullet.getX(),bullet.getY(),30,30,null);
                } else if(bullet.getisFrozen()){
                    g2d.drawImage(bulletImage[1],(int) bullet.getX(),bullet.getY()-10,70,30,null);
                }
            }
        }
    }


    //___________________________________________________________________________
    private void move(double speed) {
    }
    public Rectangle getBoundary (Bullet bullet) {
        return new Rectangle((int) bullet.getX(), bullet.getY(), 20, 20);

}


 /*   public void bullet_update(Zombie zombie) {
        move(4);
        if (super.x >= graphical.getScreenWidth()) {
            super.x = originalX;
            super.y = originalY;
        }
        if (super.x < (zombie.getX() + graphical.getZomWidth()) & super.x > (zombie.getX() - graphical.getZomWidth())) {
            super.x = originalX;
            super.y = originalY;
        }

    }

  */


    //___________________________________________________________________________
    public void addBullet(int x, int y) {
        bulletList.add(new Bullet(x, y, 30));
        System.out.println(bulletList);
    }
    public void slow(){

    }
}

