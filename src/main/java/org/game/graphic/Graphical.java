package org.game.graphic;

import org.game.MeowPack.Shooter;
import org.game.Zom.Zombie;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Graphical extends JPanel implements Runnable{



    // screen setting
    final int originalTileSize = 16; //16x16 tile
    private final int meowWidth = 13;
    private final int meowHeight = 14;

    private final int zomWidth = 14;
    private final int zomHeight = 22;
    public final int scale = 4; // set scale all to 4
    public final int tileSize = originalTileSize*scale; // 64

    private final int maxScreencol = 14;
    private final int maxScreenrow = 10;

    final int screenWidth = maxScreencol * tileSize; // 896
    final int screenHeight = maxScreenrow * tileSize;// 640

    public BufferedImage backgroundImage;
    BackgoundGraphic bg = new BackgoundGraphic(this);

    private int FPS = 60;


    Thread gameThread;
    Shooter shooter = new Shooter(this);
    Zombie zombie_1 = new Zombie(this);


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
        zombie_1.zom_update();
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        bg.render(g2);
        shooter.render(g2);
        zombie_1.render(g2);

        g2.dispose();
    }
}
