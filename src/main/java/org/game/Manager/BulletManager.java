package org.game.Manager;
import org.game.Zombie.Zombie;
import org.game.bullet.Bullet;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class BulletManager {
    private double speed;
    private List<Bullet> bullet = new ArrayList<>();
    private int originalX;
    private int originalY;
    public BufferedImage bullet_1, bullet_2;
    public ArrayList<Bullet> bulletList = new ArrayList<>();
    Zombie zombie;

    public BulletManager(int x, int y, int Dame , boolean isFrozen) {
        this.originalX = x;
        this.originalY = y;
        getBulletImage();
    }



    //___________________________________________________________________________
    //
    public void getBulletImage() {
        try {
            bullet_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bullet/wool.png")));
            bullet_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bullet/Icewool.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics2D g2) {
        for (int i=0 ; i<= bullet.size();i++) {
            if (!bullet.get(i).getisFrozen()) {
                g2.drawImage(bullet_1, (int) bullet.get(i).getX(), bullet.get(i).getY(), bullet.get(i).getBulletSize(), bullet.get(i).getBulletSize(), null);

            } else {
                g2.drawImage(bullet_2,(int) bullet.get(i).getX(), bullet.get(i).getY(), bullet.get(i).getBulletSize(), bullet.get(i).getBulletSize(), null);
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

