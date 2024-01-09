package org.game.graphic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class BackgroundGraphic {

    public BufferedImage backgroundImage;
    private Graphical graphical;




    public BackgroundGraphic(Graphical graphical){
        this.graphical=graphical;
        getBackgroundImage();


    }

    public void getBackgroundImage() {
        try {
            this.backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Scene/background_2.jpg")));

        } catch (IOException e){e.printStackTrace();}
    }

    public void render(Graphics2D g2){
        g2.drawImage(backgroundImage, 0 ,0 ,graphical.getScreenWidth(), graphical.getScreenHeight(), null);
    }
}
