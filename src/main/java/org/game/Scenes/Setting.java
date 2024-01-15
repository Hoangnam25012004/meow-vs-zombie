package org.game.Scenes;

import org.game.Audio.*;
import org.game.Component.*;
import org.game.Manager.*;

import javax.swing.*;
import java.awt.*;
import static org.game.Scenes.GameScenes.*;

public class Setting implements SceneMethods{
    private World w;
    private MyButtons bMenu, bRestart, bPlaying;
    private Image[] buttonOfSetting;
    private Toolkit t = Toolkit.getDefaultToolkit();

    public Setting(World w){
        this.w = w;
    }

    public void initButtons(){
        bMenu = new MyButtons("Main menu", 293, 360,126,44);
        bRestart = new MyButtons("Restart", 460, 360, 126, 44);
        bPlaying = new MyButtons("Play", 627, 360, 126, 44);
    }

    private void importImg(){
        buttonOfSetting = new Image[3];
        try {
            buttonOfSetting[0] = t.getImage(getClass().getResource("/Scene/exitpause copy.png"));
            buttonOfSetting[1] = t.getImage(getClass().getResource("/Scene/Restart paused ư.png"));
            buttonOfSetting[2] = t.getImage(getClass().getResource("/Scene/resume paused2.png"));
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error - importImage()");
        }
    }

    public void drawImg(Graphics g){
        g.drawImage(buttonOfSetting[0], 305, 360,126,44, null);
        g.drawImage(buttonOfSetting[1], 472, 360, 126, 44, null);
        g.drawImage(buttonOfSetting[2], 639, 360, 126, 44, null);
    }


    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img, 223,162, 624, 252,null);
        initButtons();
        importImg();
        drawImg(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)){
            Audio.stopRoof();
            Audio.menu();
            Audio.stopSetting();
            setGameScenes(MENU);
        } else if (bRestart.getBounds().contains(x, y)){
            System.exit(0);
         //   setGameScenes(LOSE);reset
        } else if (bPlaying.getBounds().contains(x, y)){
            Audio.stopSetting();
            Audio.roofStage();
            setGameScenes(PLAYING);
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
