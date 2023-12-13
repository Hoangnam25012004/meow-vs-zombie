package org.game;

import java.util.ArrayList;

 public class User {

    private String username;
    private int level;
    private int coins;
    private ArrayList<Meow> equippedMeow;

    public User(String username) {
        this.username = username;
        this.level = 1;
        this.coins = 100;
        this.equippedMeow = new ArrayList<>();

        // Initialize starting plant inventory
        unlockedPlants.add(new Sunflower());
        unlockedPlants.add(new Peashooter());
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

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public ArrayList<Meow> getEquippedMeow() {
        return equippedMeow;
    }

    public void equipPlant(Plant plant) {
        if (unlockedPlants.contains(plant) && !equippedPlants.contains(plant)) {
            equippedPlants.add(plant);
        }
    }

    public void unequipPlant(Plant plant) {
        if (equippedPlants.contains(plant)) {
            equippedPlants.remove(plant);
        }
    }
}

