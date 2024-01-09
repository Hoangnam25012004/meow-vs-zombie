package org.game.Scenes;

import org.game.Manager.World;
import org.game.Component.MyButtons;
import java.awt.*;
import java.util.Set;

public class Setting implements SceneMethods{
    private World w;
    private MyButtons bMenu, bQuit, bPlaying;
    private Image[] buttonOfSetting;
    private Toolkit t = Toolkit.getDefaultToolkit();

    public Setting(World w){
        this.w = w;
    }

    public void initButtons(){
        bMenu = new MyButtons("Main menu", 277, 390,133,44);
        bQuit = new MyButtons("Quit", 453, 390, 126, 44);
        bPlaying = new MyButtons("Play", 620, 390, 126, 44);
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
