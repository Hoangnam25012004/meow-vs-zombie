package org.game.MeowPack;

import org.game.Zombie.Zombie;
import org.game.graphic.Graphical;

import java.awt.*;

public class BombPate extends Meow {
    private Graphical graphical;
    private int meowX,meowY;

    // Attack damage/ explode damage
    private float explosionRadius = 10.0f;
    private int explosionDamage = 1;
    public BombPate(int meowId, String meowName,int x, int y, int width, int height, int healthPoint, int price) {
        super(meowId, meowName, x, y, width, height, healthPoint, price);
    }

    // Calls this function when zombies hit or collide with BombPate's rectangle
    public void expolde(Zombie zombie) {
        if (isWithinExplosionRange(zombie))
        {
            zombie.takeDamage(explosionDamage);
            // or
            // zombie.die() or zombie.destroy()...
        }
    }

    private boolean isWithinExplosionRange(Zombie zombie)
    {
        double distance = calculateDistance(zombie);
        return distance <= explosionRadius;
    }

    private float calculateDistance(Zombie zombie)
    {
        return (float)Math.sqrt(Math.pow(zombie.getX() - getX(), 2) + Math.pow(zombie.getY() - getY(), 2));
    }

    @Override
    public void render(Graphics2D g2) {
        g2.drawImage(can_1, meowX, meowY, graphical.getmeowWidth(), graphical.getmeowHeight(), null);
        g2.drawImage(can_2, meowX, meowY, graphical.getmeowWidth(), graphical.getmeowHeight(), null);
        g2.drawImage(can_3, meowX, meowY, graphical.getmeowWidth(), graphical.getmeowHeight(), null);
        g2.drawImage(can_4, meowX, meowY, graphical.getmeowWidth(), graphical.getmeowHeight(), null);

    }

    @Override
    public void update() {

    }
}
