package org.game.Manager;

import org.game.Component.Tile;
import org.game.Scenes.Playing;

//import javax.imageio.ImageIO;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;


public class TileManager {
    private Tile[] tiles = new Tile[45];
    private int wTile = 86, hTile = 86;
    private Playing playing;
    private Image[] meowLightBlur = new Image[5];
    private Image[] meowHardBlur = new Image[5];
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Image bagSprite = t.getImage(getClass().getResource("/Bag/Bag.png"));
    private Tile[] tilesOfLake = new Tile[5];
    public int wTileOfLake = 125;
    public int hTileOfLake = 70;
    private static TileManager instance;
    private boolean isInTile = false;
    int[][] mapTileNum;

    public boolean isInTile() {
        return isInTile;
    }

    private TileManager(Playing playing) {
        initTiles();
        importHardBlurMeow();
        importLightBlurMeow();
        initTilesOfLake();
        mapTileNum = new int[9][5];
        loadMap();
        getTileImage();
        this.playing = playing;
    }

    public static TileManager createTileManager(Playing playing) {
        if(instance == null) {
            instance = new TileManager(playing);
        } else {
            System.out.println("Cannot create another TileManager");
        }
        return instance;
    }


    private void initTiles() {
        int curX = 90, curY = 102, rowCounter = 0;
        for (int i = 0; i < 45; i++) {
            if (rowCounter >= 9) {
                curY += hTile;
                curX = 90;
                rowCounter = 0;
            }
            curX += wTile;
            tiles[i] = new Tile(new Rectangle(curX, curY, wTile, hTile) );
            rowCounter++;
        }
    }

    public void importLightBlurMeow(){
        meowLightBlur[0] = t.getImage(getClass().getResource("/BlurMeow/LightBlur/bucket 1.png"));
        meowLightBlur[1] = t.getImage(getClass().getResource("/BlurMeow/LightBlur/cat-sit 1.png"));
        meowLightBlur[2] = t.getImage(getClass().getResource("/BlurMeow/LightBlur/Cattray (1)1.png"));
        meowLightBlur[3] = t.getImage(getClass().getResource("/BlurMeow/LightBlur/Icecat 1.png"));
        meowLightBlur[4] = t.getImage(getClass().getResource("/BlurMeow/LightBlur/food 1.png"));
    }
    public void importHardBlurMeow(){
        meowHardBlur[0] = t.getImage(getClass().getResource("/BlurMeow/HardBlur/bucket 1.png"));
        meowHardBlur[1] = t.getImage(getClass().getResource("/BlurMeow/HardBlur/cat-sit 1.png"));
        meowHardBlur[2] = t.getImage(getClass().getResource("/BlurMeow/HardBlur/Cattray (1)1.png"));
        meowHardBlur[3] = t.getImage(getClass().getResource("/BlurMeow/HardBlur/Icecat 1.png"));
        meowHardBlur[4] = t.getImage(getClass().getResource("/BlurMeow/HardBlur/food 1.png"));
    }
    public void drawBagSprite(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
            if(playing.getMeowManager().getisBagged()){
                Rectangle r = new Rectangle((int)tiles[playing.getMouseMotionManager().getTileSelectedByMouse()].getBound().getX(),(int)tiles[playing.getMouseMotionManager().getTileSelectedByMouse()].getBound().getY(),tiles[playing.getMouseMotionManager().getTileSelectedByMouse()].getwTile(),tiles[playing.getMouseMotionManager().getTileSelectedByMouse()].gethTile());
                g2d.drawImage(bagSprite,(int)r.getX()-10,(int)r.getY()-5,(int)r.getWidth()+20,(int)r.getHeight()+20,null);
            }

    }
    public void drawTiles(Graphics g){
        int col = 0;
        int row =0;
        int x = 176, y = 102;

        while (col < 9 && row< 5){
            int tileNum = mapTileNum[col][row];
            g.drawImage(tiles[tileNum].image,x,y,wTile,hTile,null);
            col++;
            x += wTile;
            if (col == 9){
                col = 0;
                x =176 ;
                row++;
                y += hTile;
            }
        }
    }

    private void initTilesOfLake() {
        int curX = 230;
        int curY = 171;
        int rowCounter = 0;

        for (int i = 0; i < 5; i++) {
            if (rowCounter >= 1) {
                curX = 230;
                curY += hTileOfLake + 10;
                rowCounter = 0;
            }
            tilesOfLake[i] = new Tile(new Rectangle(curX, curY, wTileOfLake, hTileOfLake));
            rowCounter++;
        }
    }

    public Tile[] getTiles() {
        return tiles;
    }
    public void setInTile(boolean inTile){
        this.isInTile = inTile;
    }
    public void drawMeowPreparedToPlace(Graphics g){
        if((playing.getMeowManager().isSelected() && playing.getMouseMotionManager().getisControlledByMouse()) || !playing.getMouseMotionManager().getisControlledByMouse()){
            Rectangle r = new Rectangle((int)tiles[playing.getMouseMotionManager().getTileSelectedByMouse()].getBound().getX(),(int)tiles[playing.getMouseMotionManager().getTileSelectedByMouse()].getBound().getY(),tiles[playing.getMouseMotionManager().getTileSelectedByMouse()].getwTile(),tiles[playing.getMouseMotionManager().getTileSelectedByMouse()].gethTile());
            if(playing.getMeowManager().getIDhold() >= 0){
                if(!playing.getBarManager().getIsMeowInCD()[playing.getMeowManager().getIDhold()] && playing.getBarManager().getIsMeowEnoughFish()[playing.getMeowManager().getIDhold()] && !playing.getMeowManager().isForbidden()){
                    Graphics2D g2d = (Graphics2D) g;

                    g2d.drawImage(meowLightBlur[playing.getMeowManager().getIDhold()],(int)r.getX(),(int)r.getY()+5,(int)r.getWidth()-15,(int)r.getHeight()-15,null);

                } else {
                    Graphics2D g2n = (Graphics2D) g;{
                        g2n.drawImage(meowHardBlur[playing.getMeowManager().getIDhold()],(int)r.getX(),(int)r.getY()+5,(int)r.getWidth()-15,(int)r.getHeight()-15,null);
                    }
                }
            }
        }
    }
    public void drawTileSelectedByMouse(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(playing.getBarManager().getPickedMeow(),(int)tiles[playing.getMouseMotionManager().getTileSelectedByMouse()].getBound().getX(),(int)tiles[playing.getMouseMotionManager().getTileSelectedByMouse()].getBound().getY(),tiles[playing.getMouseMotionManager().getTileSelectedByMouse()].getwTile(),tiles[playing.getMouseMotionManager().getTileSelectedByMouse()].gethTile(),null);
    }
    public void draw(Graphics g){
        drawTiles(g);
        drawMeowPreparedToPlace(g);
        drawTileSelectedByMouse(g);
    }

    public int getwTile(){
        return wTile;
    }

    public int gethTile() {
        return hTile;
    }

    public void getTileImage(){
        try {
            for (int i = 1; i <= 45; i++) {
                tiles[i-1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Scene/land/land" +i+".png")));
            }
        } catch (IOException e){e.printStackTrace();}
    }


    public void loadMap(){
        try {
            InputStream is = getClass().getResourceAsStream("/Scene/LandTile.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < 9 & row< 5){
                String line = br.readLine();
                while(col < 9){
                    String number[] = line.split(" ");
                    int num = Integer.parseInt(number[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == 9){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e){e.printStackTrace();}
    }

}
