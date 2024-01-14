package org.game.bullet;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BulletLogic {

    private List<Bullet> bullet = new ArrayList<>();

    public List<Bullet> getBullet() {
        return bullet;
    }
    public abstract void drawProjectile(Graphics g);
}
