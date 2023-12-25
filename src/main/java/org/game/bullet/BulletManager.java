package org.game.bullet;
import org.game.MeowPack.Shooter;
import org.game.Zombie.Zombie;
import org.game.Zombie.Zombie;
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
    public BufferedImage bullet_1;
    public ArrayList<Bullet> bulletLists = new ArrayList<>();
    Thread gameThread;
    Bullet bullet;
    Zombie zombie;

    public BulletManager(Graphical graphical, int x, int y, int Dame){
        super(x,y,Dame);
        this.graphical = graphical;
        this.originalX = x;
        this.originalY = y;
        setPosition(x,y);
        getBulletImage();}

    public void setPosition(double x, int y){
        this.x = x;
        this.y = y;}



    //___________________________________________________________________________
    //
    public void getBulletImage() {
        try {
            bullet_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bullet/wool.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics2D g2) {
        g2.drawImage(bullet_1, (int) this.x, this.y, graphical.getwoolWidth(), graphical.getwoolHeight(), null);
    }

    //___________________________________________________________________________
    private void move(double speed) {
        setPosition(this.x + speed, this.y);
    }


    public void bullet_update(Zombie zombie) {
        move(4);
        if (this.x >= graphical.getScreenWidth()) {
            this.x = originalX;
            this.y = originalY;
        }
        if (this.x < (zombie.getX()+graphical.getZomWidth()) & this.x > (zombie.getX() - graphical.getZomWidth())){
            this.x = originalX;
            this.y = originalY;
        }
        addBullet();
    }



    //___________________________________________________________________________
    public void addBullet() {
        long Currenttime = System.nanoTime();
        long timer = 0;

        if (timer > 1000000000){
            bulletLists.add (bullet);
            System.out.println(bulletLists);
            timer = 0;
        }

    }



    //___________________________________________________________________________
    public Rectangle getBoundary (){
        return new Rectangle((int) this.getX(), this.getY(), 5*graphical.scale, 5*graphical.scale) ;}
}