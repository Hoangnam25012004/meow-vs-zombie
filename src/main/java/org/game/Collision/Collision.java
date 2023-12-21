package org.game.Collision;

import org.game.MeowPack.Meow;
import org.game.Hitbox.Rect;
import org.game.MeowPack.Shooter;
import org.game.Zombie.Zombie;
import org.game.bullet.Bullet;
import org.game.graphic.Graphical;

import java.io.IOException;

public class Collision {

    Shooter shooter = new Shooter(140,110);

    Zombie zombie;

    Bullet bullet;

    Graphical graphical;

    Rect rect_zombie = new Rect(zombie.getX(), zombie.getY(), graphical.getZomWidth(),graphical.getZomHeight() );

    public Collision(Shooter shooter, Zombie zombie,Bullet bullet){
        this.shooter = shooter;
        this.zombie = zombie;
        this.bullet = bullet;
    }

    public boolean checkCollideMeowZom(Shooter shooter, Zombie zombie) {
        this.shooter = shooter;
        this.zombie = zombie;


        if (zombie.getX() < (this.shooter.getMeowX() + (graphical.getmeowWidth() / 2)) &
                zombie.getX() > (this.shooter.getMeowX() - (graphical.getmeowWidth() / 2))) {
                return true;

        } else {
                return false;
            }


    }

}
// to check collision , and main code of collide