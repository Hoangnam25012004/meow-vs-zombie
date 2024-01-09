//package org.game.graphic;
//
//import org.game.Collision.Collision;
//import org.game.Manager.BulletManager;
//import org.game.Manager.TileManager;
//import org.game.MeowPack.Shooter;
//import org.game.Zombie.Zombie;
//import org.game.Zombie.catEarZombie;
//import org.game.Zombie.helmetZombie;
//import org.game.Zombie.normalZombie;
//import org.game.bullet.Bullet;
//import org.game.Scenes.*;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//
//
//
//public class Graphical{
//
//
//
//    // screen setting
//    final int originalTileSize = 16; //16x16 tile
//    private final int meowWidth = 15;
//    private final int meowHeight = 15;
//
//    private final int woolWidth = 6;
//    private final int woolHeight = 6;
//
//    private final int zomWidth = 14;
//    private final int zomHeight = 26;
//    public final int scale = 4; // set scale all to 4
//    public final int tileSize = originalTileSize*scale; // 64
//
//    private final int maxScreencol = 16;
//    private final int maxScreenrow = 9;
//    public final int grassCol = 9;
//    public final int grassRow = 5;
//    public final int grassTile = 87;
//
//
//    final int screenWidth = maxScreencol * tileSize;  //1024
//    final int screenHeight = maxScreenrow * tileSize; //576
//
//
//    private Lose lose;
//    private MenuGame menuGame;
//    private Playing playing;
//    private Setting setting;
//    private Win win;
//
//
//    BackgroundGraphic bg = new BackgroundGraphic(this);
//
//
//    private int FPS = 60;
//
//
//    Thread gameThread;
//    Shooter shooter = new Shooter(this,180,110);
//    Zombie zombie_1 = new normalZombie(this,700,90);
//    Zombie zombie_2 = new helmetZombie(this,700,300);
//    Zombie zombie_3 = new catEarZombie(this, 750,400);
//    BulletManager bulletManager = new BulletManager(this,140,110, 30 , true);
//    TileManager tileM = new TileManager(this);
//
//
//
//
//
//    public Graphical(){
//    }
//    public Lose getLose() {
//        return lose;
//    }
//    public MenuGame getMenuGame() {
//        return menuGame;
//    }
//    public Playing getPlaying() {
//        return playing;
//    }
//
//
//
//    public int getScreenWidth() {
//        return screenWidth;
//    }
//    public int getScreenHeight() {
//        return screenHeight;
//    }
//    public int getmeowHeight() {return meowHeight*scale;}
//    public int getmeowWidth() {return meowWidth*scale;}
//    public int getZomWidth() {return zomWidth*scale;}
//    public int getZomHeight() {return zomHeight*scale;}
//
//    public int getwoolWidth(){return woolWidth*scale;}
//    public int getwoolHeight(){return woolHeight*scale;}
//
//    public void update() {
//        zombie_1.update(shooter);
//        zombie_2.update(shooter);
//        zombie_3.update(shooter);
//        bulletManager.bullet_update(zombie_1);
//        shooter.update();
//
//
//
//    }
//   /* public void paint(Graphics g){
//        super.paint(g);
//        Graphics2D g2 = (Graphics2D)g;
//       // bg.render(g2);
//        tileM.render(g2);
//        shooter.render(g2);
//        zombie_1.render(g2);
//        zombie_2.render(g2);
//        zombie_3.render(g2);
//        bulletManager.render(g2);
//        g2.dispose();
//    }*/
//}
