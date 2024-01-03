package org.game.Manager;

import org.game.graphic.Graphical;
import org.game.graphic.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    Graphical graphical;
    Tile[] tile;
    int mapTileNum [][];


    public TileManager(Graphical graphical){
        this.graphical = graphical;
        tile = new Tile[45];
        mapTileNum = new int[graphical.grassCol][graphical.grassRow];
        getTileImage();
        loadMap();
    }

    public void getTileImage(){
        try {

            for (int i = 1; i <= 45; i++) {

                tile[i-1] = new Tile();
                tile[i-1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Background/land/land"+i+".png")));


            }
        } catch (IOException e){e.printStackTrace();}
    }

    public void loadMap(){
        try {
            InputStream is = getClass().getResourceAsStream("/Background/LandTile.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < graphical.grassCol & row<graphical.grassRow){
                String line = br.readLine();
                while(col < graphical.grassCol){
                    String number[] = line.split(" ");
                    int num = Integer.parseInt(number[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == graphical.grassCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e){}
    }

    public void render(Graphics2D g2){
       // g2.drawImage(tile[0].image, 0 ,0 , graphical.tileSize, graphical.tileSize, null);
       // g2.drawImage(tile[1].image , 100,0,graphical.tileSize,graphical.tileSize,null);

        int col = 0;
        int row =0;
        int x = 160;
        int y=102;

        while (col < graphical.grassCol && row< graphical.grassRow){
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image,x,y,graphical.grassTile,graphical.grassTile,null);
            col++;
            x += graphical.grassTile;
            if (col == graphical.grassCol){
                col = 0;
                x =160 ;
                row++;
                y += graphical.grassTile;
            }
        }
    }
}
