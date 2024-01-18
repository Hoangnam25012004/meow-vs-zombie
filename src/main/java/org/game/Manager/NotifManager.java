package org.game.Manager;
import org.game.Timer.*;
import org.game.notification.*;
import org.game.Scenes.Playing;

import java.awt.*;

public class NotifManager {
    private NotifPattern[] notifs;
    private Playing playing;
    private timeStage clearStageTime;
    private timeCDWave waveCDTime;
    private static NotifManager instance = null;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Image noticeImg = t.getImage(getClass().getResource("/scene/NOTICE.png"));
    private NotifManager(Playing playing) {
        notifs = new NotifPattern[2];
        this.playing = playing;
        setUpNotif();
    }

    public static NotifManager createNotifManager(Playing playing) {
        if(instance == null) {
            instance = new NotifManager(playing);
        } else {
            System.out.println("Cannot create another NotifManager");
        }
        return instance;
    }


    public void setUpNotif() {
        notifs[0] = new PlayingNotif(0, 4);
        clearStageTime = new timeStage(this.notifs[0].timeNotif(), 1);
        notifs[1] = new PlayingNotif(1, 10);
        waveCDTime = new timeCDWave(this.notifs[1].timeNotif(), 1);
        waveCDTime.resetTime();
    }

    public void refresh() {
        clearStageTime.refresh();
    }

    //draw
    public void drawNotif(Graphics g) {
        if (!playing.isStartWave()) {
            if (playing.getZombieManager().allZombieDead() && playing.getZombieManager().getTotalZom() == 0) {
                refresh();
                if(waveCDTime.isTime()) {
                    waveCDTime.setEndCDWave(true);
                } else {
                    waveCDTime.refresh();
                }
                countWave(g);
                if (!clearStageTime.isExecuted()) {
                    playing.setStartWaveForCD(false);
                    stageClear(g);
                }
            }

        }
        stageCurrent(g);
    }

    public void countWave(Graphics g) {
        g.drawImage(noticeImg, 700, 20, 190, 35, null);
        Font font = new Font("Arial", Font.BOLD, 22);
        g.setFont(font);
        g.setColor(Color.WHITE);
        int time = playing.getWaveManager().getCoolDownWave() - waveCDTime.getCurrentSec();
        g.drawString("Count down: " + time, 700, 20);
    }
    public void stageClear(Graphics g) {
        g.drawImage(notifs[0].getImage(), 1076 / 2 - 200, 576 / 2 - 200, 400, 400, null);
    }

    public void stageCurrent(Graphics g) {
        g.drawImage(noticeImg, 700, 40, 120, 35, null);
        Font font = new Font("Arial", Font.BOLD, 22);
        g.setFont(font);
        g.setColor(Color.WHITE);
        int currentWave = playing.getWaveManager().getCurWave();
        int currWave = 0;
        Integer.toString(currentWave);
        if (currentWave < 10 && currentWave > 0) {
            currWave += currentWave;
        }
        g.drawString("Wave " + currWave, 700, 40);
    }

    public void reset() {
        clearStageTime.resetTime();
        waveCDTime.resetTime();
    }

    public timeCDWave getWaveCDTime() {
        return waveCDTime;
    }
}
