package org.game.Zom;

public class catEarZombie extends Zombie {
    public catEarZombie(int HP, double speed, int attackPower) {
        super(HP, speed, attackPower);
        //TODO Auto-generated constructor stub
    
        HP = 150; // Lower health than average
        speed = 0.5; // Average speed
        attackPower = 15; // Moderate attack power
    }
}
