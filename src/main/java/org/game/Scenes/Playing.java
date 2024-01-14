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
    private ButtonManager buttonManager;
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
        initManagers();
    }
    private void initManagers() {
        mouseMotionManager = new MouseMotionManager(this);
        //singleton application
        //notifManager = NotifManager.createNotifManager(this);
        zombieManager = ZombieManager.createZombieManager(this);
        barManager = BarManager.createBar(this);
        tileManager = TileManager.createTileManager(this);
        meowManager = MeowManager.createMeowManager(this);
        fishManager = FishManager.createFishManager(this);
        bulletManager = new BulletManager();
    }

    public void mouseMove(int x, int y){
        mouseMotionManager.changeStatusToMouse(x,y,w);
        mouseMotionManager.mouseTrackMeowBar(x,y);
        mouseMotionManager.tileTrack(x,y);
    }


    @Override
    public void mouseClicked(int x, int y) {
        //changeScene(x,y);
        chooseMeow(x,y);

    }

    @Override
    public void mousePressed(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {
        meowManager.mouse(x, y);
       // meowManager.removeMeowByBag(x,y);


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
        meowManager.update();
        barManager.update();
    }

    public void chooseMeow(int x, int y){
        for (MyButtons b2 : barManager.getPickMeow()) {
            if (b2.getBounds().contains(x, y)) {
                System.out.println("You choose " + b2.getText());
                //Audio.tapPlantBar();
                meowManager.setSelected(true);
                if(!barManager.isMeowLocked()){
                    if (b2.getText().contains("Bucket")) {
                        if(!isStartWaveForCD()){
                            meowManager.meowForbiddenFromStart();
                        } else {
                            meowManager.setForbidden(false);
                            barManager.bucket();
                        }
                    } else if (b2.getText().contains("Meow")) {
                        meowManager.setForbidden(false);
                        barManager.Meow();
                    } else if (b2.getText().contains("Stinky Pate")) {
                        meowManager.setForbidden(false);
                        barManager.StinkyPate();
                    } else if (b2.getText().contains("Ice Meow")) {
                        meowManager.setForbidden(false);
                        barManager.IceMeow();
                    } else if (b2.getText().contains("Pate Bomb")) {
                        meowManager.setForbidden(false);
                        barManager.PateBomb();
                    } else if(b2.getText().contains("Shovel")){
                        meowManager.setSelected(false);
                       // meowManager.setBagged(true);
                    }
                }
                barManager.setMeowLocked(true);
            }
        }
    }
 /*   public void changeScene(int x, int y){
        if (buttonManager.getbSetting().getBounds().contains(x, y)) {
           /* Audio.setting();
            Audio.stopRoof();
            Audio.stopReadySetPlant();
            setGameScenes(SETTING);
        } else if (buttonManager.getbStart().getBounds().contains(x, y)) {
            if (!startWave && zombieManager.allZombieDead()) {
                startGame();
                startWave = true;
                callHorde = false;
                startWaveForCD = true;
                System.out.println("click on start");
                waveManager.readyNewWave();
                notifManager.reset();
            }
        }
    } */
    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img, 0, 0, w.getWidth(), w.getHeight(), null);
//        buttonManager.drawButtons(g);
//        tileManager.drawTiles(g, meowManager);
//        tileManager.drawTiles(g, houseOwnerManager);
        barManager.draw(g);
        mouseMotionManager.drawMeowSelectedByMouse(g);
        tileManager.draw(g);
        meowManager.draw(g);
        zombieManager.render(g);
        fishManager.drawFish(g);
      //  notifManager.drawNotif(g);
        bulletManager.drawProjectile(g);
    }
}

