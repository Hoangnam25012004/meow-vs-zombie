package org.game.Manager;

import org.game.MeowPack.Meow;
import org.game.Component.Tile;
import org.game.Scenes.Playing;
import org.game.Zombie.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MeowManager {
    private final Image[] fish = new Image[27];
    private final Image[] meow_idle = new Image[59];
    private final Image[] meow_attack = new Image[59];
    private final Image[] stinkyPate = new Image[82];
    private final Image[] iceMeow_Idle = new Image[23];
    private final Image[] iceMeow_Attack = new Image[21];
    private final Image[] pateBomb = new Image[49];
    private Toolkit t = Toolkit.getDefaultToolkit();
    private List<Meow> meowList = new ArrayList<>();
    private boolean isTimeToMeow = false;
    private Playing playing;
    private boolean selected = true;
    private boolean isMeowed = false;
    private boolean isForbidden = false;
//    private boolean isShoveled = false;
    private int IDhold;
    private int fishCostHold;
    private static MeowManager instance;
    private int[] numMeow = new int[5];
    private int waitingTime = 0;
    private int meowSize = 49;
    private int listAdded = 0;

    public void setNumMeow(){
        numMeow[0] = 0;
        numMeow[1] = 1;
        numMeow[2] = 2;
        numMeow[3] = 3;
        numMeow[4] = 4;
    }

    private MeowManager(Playing playing) {
        this.playing = playing;
        importPateBomb();
        importMeow();
        importFish();
        importStinkyPate();
        importIceMeow();
        meowForbiddenFromStart();
        initStorage();
        setNumMeow();
    }

    public void setFishCostHold(int sunCostHold) {
        this.fishCostHold = sunCostHold;
    }
    public static MeowManager createMeowManager(Playing playing) {
        if(instance == null) {
            instance = new MeowManager(playing);
        } else {
            System.out.println("Cannot create another PlantManager");
        }
        return instance;
    }

    public void shiftMeow(Tile tile){
        for(int i = 0;i<meowSize;i++){
            if(meowList.get(i).getID() == IDhold && i == numMeow[IDhold]){
                Rectangle r = tile.getBound();
                meowList.get(i).setX(r.x);
                meowList.get(i).setY(r.y);
                meowList.get(i).setWidth(r.width);
                meowList.get(i).setHeight(r.height);
                meowList.get(i).setAlive(true);
                playing.getBarManager().setIsMeowInCD(meowList.get(i).getID(),true);
                numMeow[IDhold] = numMeow[IDhold] +5;
                break;
            }
        }
        reCreateStorage();
    }

    public void reCreateStorage(){
        if(meowSize <= 0 || numMeow[IDhold]>44+listAdded){
            initStorage();
            meowSize += 50;
            listAdded += 50;
        }
    }

    public void initStorage(){
        for(int i = 0; i<10;i++){
            meowList.add(new Meow(100,0,0,25,0,0,-999,60,70));
            meowList.add(new Meow(100,1,20,58,59,0,-999,60,70));
            meowList.add(new Meow(1000,2,0,81,0,0,-999,60,70));
            meowList.add(new Meow(100,3,20,20,20,0,-999,60,70));
            meowList.add(new Meow(10000,4,1000,30,0,0,-999,60,70));
        }
    }

    public void setIDhold(int IDhold) {
        this.IDhold = IDhold;
    }
    public boolean isTimeToMeow() {
        return isTimeToMeow;
    }

    public void setTimeToMeow(boolean timeToMeow) {
        isTimeToMeow = timeToMeow;
    }
    public boolean isSelected() {
        return selected;
    }

    public void importFish(){
        for(int i = 0;i<fish.length;i++){
            fish[i] = t.getImage(getClass().getResource("/Fish/fish.png"));
        }
    }

    public void importMeow(){
        for (int i = 0;i<meow_idle.length;i++){
            meow_idle[i] = t.getImage(getClass().getResource("/peaShooter - idle/PeaShooter_Idle1-"+i+".png"));
        }
        for(int i = 0;i <= meow_attack.length;i++){
            meow_attack[i] = t.getImage(getClass().getResource("/Meow - attack/meow_-"+i+".png"));
        }
    }

    public void importStinkyPate(){
        for(int i = 0;i<stinkyPate.length;i++){
            stinkyPate[i] = t.getImage(getClass().getResource("/StinkyPate/stinkyPate_animation-"+i+".png"));
        }
    }
    public void importIceMeow(){
        for(int i = 0;i<iceMeow_Idle.length;i++){
            iceMeow_Idle[i] = t.getImage(getClass().getResource("/IceMeow - idle/iceMeow_idle-"+i+".png"));
        }
        for(int i = 0;i < iceMeow_Attack.length;i++){
            iceMeow_Attack[i] = t.getImage(getClass().getResource("/IceMeow - attack/iceMeow_attack-"+i+".png"));
        }
    }

    public void importPateBomb(){
        try {
            for(int i = 0;i<pateBomb.length;i++){
                pateBomb[i] = t.getImage(getClass().getResource("/PateBomb/pateBomb-"+i+".png"));
            }
        } catch (Exception e){

        }
    }

    public void drawPlantAttackTest(Graphics g, Meow m){
        if(m.getID() == 1 && waitingTime <240){
            g.drawImage(meow_attack[m.getFrameCountAttack()], m.getX(), m.getY(), m.getWidth(), m.getHeight(), null);
        } else if(m.getID() == 3 && waitingTime<240){
            g.drawImage(iceMeow_Attack[m.getFrameCountAttack()], m.getX()-10, m.getY()-30, m.getWidth()+30, m.getHeight()+30, null);
        }
    }

    public void drawPlant(Graphics g){
        Iterator<Meow> iterator = meowList.iterator();
        while (iterator.hasNext()){
            Meow m = iterator.next();
            if(m.isAlive() || waitingTime<240){
                if (m.getID() == 0){
                    g.drawImage(fish[m.getFrameCountIdle()], m.getX(), m.getY(), m.getWidth(), m.getHeight(), null);
                } else if (m.getID() == 1){
                    if(!m.isDangered()){
                        g.drawImage(meow_idle[m.getFrameCountIdle()], m.getX(), m.getY(), m.getWidth(), m.getHeight(), null);
                    } else {
                        g.drawImage(meow_attack[m.getFrameCountAttack()], m.getX(), m.getY(), m.getWidth(), m.getHeight(), null);
                    }
                } else if (m.getID() == 2){
                    g.drawImage(stinkyPate[m.getFrameCountIdle()], m.getX(), m.getY(), m.getWidth(), m.getHeight(), null);
                } else if (m.getID() == 3){
                    if(!m.isDangered()){
                        g.drawImage(iceMeow_Idle[m.getFrameCountIdle()], m.getX()-10, m.getY()-30, m.getWidth()+30, m.getHeight()+30, null);
                    } else {
                        g.drawImage(iceMeow_Attack[m.getFrameCountAttack()], m.getX()-10, m.getY()-30, m.getWidth()+30, m.getHeight()+30, null);
                    }
                } else if (m.getID() == 4){
                    g.drawImage(pateBomb[m.getFrameCountIdle()],m.getX(), m.getY(), m.getWidth(), m.getHeight(), null);
                }
                drawPlantAttackTest(g,m);
            }
        }
    }

    public void update(){
        Iterator<Meow> iterator = meowList.iterator();
        while (iterator.hasNext()){
            Meow meow = iterator.next();
            if(meow.isAlive() || waitingTime<240){
                meow.updateFrameCountIdle();
                if(meow.getID() == 1 || meow.getID() == 3){
                    meow.updateFrameCountAttack();
                }
            }
        }
        waitingTime++;
        alertMeow();
        calmMeow();
        updateFish();
        timeExplode();
        meowAttack();
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getIDhold() {
        return IDhold;
    }

    public boolean isForbidden() {
        return isForbidden;
    }

    public void setForbidden(boolean forbidden) {
        isForbidden = forbidden;
    }

    public void meowForbiddenFromStart(){
        IDhold = 0;
        playing.getBarManager().setMeowLocked(false);
        isForbidden = true;
    }

    public void meowOnTile(Tile tile, int x, int y,int i){
        if(!tile.isOccupied()){
            Rectangle r = new Rectangle((int)tile.getBound().getX(), (int)tile.getBound().getY(), (int)tile.getBound().getWidth(), (int)tile.getBound().getHeight());
            if (r.contains(x, y)){
                if(playing.getFishManager().getFishHold() >= fishCostHold){
                    tile.setOccupied(true);
                    shiftMeow(tile);
                    for (int j = 0; j < meowList.size(); j++){
                        meowList.get(meowList.size() - 1).setTileHold(i);
                        if(!tile.isPlanted()){
                            tile.setPlanted(true);
                        }
                    }
                    isMeowed = true;
                    playing.getFishManager().fishConsumed(fishCostHold);
                }
            }
        }
    }

    public void mouse(int x, int y){
        if(!isForbidden){
            if(selected && !playing.getBarManager().getIsMeowInCD()[playing.getBarManager().getMeowPickedID().get(playing.getBarManager().getMeowPickedID().size()-1)]){
                for (int i = 0; i < playing.getTileManager().getTiles().length; i++){
                    meowOnTile(playing.getTileManager().getTiles()[i],x,y,i);
                }
            }
        }
    }

    public void setMeowDangered(Tile tile){
        Rectangle rPlant = tile.getBound();
        for(Meow meow:meowList){
            if(rPlant.contains(meow.getX(),meow.getY())){
                meow.setDangered(true);
            }
        }
    }

    public void setMeowIdle(Tile tile){
        Rectangle rMeow = tile.getBound();
        Iterator<Meow> iterator = meowList.iterator();
        while (iterator.hasNext()){
            Meow meow = iterator.next();
            if(rMeow.contains(meow.getX(),meow.getY())){
                if(meow.isDangered()){
                    meow.setDangered(false);
                    meow.setFrameCountAttack(0);
                }
            }
        }
    }

    public List<Meow> getPlantList() {
        return meowList;
    }

    public void alertMeow(){
        for(int i = 0;i<playing.getTileManager().getTiles().length;i++){
            Rectangle r = playing.getTileManager().getTiles()[i].getBound();
            Iterator<Zombie> iterator = playing.getZombieManager().getZombies().iterator();
            while (iterator.hasNext()){
                Zombie zombie = iterator.next();
                if(r.contains(zombie.X()+50,zombie.Y()+70) && zombie.isAlived()){
                    if(i>=0 && i<9){
                        for(int j = 0;j < 9;j++){
                            setMeowDangered(playing.getTileManager().getTiles()[j]);
                        }
                    } else if(i >= 9 && i<18){
                        for(int j = 9;j < 18;j++){
                            setMeowDangered(playing.getTileManager().getTiles()[j]);
                        }
                    } else if(i>=18 && i<27){
                        for(int j = 18;j < 27;j++){
                            setMeowDangered(playing.getTileManager().getTiles()[j]);
                        }
                    } else if(i>=27 && i<36){
                        for(int j = 27;j < 36;j++){
                            setMeowDangered(playing.getTileManager().getTiles()[j]);
                        }
                    } else if(i >= 36 && i<45) {
                        for(int j = 36;j < 45;j++){
                            setMeowDangered(playing.getTileManager().getTiles()[j]);
                        }
                    }
                }
            }
        }
    }

    public int isMeowAttack(int start, int end, TileManager tileManager, ZombieManager zombieManager){
        int tileStart;
        for(int i = start;i<end;i++){
            Rectangle r = tileManager.getTiles()[i].getBound();
            Iterator<Zombie> iterator = zombieManager.getZombies().iterator();
            while (iterator.hasNext()){
                Zombie zombie = iterator.next();
                Rectangle rZombie = new Rectangle((int)zombie.X(),(int)zombie.Y()+70,zombie.getWidth(),zombie.getHeight()-70);
                if(r.intersects(rZombie) && zombie.isAlived()){
                    tileStart = i;
                    return tileStart;
                }
            }
        }
        return start;
    }

    public void calmMeow(){
        for (int i = isMeowAttack(0,9,playing.getTileManager(),playing.getZombieManager()); i < 9; i++) {
            setMeowIdle(playing.getTileManager().getTiles()[i]);
        }

        for (int i = isMeowAttack(9,18,playing.getTileManager(),playing.getZombieManager()); i < 18; i++) {
            setMeowIdle(playing.getTileManager().getTiles()[i]);
        }

        for (int i = isMeowAttack(18,27,playing.getTileManager(),playing.getZombieManager()); i < 27; i++) {
            setMeowIdle(playing.getTileManager().getTiles()[i]);
        }

        for (int i = isMeowAttack(27,36,playing.getTileManager(),playing.getZombieManager()); i < 36; i++) {
            setMeowIdle(playing.getTileManager().getTiles()[i]);
        }

        for (int i = isMeowAttack(36,45,playing.getTileManager(),playing.getZombieManager()); i < 45; i++) {
            setMeowIdle(playing.getTileManager().getTiles()[i]);
        }
    }

    public void meowAttack(){
        for(Meow meow:meowList){
            if(meow.isAlive()){
                if(meow.isDangered()){
                    if(meow.getID() == 1){
                        if(meow.getFrameCountAttack() == 31){
//                            playing.getProjectileOfPlant().projectileCreated((Shooter) plant);
                            meow.setFrameCountAttack(meow.getFrameCountAttack()+1);
                        }
                    } else if(meow.getID() == 3){
                        if(meow.getFrameCountAttack() == 8){
//                            playing.getProjectileOfPlant().projectileCreated((Shooter) plant);
                            meow.setFrameCountAttack(meow.getFrameCountAttack()+1);
                        }
                    }
                }
            }
        }
    }

    private double explosionX;
    private double explosionY;
    private double explosionWidth;
    private double explosionHeight;
    private boolean isExploded = false;
    private int explosionTime = 0;
    public void pateExplode(int x, int y){
        Rectangle explodeRange = new Rectangle(x-120,y-130,300,330);
        explosionX = explodeRange.getX();
        explosionY = explodeRange.getY();
        explosionWidth = explodeRange.getWidth();
        explosionHeight = explodeRange.getHeight();
        synchronized (playing.getZombieManager().getZombies()){
            Iterator<Zombie> iterator = playing.getZombieManager().getZombies().iterator();
            while (iterator.hasNext()){
                Zombie zombie = iterator.next();
                if(explodeRange.contains(zombie.getBound().getX(),zombie.getBound().getY()+70)){
                    zombie.dead();
                }
            }
        }
        isExploded = true;
    }

    public void timeExplode(){
        Iterator<Meow> iterator = meowList.iterator();
        while (iterator.hasNext()){
            Meow meow = iterator.next();
            if(meow.isAlive()){
                if(meow.getID() == 4){
                    if(meow.getFrameCountIdle() == 29){
                        meow.setHealthPoint(0);
                        pateExplode(meow.getX(),meow.getY());
                        meow.removeMeow(meow,iterator,playing.getTileManager(),this);
                        meow.setFrameCountIdle(0);
                        meow.setFrameCDIdle(0);
                    }
                }
            }
        }
    }


    public void drawExplosion(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        Image Explosion = t.getImage(getClass().getResource("/Event/Powie.png"));
        explosionTime++;
        if(explosionTime<60 && isExploded){
            g2d.drawImage(Explosion,(int)explosionX,(int)explosionY,(int)explosionWidth,(int)explosionHeight,null);
        } else {
            isExploded = false;
            explosionTime = 0;
        }
    }

    public void draw(Graphics g){
        drawPlant(g);
        drawExplosion(g);
    }

}
