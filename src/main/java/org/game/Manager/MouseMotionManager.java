package org.game.Manager;

import org.game.Scenes.Playing;


import java.awt.*;

public class MouseMotionManager {
    private Playing playing;
    private int meowPickedByMouse = 0;
    private int tileSelectedByMouse;

    public void setmeowPickedByMouse(int meowPickedByMouse) {
        this.meowPickedByMouse = meowPickedByMouse;
    }

    private boolean isMouseMoveForFirstTime = true;
    private boolean isControlledByMouse = false;
    public MouseMotionManager(Playing playing){
        this.playing = playing;
    }
    public void returnToSelectPlantByMouse(){
        playing.getMeowManager().setSelected(false);
        playing.getMeowManager().setTimeToMeow(true);
        playing.getBarManager().setMeowLocked(false);
    }
    public void changeStatusToMouse(int x, int y, World w){
        Rectangle world = new Rectangle(w.getX(),w.getY(),w.getWidth(),w.getHeight());
        if(world.contains(x,y)){
            if(isMouseMoveForFirstTime){
                returnToSelectPlantByMouse();
                isMouseMoveForFirstTime = false;
            }
            isControlledByMouse = true;
        }
    }
    public void mouseTrackPlantBar(int x, int y){
        for(int i = 0;i<playing.getBarManager().getPickMeow().length;i++){
            Rectangle r = playing.getBarManager().getPickMeow()[i].getBounds();
            if(r.contains(x,y)){
                if(playing.getTileManager().isInTile()){
                    playing.getTileManager().setInTile(false);
                    playing.getMeowManager().setSelected(false);
                    playing.getBarManager().setMeowLocked(false);
                    playing.getMeowManager().setIsDog(false);
                }
                meowPickedByMouse = i;
                //playing.getKeyBoardManager().setPlantPickedByKeyBoard(meowPickedByMouse);
            }
        }
    }
    public void drawMeowSelectedByMouse(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(playing.getBarManager().getPickedMeow(), (int)playing.getBarManager().getPickMeow()[meowPickedByMouse].getBounds().getX(),(int)playing.getBarManager().getPickMeow()[meowPickedByMouse].getBounds().getY(),(int)playing.getBarManager().getPickMeow()[meowPickedByMouse].getBounds().getWidth(),(int)playing.getBarManager().getPickMeow()[meowPickedByMouse].getBounds().getHeight(),null);
    }
    public boolean getisControlledByMouse() {
        return isControlledByMouse;
    }

    public void setControlledByMouse(boolean controlledByMouse) {
        isControlledByMouse = controlledByMouse;
    }

    public int getTileSelectedByMouse() {
        return tileSelectedByMouse;
    }


    public void setTileSelectedByMouse(int tileSelectedByMouse) {
        this.tileSelectedByMouse = tileSelectedByMouse;
    }
    public void tileTrack(int x, int y){
        for(int i = 0;i<playing.getTileManager().getTiles().length;i++){
            Rectangle r = new Rectangle(
                    (int)playing.
                            getTileManager().
                            getTiles()[i].
                            getBound().
                            getX(),
                    (int)playing.getTileManager().
                            getTiles()[i].
                            getBound().getY(),
                    playing.getTileManager().
                            getTiles()[i].
                            getwTile(),
                    playing.
                            getTileManager().
                            getTiles()[i].
                            gethTile()
            );
            if(r.contains(x,y)){
                playing.getTileManager().setInTile(true);
                tileSelectedByMouse = i;
                //playing.getKeyBoardManager().setTileSelectedByKeyBoard(tileSelectedByMouse);
            }
        }
    }
}
