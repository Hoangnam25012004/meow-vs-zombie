package org.game.Manager;

import org.game.Component.*;
import org.game.Scenes.Playing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BarManager {
    private Image[] pick_meowBar;
    private Image[] meowInCD;
    private Image pickedMeow;
    private Image meowBar;
  //  private Image dog;
    private MyButtons pickMeow[];

    private List<Integer> meowPickedID = new ArrayList<>();
    private boolean[] isMeowInCD = new boolean[5];
    private boolean[] isCDReducing = new boolean[5];
    private boolean[] isMeowEnoughFish = new boolean[5];
    private boolean isMeowLocked = false;
    private static BarManager instance = null;

    public boolean isMeowLocked() {
        return isMeowLocked;
    }

    public void setMeowLocked(boolean meowLocked) {
        isMeowLocked = meowLocked;
    }

    private int[] meowCD = new int[5];
    private int tile = 0;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private static Playing playing;
    private BarManager(Playing playing) {
        initButtons();
        importImg();
        initmeowInCD();
        this.playing = playing;
    }

    public static BarManager createBar(Playing playing) {
        if(instance == null) {
            instance = new BarManager(playing);
        } else {
            System.out.println("Cannot create another BarManager");
        }
        return instance;
    }

    private void initButtons() {
        pickMeow = new MyButtons[5];
        pickMeow[0] = new MyButtons("Bucket", 95, 12, 100, 74);
        pickMeow[1] = new MyButtons("Meow", 195, 12, 100, 74);
        pickMeow[2] = new MyButtons("Stinky Pate", 295, 12, 100, 74);
        pickMeow[3] = new MyButtons("Ice Meow", 395, 12, 100, 74);
        pickMeow[4] = new MyButtons("Pate Bomb", 495, 12, 100, 74);
        //pickPlant[5] = new MyButtons("Shovel",940,20,80,70);
    }

    private void importImg(){
        pick_meowBar = new Image[5];
        try {
            pick_meowBar[0] = t.getImage(getClass().getResource("/meowBar/Bucket.png"));
            pick_meowBar[1] = t.getImage(getClass().getResource("/meowBar/Meow.png"));
            pick_meowBar[2] = t.getImage(getClass().getResource("/meowBar/Tray.png"));
            pick_meowBar[3] = t.getImage(getClass().getResource("/meowBar/Icecat.png"));
            pick_meowBar[4] = t.getImage(getClass().getResource("/meowBar/pateBomb.png"));
            //pick_plantBar[5] = t.getImage(getClass().getResource("/shovel/shovel.png"));
            pickedMeow = t.getImage(getClass().getResource("/meowBar/plantSelected.png"));
            meowBar = t.getImage(getClass().getResource("/meowBar/MeowBar.png"));
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error - importImage()");
        }
    }
    public Image getPickedMeow() {
        return pickedMeow;
    }

    public boolean[] getIsMeowInCD() {
        return isMeowInCD;
    }

    public List<Integer> getMeowPickedID() {
        return meowPickedID;
    }

    public void setIsMeowInCD(int index,boolean isMeowInCD) {
        this.isMeowInCD[index] = isMeowInCD;
    }

    public void drawMeowbar(Graphics g){
    /*    g.setColor(Color.black);
        g.drawRect(365, 10,575,90);
        g.setColor(Color.pink);
        g.fillRect(365, 10, 575, 90); */
        g.drawImage(meowBar, 0, 0, 612, 108, null);
        Graphics2D g2d = (Graphics2D) g;
        int distance = 0;
        for (Image p : pick_meowBar){
            g.drawImage(p,95 + distance, 12, 100, 74, null );
            distance += 100;
        }
    }

    public void fish(){
        playing.getMeowManager().setIDhold(0);
        meowPickedID.add(0);
        playing.getMeowManager().setFishCostHold(50);
    }
    public void peaShooter(){
        playing.getMeowManager().setIDhold(1);
        meowPickedID.add(1);
        playing.getMeowManager().setFishCostHold(100);
    }
    public void wall_nut(){
        playing.getMeowManager().setIDhold(2);
        meowPickedID.add(2);
        playing.getMeowManager().setFishCostHold(50);
    }
    public void shadowPea(){
        playing.getMeowManager().setIDhold(3);
        meowPickedID.add(3);
        playing.getMeowManager().setFishCostHold(175);
    }
    public void cherryBomb(){
        playing.getMeowManager().setIDhold(4);
        meowPickedID.add(4);
        playing.getMeowManager().setFishCostHold(150);
    }

    public boolean[] getIsMeowEnoughFish() {
        return isMeowEnoughFish;
    }
    public void setCDatStartOfGame(){
        meowCD[0] = 205;
        meowCD[1] = 205;
        meowCD[2] = 205;
        meowCD[3] = 205;
        meowCD[4] = 205;
        meowPickedID.add(0);
        meowPickedID.add(1);
        meowPickedID.add(2);
        meowPickedID.add(3);
        meowPickedID.add(4);
        isMeowInCD[0] = true;
        isMeowInCD[1] = true;
        isMeowInCD[2] = true;
        isMeowInCD[3] = true;
        isMeowInCD[4] = true;
    }

    public void initmeowInCD(){
        meowInCD = new Image[5];
        try {
            meowInCD[0] = t.getImage(getClass().getResource("/meowBar/meowINCD/Bucket.png"));
            meowInCD[1] = t.getImage(getClass().getResource("/meowBar/meowINCD/Meow.png"));
            meowInCD[2] = t.getImage(getClass().getResource("/meowBar/meowINCD/Tray.png"));
            meowInCD[3] = t.getImage(getClass().getResource("/meowBar/meowINCD/Icecat.png"));
            meowInCD[4] = t.getImage(getClass().getResource("/meowBar/meowINCD/Food.png"));
        } catch (Exception e){

        }
    }

    public void drawMeowInCD(Graphics g){
        int distance = 0;
        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0;i<5;i++){
            if(isMeowInCD[i]){
                g.drawImage(meowInCD[i], 95 + distance, 12, 100, 74, null);
                int cd = (meowCD[i]+59)/60;
                g2d.setColor(Color.YELLOW);
                g2d.setFont(new Font("Arial",Font.BOLD,30));
                g2d.drawString(String.format("%d",cd),95 + 86*4/5 + distance,13 +86/2 - 4);
            }
            distance += 100;
        }
    }


    public MyButtons[] getPickMeow(){
        return pickMeow;
    }

    public void draw(Graphics g){
        drawMeowbar(g);
        drawMeowInCD(g);
    }
}
