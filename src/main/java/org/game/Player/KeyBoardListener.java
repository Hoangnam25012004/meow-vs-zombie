package org.game.Player;

import org.game.graphic.Graphical;
import org.game.Scenes.GameScenes;
import org.game.Scenes.MenuGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyBoardListener implements KeyListener {
    private Graphical graphical;
    public KeyBoardListener(Graphical graphical){
        this.graphical = graphical;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
