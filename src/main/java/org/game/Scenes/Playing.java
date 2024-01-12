package org.game.Scenes;

import org.game.Manager.*;
import org.game.bullet.Bullet;
import org.game.Component.MyButtons;

import static org.game.Scenes.GameScenes.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Playing implements SceneMethods {
    private BulletManager bulletManager;
    private FishManager fishManager;
    private MeowManager meowManager;
    private MouseMotionManager mouseMotionManager;
    private TileManager tileManager;
    private ZombieManager zombieManager;
    private BarManager barManager;
    //private NotifManager notifManager;
    private KeyBoardManager keyBoardManager;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private World w;
    private boolean startWaveForCD = false;
    public Playing(World w) {
        this.w = w;
//        initManagers();
    }
    private void initManagers() {
        mouseMotionManager = new MouseMotionManager(this);
        //singleton application
        //notifManager = NotifManager.createNotifManager(this);
        zombieManager = ZombieManager.createZombieManager(this);
//        ZombieManager zombieManager1 = ZombieManager.createZombieManager(this);
        barManager = BarManager.createBar(this);
        tileManager = TileManager.createTileManager(this);
        meowManager = MeowManager.createMeowManager(this);
        fishManager = FishManager.createFishManager(this);
        bulletManager = new BulletManager();
    }

    public void mouseMove(int x, int y){
        mouseMotionManager.changeStatusToMouse(x,y,w);
        mouseMotionManager.mouseTrackPlantBar(x,y);
        mouseMotionManager.tileTrack(x,y);
    }


    @Override
    public void mouseClicked(int x, int y) {

    }

    @Override
    public void mousePressed(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {

    }

    public MeowManager getMeowManager() {
        return meowManager;
    }

    public BarManager getBarManager() {
        return barManager;
    }
    public KeyBoardManager getKeyBoardManager(){
        return keyBoardManager;
    }
    public MouseMotionManager getMouseMotionManager() {
        return mouseMotionManager;
    }
    public TileManager getTileManager(){
        return tileManager;
    }

    public FishManager getFishManager() {
        return this.fishManager;
    }

    public ZombieManager getZombieManager() {
        return this.zombieManager;
    }
    public boolean isStartWaveForCD() {
        return startWaveForCD;
    }
    public void MousePress(){
        mouseMotionManager.returnToSelectPlantByMouse();
    }



    public void updates() {
    }

    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img, 0, 0, w.getWidth(), w.getHeight(), null);
//        buttonManager.drawButtons(g);
//        tileManager.drawTiles(g, plantManager);
//        tileManager.drawTiles(g, houseOwnerManager);
        barManager.draw(g);
        mouseMotionManager.drawPlantSelectedByMouse(g);
        tileManager.draw(g); //fix laij chon bang chuot thoi
        meowManager.draw(g);
        zombieManager.render(g);
        fishManager.drawFish(g);
      //  notifManager.drawNotif(g);
        bulletManager.drawProjectile(g);
    }
}

