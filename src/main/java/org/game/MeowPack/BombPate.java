package org.game.MeowPack;

import org.game.Manager.ZombieManager;
import org.game.Zombie.Zombie;

import java.awt.*;

public class BombPate extends Meow {
    public ZombieManager zombieManager;
    private int meowX,meowY;

    // Attack damage/ explode damage
    private float explosionRadius = 10.0f;
    private int explosionDamage = 1;
    public BombPate(int meowHP, int meowId, int ATK,int x, int y,int width, int height , int price) {
        super(meowHP, meowId, ATK,x, y, width,height ,price,false);
    }

    // Calls this function when zombies hit or collide with BombPate's rectangle
    public void expolde(Zombie zombie) {
        if (isWithinExplosionRange(zombie))
        {
            zombieManager.takeDamage(explosionDamage);
            // or
            // zombie.die() or zombie.destroy()... okie chị ui
        }
    }

    private boolean isWithinExplosionRange(Zombie zombie)
    {
        double distance = calculateDistance(zombie);
        return distance <= explosionRadius;
    }

    private float calculateDistance(Zombie zombie)
    {
        return (float)Math.sqrt(Math.pow(zombie.X() - getX(), 2) + Math.pow(zombie.Y() - getY(), 2));
    }

    public void update() {

    }
}
