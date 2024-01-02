package org.game.Manager;
import org.game.Zombie.Zombie;
import org.game.bullet.Bullet;
import org.game.graphic.Graphical;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
public class BulletManager extends Bullet {
    private Graphical graphical;
    private double speed;
    private int originalX;
    private int originalY;
    public BufferedImage bullet_1, bullet_2;
    public ArrayList<Bullet> bulletList = new ArrayList<>();
    Zombie zombie;

    public BulletManager(Graphical graphical, int x, int y, int Dame , boolean isFrozen) {
        super(graphical, x, y, Dame, isFrozen);
        this.graphical = graphical;
        this.originalX = x;
        this.originalY = y;
        setPosition(x, y);
        getBulletImage();
    }

    public void setPosition(double x, int y) {
        this.x = x;
        this.y = y;
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
        if (getisFrozen() == false) {
            g2.drawImage(bullet_1, (int) super.x, super.y, graphical.getwoolWidth(), graphical.getwoolHeight(), null);

        } else {g2.drawImage(bullet_2, (int) super.x, super.y, graphical.getwoolWidth(), graphical.getwoolHeight(), null);}

    }

    //___________________________________________________________________________
    private void move(double speed) {
        setPosition(super.x + speed, super.y);
    }


    public void bullet_update(Zombie zombie) {
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


    //___________________________________________________________________________
    public void addBullet(int x, int y) {
        bulletList.add(new Bullet(x, y, 30));
        System.out.println(bulletList);
    }

    public void slow(){

    }
}

