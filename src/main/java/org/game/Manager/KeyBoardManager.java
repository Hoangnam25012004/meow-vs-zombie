package org.game.Manager;
import org.game.Scenes.Playing;

public class KeyBoardManager {
    private Playing playing;
    private int plantPickedByKeyBoard = 0;
    private boolean isKeyPressForTheFirstTime = true;
    private int tileSelectedByKeyBoard = 0;
    private static KeyBoardManager instance = null;
    private KeyBoardManager(Playing playing){
        this.playing = playing;
    }

    public int getTileSelectedByKeyBoard() {
        return tileSelectedByKeyBoard;
    }


}
