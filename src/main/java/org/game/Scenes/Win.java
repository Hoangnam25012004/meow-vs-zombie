package org.game.Scenes;

import org.game.Manager.*;
import org.game.Component.MyButtons;
import java.awt.*;

public class Win implements SceneMethods{

    private World w;
    private MyButtons bMenu;
    private Image[] buttonOfWin;
    private Toolkit t = Toolkit.getDefaultToolkit();
    public Win(World w){
        this.w = w;
    }
    public void initButtons(){
        bMenu = new MyButtons("Main menu", 455,555,120,42);
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
