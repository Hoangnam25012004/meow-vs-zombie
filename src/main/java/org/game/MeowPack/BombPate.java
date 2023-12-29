package org.game.MeowPack;

import org.game.graphic.Graphical;

import java.awt.*;

public class BombPate extends Meow {
    private Graphical graphical;
    private int meowX,meowY;
    public BombPate(int meowId, String meowName,int x, int y, int width, int height, int healthPoint, int price) {
        super(meowId, meowName, x, y, width, height, healthPoint, price);
    }

    public void expolde() {

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
