package org.game.graphic;

import org.game.Collision.Collision;
import org.game.MeowPack.Shooter;
import org.game.Zombie.Zombie;
import org.game.Zombie.catEarZombie;
import org.game.Zombie.helmetZombie;
import org.game.Zombie.normalZombie;
import org.game.bullet.Bullet;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Graphical extends JPanel implements Runnable{



    // screen setting
    final int originalTileSize = 16; //16x16 tile
    private final int meowWidth = 13;
    private final int meowHeight = 14;

    private final int woolWidth = 5;
    private final int woolHeight = 5;

    private final int zomWidth = 14;
    private final int zomHeight = 22;
    public final int scale = 4; // set scale all to 4
    public final int tileSize = originalTileSize*scale; // 64

    private final int maxScreencol = 16;
    private final int maxScreenrow = 9;

    private final int grassCol = 9;
    private final int grassRow = 5;
    final int grassTile = 86;


    final int screenWidth = maxScreencol * tileSize; // 1024
    final int screenHeight = maxScreenrow * tileSize;// 576

    private final int grassWidth = grassCol*grassTile; //774
    private final int grassHeight = grassRow*grassTile; //430

    public BufferedImage backgroundImage;
    BackgroundGraphic bg = new BackgroundGraphic(this);


    private int FPS = 60;


    Thread gameThread;
    Shooter shooter = new Shooter(this,140,110);
    Zombie zombie_1 = new normalZombie(this,700,90);
    Zombie zombie_2 = new helmetZombie(this,700,300);
    Zombie zombie_3 = new catEarZombie(this, 750,400);
    Bullet bullet = new Bullet(this,140,110, 30);




    public Graphical(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreenHeight() {
        return screenHeight;
    }
    public int getmeowHeight() {return meowHeight*scale;}
    public int getmeowWidth() {return meowWidth*scale;}
    public int getZomWidth() {return zomWidth*scale;}
    public int getZomHeight() {return zomHeight*scale;}

    public int getwoolWidth(){return woolWidth*scale;}
    public int getwoolHeight(){return woolHeight*scale;}

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS; //0.0166667 second appear 1 frame
        double delta = 0;
        long lastTime = System.nanoTime(); //check this moment time
        long currentTime; //the currentTime
        long timer =0;
        int drawCount = 0;

        while (gameThread != null){
            currentTime = System.nanoTime(); // Always update the currentTime after 1 loop

            delta += (currentTime - lastTime)/drawInterval; // the time from the last time to the currenttime
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta>1) { //which mean its > 1 frame
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer > 1000000000){
                System.out.println("FPS: "+drawCount);
                drawCount=0;
                timer=0;
            }
        }
    }
    public void update() {
        zombie_1.zom_update(shooter);
        zombie_2.zom_update(shooter);
        zombie_3.zom_update(shooter);
        bullet.bullet_update(zombie_1);


    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        bg.render(g2);
        shooter.render(g2);
        zombie_1.render(g2);
        zombie_2.render(g2);
        zombie_3.render(g2);
        bullet.render(g2);

        g2.dispose();
    }
}
