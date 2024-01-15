package org.game.Manager;
import org.game.Zombie.Zombie;
import org.game.bullet.Bullet;
import org.game.bullet.BulletLogic;
import org.game.bullet.Shooting;

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
    private Toolkit t = Toolkit.getDefaultToolkit();
    public ArrayList<Bullet> bulletList = new ArrayList<>();
    Zombie zombie;



    //___________________________________________________________________________
    //

    @Override
    public void drawBullet(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        bulletImage[0] = t.getImage(getClass().getResource("/Bullet/wool.png"));
        bulletImage[1] = t.getImage(getClass().getResource("/Bullet/Icewool.png"));
        synchronized (getBulletList()){
            for (int i = 0; i < getBulletList().size(); i++) {
                if (getBulletList().get(i) != null) {
                    Bullet bullet = getBulletList().get(i);
                    if (bullet.getisFrozen() == false) {
                        g2d.drawImage(bulletImage[0], (int) bullet.getX(), bullet.getY(), 30, 30, null);
                    } else if (bullet.getisFrozen() == true) {
                        g2d.drawImage(bulletImage[1], (int) bullet.getX(), bullet.getY() - 10, 30, 30, null);
                    }
                }
            }
        }
    }


    //___________________________________________________________________________


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
    @Override
    public void bulletCreated(Shooting shooting) {
        synchronized (getBulletList()){
            if(shooting.getID() == 1){
                getBulletList().add(new Bullet(shooting.getX()+shooting.getWidth(),shooting.getY()+8,shooting.getATK(),false));
            } else if(shooting.getID() == 3){
                getBulletList().add(new Bullet(shooting.getX()+shooting.getWidth(),shooting.getY()+8,shooting.getATK(),true));
            }
        }
    }
}

