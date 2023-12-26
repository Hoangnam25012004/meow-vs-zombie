package org.game.bullet;

import org.game.graphic.Graphical;

public class FreezeBullet extends Bullet {
    public FreezeBullet(Graphical graphical, int x, int y, int Dame){
        super(graphical,x,y,Dame);
        super.setisFrozen(true);
    }




    
}
