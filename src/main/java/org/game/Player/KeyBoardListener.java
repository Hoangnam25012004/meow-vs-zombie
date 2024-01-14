package org.game.Player;
import org.game.Scenes.GameScenes;
import org.game.Scenes.MenuGame;
import org.game.Manager.World;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyBoardListener implements KeyListener {
    private World w;
    public KeyBoardListener(World w) {
        this.w = w;
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
