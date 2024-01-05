package org.game.Manager;

import org.game.Scenes.Playing;
import org.game.graphic.Graphical;

import java.awt.*;

public class MouseMotionManager {
    private Playing playing;
    private int plantPickedByMouse = 0;
    private int tileSelectedByMouse;

    public void setPlantPickedByMouse(int plantPickedByMouse) {
        this.plantPickedByMouse = plantPickedByMouse;
    }

    private boolean isMouseMoveForFirstTime = true;
    private boolean isControlledByMouse = false;
    public MouseMotionManager(Playing playing){
        this.playing = playing;
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
        for(int i = 0;i<playing.getTm().getTile().length;i++){
            Rectangle r = new Rectangle((int)playing.getTm().getTile()[i].getBound().getX(),(int)playing.getTm().getTile()[i].getBound().getY(),playing.getTm().getTile()[i].getGrassTile() ,playing.getTm().getTile()[i].getGrassTile());
            if(r.contains(x,y)){
                playing.getTm().setInTile(true);
                tileSelectedByMouse = i;
                //playing.getKeyBoardManager().setTileSelectedByKeyBoard(tileSelectedByMouse);
            }
        }
    }
}
