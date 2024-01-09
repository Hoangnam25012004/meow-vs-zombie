package org.game.Manager;

import org.game.Component.MyButtons;

public class BarManager {
    public boolean isPlantLocked = true;
    private MyButtons pickPlant[];
    public BarManager(){}
    public void setPlantLocked(boolean plantLocked) {
        isPlantLocked = plantLocked;
    }

    public MyButtons[] getPickPlant(){
        return pickPlant;
    }
}
