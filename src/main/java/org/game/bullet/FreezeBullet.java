package org.game.bullet;
public class FreezeBullet extends Bullet {
    public FreezeBullet(int x, int y, int Dame){
        super(x,y,Dame);
        super.setisFrozen(true);
    }




    
}
