package org.game.Scenes;

import org.game.Manager.*;
import org.game.Component.MyButtons;
import org.game.Audio.*;

import static org.game.Scenes.GameScenes.*;
import javax.swing.*;
import java.awt.*;

public class Lose implements SceneMethods{
    private World w;
    private MyButtons bMenu;
    private Image[] buttonOfLose;
    private Image BlackBackground;
    private Toolkit t = Toolkit.getDefaultToolkit();

    public Lose(World w){
        this.w = w;
    }

    private void initButtons() {

        bMenu = new MyButtons("Main menu", 475,347,120,42);
    }
    private void importImg(){
        buttonOfLose = new Image[1];
        try {
            buttonOfLose[0] = t.getImage(getClass().getResource("/Scene/EXIT.png"));
            BlackBackground = t.getImage(getClass().getResource("/Scene/Black.png.png"));
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error - importImage()");
        }
    }
    @Override
    public void render(Graphics g, Image img) {
        importImg();
        initButtons();
        g.drawImage(BlackBackground, 0,0 , w.getScreenWidth() , w.getScreenHeight(),null);
        g.drawImage(img,140,208, 790, 160, null);
        g.drawImage(buttonOfLose[0], 475,347,120,42, null );

    }

    @Override
    public void mouseClicked(int x, int y) {
        if(bMenu.getBounds().contains(x,y)) {
            Audio.menu();
            Audio.stopLose();
            setGameScenes(MENU);
        }

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
