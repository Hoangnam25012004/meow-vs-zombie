package org.game.Scenes;

import org.game.Manager.TileManager;
import org.game.graphic.Graphical;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Playing implements SceneMethods{

    private Graphical graphical;
    private TileManager tm;
    public Playing(Graphical graphical){this.graphical = graphical;}

    public TileManager getTm(){
        return tm;
    }

    @Override
    public void render(Graphics g, Image img) {

    }

    @Override
    public void mouseClicked(int x, int y) {

    }

    @Override
    public void mousePressed(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {

    }
}
