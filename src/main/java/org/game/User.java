package org.game;

import java.util.ArrayList;

 public class User {

    private String username;
    private int level;
    private int fish;
    private ArrayList<Meow> unlockedMeow;
    

    public User(String username) {
        this.username = username;
        this.level = 1;
        this.fish = 100;
        this.unlockedMeow = new ArrayList<>();

        // Initialize starting plant inventory
        unlockedMeow.add(new FishBucket());
        //unlockedMeow.add(new Meow());
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getFish() {
        return fish;
    }

    public void setFish(int fish) {
        this.fish = fish;
    }

    public ArrayList<Meow> unlockedMeow() {
        return unlockedMeow;
    }
    
}