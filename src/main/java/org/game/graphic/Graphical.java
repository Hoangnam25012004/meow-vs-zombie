package org.game.graphic;

import javax.swing.*;
import java.awt.*;

public class Graphical extends JPanel implements Runnable{

    // screen setting
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3; // set scale all to 3
    final int tileSize = originalTileSize*scale; // 48

    final int maxScreencol = 16;
    final int maxScreenrow = 12;

    final int screenWidth = maxScreencol * tileSize; // the screen 16 times bigger the enity, 768
    final int screenHeight = maxScreenrow * tileSize;// 576


    Thread gameThread;


    public Graphical(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        while (gameThread != null){
            System.out.println("THIS GAME IS WORKING");
        }

    }
}
