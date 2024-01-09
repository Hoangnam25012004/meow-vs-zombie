package org.game.Scenes;

import org.game.Manager.*;
import org.game.bullet.Bullet;
import org.game.graphic.Graphical;
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
    private KeyBoardManager keyBoardManager;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private World w;
    public Playing(World w) {
        this.w = w;
//        initManagers();
    }

    public void mouseMove(int x, int y){
        mouseMotionManager.changeStatusToMouse(x,y,w);
        mouseMotionManager.mouseTrackPlantBar(x,y);
        mouseMotionManager.tileTrack(x,y);
    }

    @Override
    public void render(Graphics g, Image img) {

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
    public TileManager getTileManager(){
        return tileManager;
    }
    public void MousePress(){
        mouseMotionManager.returnToSelectPlantByMouse();
    }



    public void updates() {
    }
}

