package org.game.Zom;

public class helmetZombie extends Zombie {
    public helmetZombie(int HP, double speed, int attackPower) {
        super(HP, speed, attackPower);
        //TODO Auto-generated constructor stub
    
        HP = 200; // Lower health than average
        speed = 0.5; // Average speed
        attackPower = 15; // Moderate attack power
    }

}
