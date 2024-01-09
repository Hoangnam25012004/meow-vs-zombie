package org.game.Scenes;

import org.game.Manager.*;
import org.game.Component.MyButtons;
import java.awt.*;

public class Lose implements SceneMethods{
    private World w;
    private MyButtons bMenu;
    private Image[] buttonOfLose;
    private Toolkit t = Toolkit.getDefaultToolkit();

    public Lose(World w){
        this.w = w;
    }

    private void initButtons() {
        bMenu = new MyButtons("Main menu", 445,555,120,42);
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

    public void updates() {
    }
}
