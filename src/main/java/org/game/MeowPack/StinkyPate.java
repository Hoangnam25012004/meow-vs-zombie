package org.game.MeowPack;

import org.game.graphic.Graphical;

import java.awt.*;

public class StinkyPate extends Meow {
    private Graphical graphical;
    private int meowX,meowY;

    public StinkyPate(int meowId, String meowName, int x, int y, int width, int height, int healthPoint, int price) {
        super(meowId, meowName, x, y, width, height, healthPoint, price);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.drawImage(cattray1, meowX, meowY, graphical.getmeowWidth(), graphical.getmeowHeight(), null);
        g2.drawImage(cattray2, meowX, meowY, graphical.getmeowWidth(), graphical.getmeowHeight(), null);
        g2.drawImage(cattray3, meowX, meowY, graphical.getmeowWidth(), graphical.getmeowHeight(), null);
        g2.drawImage(cattray4, meowX, meowY, graphical.getmeowWidth(), graphical.getmeowHeight(), null);

    }

    @Override
    public void update() {

    }
}
