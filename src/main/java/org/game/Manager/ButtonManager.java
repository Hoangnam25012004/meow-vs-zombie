package org.game.Manager;

import org.game.Component.MyButtons;
import org.game.Scenes.Playing;
import javax.swing.*;
import java.awt.*;

public class ButtonManager {
    private MyButtons bStart, bSetting;
    private Image[] buttonOfPlaying;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Playing playing;

    public ButtonManager(Playing playing) {
        this.playing = playing;
        initButtons();
        importImg();
    }

    private void initButtons() {
        //bStart = new MyButtons("Start", 650, 20, 140, 45);
        bSetting = new MyButtons("Setting", 950, 20, 100, 30);
    }

    public void importImg(){
        buttonOfPlaying = new Image[2];
        try {
            //buttonOfPlaying[0] = t.getImage(getClass().getResource("/Scene/PLAY.png"));
            buttonOfPlaying[1] = t.getImage(getClass().getResource("/Scene/pause.png"));
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error - importImage()");
        }
    }

    public void drawImg(Graphics g){
        //g.drawImage(buttonOfPlaying[0], 650,20,140,45, null);
        g.drawImage(buttonOfPlaying[1], 950,20,100,30, null);
    }


    public MyButtons getbStart() {
        return bStart;
    }

    public MyButtons getbSetting() {
        return bSetting;
    }
}
