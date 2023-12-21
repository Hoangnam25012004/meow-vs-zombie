package org.game.Collision;

import org.game.MeowPack.Meow;
import org.game.Hitbox.Rect;
import org.game.MeowPack.Shooter;
import org.game.Zombie.Zombie;
import org.game.bullet.Bullet;
import org.game.graphic.Graphical;

public class Collision {

    Meow shooter = new Shooter();

    Zombie zombie;

    Bullet bullet;

    Graphical graphical;

    Rect rect_zombie = new Rect(zombie.getX(), zombie.getY(), graphical.getZomWidth(),graphical.getZomHeight() );

    public Collision(Meow meow, Zombie zombie,Bullet bullet){
        this.shooter = meow;
        this.zombie = zombie;
        this.bullet = bullet;
    }

    public void checkCollideMeowZom(Meow meow, Zombie zombie){

    }

}
// to check collision , and main code of collide