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
    private Toolkit t = Toolkit.getDefaultToolkit();
    private World w;
    private TileManager tm;
    public Playing(World w) {
        this.w = w;
//        initManagers();
    }

    public TileManager getTm(){
        return tm;
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

    public void updates() {
    }
}
