package org.game.Manager;

import org.game.Component.Tile;
import org.game.Scenes.Playing;

//import javax.imageio.ImageIO;
import java.awt.*;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.Objects;

public class TileManager {
    private Tile[] tiles = new Tile[45];
    private int wTile = 60, hTile = 70;
    private Playing playing;
    private Image[] meowLightBlur = new Image[5];
    private Image[] meowHardBlur = new Image[5];
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Tile[] tilesOfLake = new Tile[5];
    public int wTileOfLake = 125;
    public int hTileOfLake = 70;
    private static TileManager instance;
    private boolean isInTile = false;

    public boolean isInTile() {
        return isInTile;
    }

    private TileManager(Playing playing) {
        initTiles();
//        importHardBlurPlant();
//        importLightBlurPlant();
        initTilesOfLake();
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
        int curX = 300, curY = 171, rowCounter = 0;
        for (int i = 0; i < 45; i++) {
            if (rowCounter >= 9) {
                curY += hTile + 10;
                curX = 300;
                rowCounter = 0;
            }
            curX += (wTile + 10);
            tiles[i] = new Tile(new Rectangle(curX, curY, wTile, hTile));
            rowCounter++;
        }
    }

    public void importLightBlurMeow(){
        meowLightBlur[0] = t.getImage(getClass().getResource("/Blur_Plants/Sunflower/sunflower (light - blur).png"));
        meowLightBlur[1] = t.getImage(getClass().getResource("/Blur_Plants/Peashooter/peashooter (light - blur).png"));
        meowLightBlur[2] = t.getImage(getClass().getResource("/Blur_Plants/Wall-nut/wall-nut (light - blur).png"));
        meowLightBlur[3] = t.getImage(getClass().getResource("/Blur_Plants/ShadowPea/ShadowPea (light - blur).png"));
        meowLightBlur[4] = t.getImage(getClass().getResource("/Blur_Plants/CherryBomb/cherrybomb (light - blur).png"));
    }
    public void importHardBlurMeow(){
        meowHardBlur[0] = t.getImage(getClass().getResource("/Blur_Plants/Sunflower/sunflower (hard - blur).png"));
        meowHardBlur[1] = t.getImage(getClass().getResource("/Blur_Plants/Peashooter/peashooter (hard - blur).png"));
        meowHardBlur[2] = t.getImage(getClass().getResource("/Blur_Plants/Wall-nut/wall-nut (hard - blur).png"));
        meowHardBlur[3] = t.getImage(getClass().getResource("/Blur_Plants/ShadowPea/ShadowPea (hard - blur).png"));
        meowHardBlur[4] = t.getImage(getClass().getResource("/Blur_Plants/CherryBomb/cherrybomb (hard - blur).png"));
    }

    public void drawTiles(Graphics g, LakeManager lakeManager) {
        int curX = 200;
        int curY = 171;

        for (Tile t: tilesOfLake) {
            Rectangle r = new Rectangle((int)t.getBound().getX(),(int)t.getBound().getY(), t.getWTileOfHouseOwner(), t.getHTileOfHouseOwner());

            g.setColor(Color.pink);
            g.fillRect(r.x, r.y, r.width, r.height);

            curY += t.getHTileOfHouseOwner() + 10;
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
            Rectangle r = new Rectangle((int)tiles[playing.getKeyBoardManager().getTileSelectedByKeyBoard()].getBound().getX(),(int)tiles[playing.getKeyBoardManager().getTileSelectedByKeyBoard()].getBound().getY(),tiles[playing.getKeyBoardManager().getTileSelectedByKeyBoard()].getwTile(),tiles[playing.getKeyBoardManager().getTileSelectedByKeyBoard()].gethTile());
            if(playing.getMeowManager().getIDhold() >= 0){
                if(!playing.getBarManager().getIsMeowInCD()[playing.getMeowManager().getIDhold()] && playing.getBarManager().getIsMeowEnoughFish()[playing.getMeowManager().getIDhold()] && !playing.getMeowManager().isForbidden()){
                    Graphics2D g2d = (Graphics2D) g;
                    if(playing.getMeowManager().getIDhold() != 3){
                        g2d.drawImage(meowLightBlur[playing.getMeowManager().getIDhold()],(int)r.getX(),(int)r.getY()+5,(int)r.getWidth()-15,(int)r.getHeight()-15,null);
                    } else {
                        g2d.drawImage(meowLightBlur[playing.getMeowManager().getIDhold()],(int)r.getX(),(int)r.getY()-25,(int)r.getWidth(),(int)r.getHeight()+15,null);
                    }
                } else {
                    Graphics2D g2n = (Graphics2D) g;
                    if(playing.getMeowManager().getIDhold() != 3){
                        g2n.drawImage(meowHardBlur[playing.getMeowManager().getIDhold()],(int)r.getX(),(int)r.getY()+5,(int)r.getWidth()-15,(int)r.getHeight()-15,null);
                    } else {
                        g2n.drawImage(meowHardBlur[playing.getMeowManager().getIDhold()],(int)r.getX(),(int)r.getY()-25,(int)r.getWidth(),(int)r.getHeight()+15,null);
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
//        drawTiles(g);
        drawMeowPreparedToPlace(g);
        drawTileSelectedByMouse(g);
    }





//    public void getTileImage(){
//        try {
//
//            for (int i = 1; i <= 45; i++) {
//
//                tile[i-1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Scene/land/land" +i+".png")));
//
//
//            }
//        } catch (IOException e){e.printStackTrace();}
//    }


//    public void loadMap(){
//        try {
//            InputStream is = getClass().getResourceAsStream("/Scene/LandTile.txt");
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            int col = 0;
//            int row = 0;
//            while (col < graphical.grassCol & row<graphical.grassRow){
//                String line = br.readLine();
//                while(col < graphical.grassCol){
//                    String number[] = line.split(" ");
//                    int num = Integer.parseInt(number[col]);
//                    mapTileNum[col][row] = num;
//                    col++;
//                }
//                if (col == graphical.grassCol){
//                    col = 0;
//                    row++;
//                }
//            }
//            br.close();
//
//        }catch (Exception e){}
//    }
//
//
//    public void render(Graphics2D g2){
//        int col = 0;
//        int row =0;
//        int x = 160, y = 102;
//
//        while (col < graphical.grassCol && row< graphical.grassRow){
//            int tileNum = mapTileNum[col][row];
//            g2.drawImage(tile[tileNum].image,x,y,graphical.grassTile,graphical.grassTile,null);
//            col++;
//            x += graphical.grassTile;
//            if (col == graphical.grassCol){
//                col = 0;
//                x =160 ;
//                row++;
//                y += graphical.grassTile;
//            }
//        }
//    }
}
