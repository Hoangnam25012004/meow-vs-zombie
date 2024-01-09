package org.game;

import org.game.Manager.World;
import javax.swing.*;

public class Main {
    public static void main(String[] args){
//        JFrame window = new JFrame();
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setResizable(false);
//        window.setTitle("Meow vs zombie");
//
//        Graphical graphical = new Graphical();
//        window.add(graphical);
//        window.pack();
//        graphical.startGameThread();
//
//        window.setLocationRelativeTo(null);
//        window.setVisible(true);
        JFrame frame = new JFrame();
        frame.add(new World());
        frame.setTitle("Meow vs Zombies");
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }

}
