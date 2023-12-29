package org.game.Player;

import org.game.graphic.Graphical;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import org.game.Scenes.GameScenes;

public class MyMouseListener implements MouseListener, MouseMotionListener {
    private Graphical graphical;

    public MyMouseListener(Graphical graphical){this.graphical = graphical;}
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            switch (GameScenes.gameScenes) {
                case MENU:
                    graphical.getMenuGame().mouseClicked(e.getX(), e.getY());
                    break;
                case PLAYING:
                    graphical.getPlaying().mouseClicked(e.getX(), e.getY());
                    break;
                case LOSE:
                    graphical.getLose().mouseClicked(e.getX(), e.getY());
                    break;
                case SETTING:
                    graphical.getSetting().mouseClicked(e.getX(), e.getY());
                    break;
                case WIN:
                    graphical.getWin().mouseClicked(e.getX(), e.getY());
                    break;
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
