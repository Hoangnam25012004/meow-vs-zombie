package org.game.Zombie;

import org.game.graphic.Graphical;

import java.awt.*;

public class catEarZombie extends Zombie {
    public catEarZombie(Graphical graphical, int HP, double speed, int attackPower) {
        super(graphical, HP, speed, attackPower);
        //TODO Auto-generated constructor stub
    
    }


    public catEarZombie(Graphical graphical, int x, int y){
        super(graphical,x,y);
        setPosition(x,y);
        getZomImage();}
    @Override
    public void render(Graphics2D g2){
        g2.drawImage(zom_2, (int) super.getX(), super.getY(),graphical.getZomWidth(), 25*graphical.scale, null);

    }
}
