package org.game.Manager;

import org.game.graphic.Graphical;
import org.game.graphic.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TileManager {
    Graphical graphical;

    Tile[] tile;


    public TileManager(Graphical graphical){
        this.graphical = graphical;
        tile = new Tile[45];
        getTileImage();
    }

    public void getTileImage(){
        try {

            for (int i = 1; i <= 45; i++) {

                tile[i-1] = new Tile();
                tile[i-1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Background/land/land"+i+".png")));


            }
        } catch (IOException e){e.printStackTrace();}
    }


    public void render(Graphics2D g2){
       // g2.drawImage(tile[0].image, 0 ,0 , graphical.tileSize, graphical.tileSize, null);
       // g2.drawImage(tile[1].image , 100,0,graphical.tileSize,graphical.tileSize,null);

        int col = 0;
        int row =0;
        int x = 0;
        int y=0;

       // while (col < graphical.grassCol)
    }
}
