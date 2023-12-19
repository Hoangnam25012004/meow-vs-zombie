package org.game.Collision;

import org.game.MeowPack.Meow;
import org.game.MeowPack.Rect;
import org.game.Zom.Zombie;
import org.game.bullet.Bullet;
import org.game.graphic.Graphical;

import java.awt.*;

public class Collision {

    Meow meow;

    Zombie zombie;

    Bullet bullet;

    Graphical graphical;

    Rect rect_zombie = new Rect(zombie.getX(), zombie.getY(), graphical.getZomWidth(),graphical.getZomHeight() );

    public Collision(Meow meow, Zombie zombie,Bullet bullet){
        this.meow = meow;
        this.zombie = zombie;
        this.bullet = bullet;
    }

    public void checkCollide(Meow meow, Zombie zombie,Bullet bullet){}

}
// to check collision , and main code of collide