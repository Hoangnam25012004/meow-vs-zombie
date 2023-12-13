package org.game;

public class Zombie {
    
        private int HP;
        private int speed;
        private int attackPower;
        private int x;
        private int y;

        public Zombie(int HP, int speed, int attackPower) {
            this.HP = HP;
            this.speed = speed;
            this.attackPower = attackPower; 
        }
        
        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }

        public void setPosition(int x, int y){
            this.x = x;
            this.y = y;
        }
    
        public void act() {
            move(speed);
            //turnTowardsPlayer();
            //attackPlayer();
            //checkHealth();
        }
    
        private void move(int speed) {
            setLocation(getX() - speed, getY());
        }
    
        

        private void setLocation(int i, int y2) {
        }

        // private void checkHealth() {
        //     if (HP <= 0) {
        //         getWorld().removeObject(this);
        //     }
        // }
    
    }
