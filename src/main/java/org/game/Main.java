package org.game;

import org.game.graphic.Graphical;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setResizable(true);
        window.setTitle("Meow vs zombie");

        Graphical graphical = new Graphical();
        window.add(graphical);
        window.pack();
        graphical.startGameThread();

        window.setLocationRelativeTo(null);
        window.setVisible(true);


    }

}
