package org.game.Zombie;

import org.game.graphic.Graphical;

import java.awt.*;

public class helmetZombie extends Zombie {
    public helmetZombie(int HP, double speed, int attackPower) {
        super(HP, speed, attackPower);
        //TODO Auto-generated constructor stub
    

    }



    public helmetZombie(Graphical graphical, int x, int y){
        super(graphical,x,y);
        setPosition(x,y);
        getZomImage();}
    @Override
    public void render(Graphics2D g2){
        g2.drawImage(zom_3, (int) super.getX(), super.getY(),graphical.getZomWidth(), 24*graphical.scale, null);

    }

}
