package org.game.Scenes;

import org.game.Audio.*;
import org.game.Component.MyButtons;
import org.game.Manager.World;

import javax.swing.*;
import java.awt.*;

import static org.game.Scenes.GameScenes.MENU;
import static org.game.Scenes.GameScenes.setGameScenes;

public class Win implements SceneMethods{
    private World w;
    private MyButtons bMenu;
    private Image[] buttonOfWin;
    private Image BlackBackground;
    private Toolkit t = Toolkit.getDefaultToolkit();

    public Win(World w){
        this.w = w;
    }

    public void initButtons(){
        bMenu = new MyButtons("Main menu", 475,347,120,42);
    }

    private void importImg(){
        buttonOfWin = new Image[1];
        try {
            buttonOfWin[0] = t.getImage(getClass().getResource("/Scene/EXIT.png"));
            BlackBackground = t.getImage(getClass().getResource("/Scene/Black.png.png"));
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error - importImage()");
        }
    }


    @Override
    public void render(Graphics g, Image img) {
        initButtons();
        importImg();
        g.drawImage(BlackBackground, 0,0 , w.getScreenWidth() , w.getScreenHeight(),null);
        g.drawImage(img,140,208, 790, 160, null);

        g.drawImage(buttonOfWin[0], 475,347,120,42, null);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(bMenu.getBounds().contains(x,y)) {
            Audio.menu();
            Audio.stopWin();
            setGameScenes(MENU);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
    }

    @Override
    public void mouseReleased(int x, int y) {
    }
    public void updates(){
    }
}
