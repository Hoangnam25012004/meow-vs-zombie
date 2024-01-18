package org.game.notification;

import java.awt.*;

public interface NotifPattern {
    void importImg(int type);

    //    void importAnimation(int type);
    Image getImage();
    int timeNotif();
}
