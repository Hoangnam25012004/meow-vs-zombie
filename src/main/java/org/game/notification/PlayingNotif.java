package org.game.notification;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class PlayingNotif implements NotifPattern {
    private Image image;
    private Image[] CDSkull = new Image[259];
    private int time;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private int type;

    public PlayingNotif(int type, int time) {
        this.type = type;
        switch (this.type) {
            case 0:
                //clear stage notif
                importImg(type);
                this.time = time;
                break;
            case 1:
                this.time = time;
                break;
            case 2:
                break;
            default:
                System.out.println("undefined notif type!");
                break;
        }
    }

    @Override
    public int timeNotif() {
        return time;
    }

    @Override
    public void importImg(int type) {
        try {
            switch (type) {
                case 0:
                    image = t.getImage(getClass().getResource("/noti/stage_clear.png"));
                    break;
                case 1:
                    break;
                case 2:
                    break;
                default:
                    System.out.println("undefined notif type!");
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR - PlayingNotif - importImg");
        }
    }


    @Override
    public Image getImage() {
        return image;
    }



}
