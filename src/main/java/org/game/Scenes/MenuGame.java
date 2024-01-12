package org.game.Scenes;

import org.game.Manager.World;
import org.game.Component.MyButtons;
import static org.game.Scenes.GameScenes.*;

import java.awt.*;
import javax.swing.*;

public class MenuGame implements SceneMethods{
    private World w;
    private MyButtons bPlaying, bQuit, bWin;
    private Image[] buttonOfMenu;
    private Toolkit t = Toolkit.getDefaultToolkit();
    public MenuGame(World w) {
        this.w = w;
    }

    public void initButtons() {
        bPlaying = new MyButtons("Play", 437, 350,150,60);
        bQuit = new MyButtons("Quit", 442, 440, 140, 55);
        bWin = new MyButtons("Win", 0, 0, 140, 55);
    }

    private void importImg(){
        buttonOfMenu = new Image[2];
        try {
            buttonOfMenu[0] = t.getImage(getClass().getResource("/Scene/PLAY.png"));
            buttonOfMenu[1] = t.getImage(getClass().getResource("/Scene/EXIT.png"));
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error - importImage()");
        }
    }

    public void drawImg(Graphics g){
        g.drawImage(buttonOfMenu[0], 437, 350, 150, 60, null);
        g.drawImage(buttonOfMenu[1], 442, 440, 140, 55, null);
    }



    @Override
    public void mouseClicked(int x, int y) {
        if(bPlaying.getBounds().contains(x,y)) {
            setGameScenes(PLAYING);
            w.getPlaying().getBarManager().setCDatStartOfGame();
        } else if (bQuit.getBounds().contains(x,y)) {
            System.exit(0);
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


    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img,0,0, w.getWidth(), w.getHeight(), null);
        initButtons();
//        drawButtons(g);
        importImg();
        drawImg(g);
    }
}
