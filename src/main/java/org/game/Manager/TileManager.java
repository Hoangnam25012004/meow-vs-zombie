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
    private Tile[] tile;
    int mapTileNum [][];
    private boolean inTile = false;


    public TileManager(Graphical graphical){
        this.graphical = graphical;
        tile = new Tile[45];
        mapTileNum = new int[graphical.grassCol][graphical.grassRow];
        initTiles();
        getTileImage();
        loadMap();
    }

    public Tile[] getTile(){
        return tile;
    }

    private void initTiles() {
        int curX = 160, curY = 102, rowCounter = 0;
        for (int i = 0; i < 45; i++) {
            if (rowCounter >= 9) {
                curY += graphical.grassTile;
                curX = 160;
                rowCounter = 0;
            }
            curX += graphical.grassTile;
            tile[i] = new Tile(new Rectangle(curX, curY, graphical.grassTile, graphical.grassTile));
            rowCounter++;
        }
    }
    public void getTileImage(){
        try {

            for (int i = 1; i <= 45; i++) {

                tile[i-1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Scene/land/land" +i+".png")));


            }
        } catch (IOException e){e.printStackTrace();}
    }
    public void setInTile(boolean b){
        this.inTile = b;
    }
    public boolean isInTile() {
        return inTile;
    }

    public void loadMap(){
        try {
            InputStream is = getClass().getResourceAsStream("/Scene/LandTile.txt");
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
        int col = 0;
        int row =0;
        int x = 160, y = 102;

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
