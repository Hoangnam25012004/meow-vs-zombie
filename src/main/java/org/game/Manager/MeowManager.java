package org.game.Manager;

import org.game.MeowPack.*;
import org.game.bullet.Bullet;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class MeowManager {
    Bullet bullet;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private static MeowManager instance = null;
    List<Meow> meows = new ArrayList<>();

    public static MeowManager getInstance()
    {
        if (instance == null)
            instance = new MeowManager();
        return instance;
    }

    private MeowManager()
    {

    }

    public void addMeow(Meow newMeow)
    {
        meows.add(newMeow);
    }

    public void removeMeow(Meow meow)
    {
        meows.remove(meow);
    }

    public List<Meow> getAllMeows()
    {
        return meows;
    }

    // Update all meows
    public void update()
    {
        for (Meow meow : meows)
        {
            meow.update();
        }
    }

    // Render all meows
    public void render(Graphics2D g2)
    {
        for (Meow meow : meows)
        {
            meow.render(g2);
        }
    }


}