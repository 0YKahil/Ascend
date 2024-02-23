package model.room;

/*
 * @filename: Room.java
 * @author: YousifMuziel, Ykahil
 *
 * Abstract representation of the Room class object with its abstract methods
 */

import model.player.Player;
import model.enemy.Enemy;
import java.util.ArrayList;

// Room is an abstract class instances cant be created directly.
public abstract class Room {
    private String type; // Type of room
    private int difficulty; // Difficulty level of the room.
    private boolean isElite; // Indicating whether the room is an elite room.
    private ArrayList<Enemy> enemies; // Enemy being the defined class .

    // Constructor
    public Room(String type, int difficulty, boolean isElite) {
        this.type = type;
        this.difficulty = difficulty;
        this.isElite = isElite;
        this.enemies = new ArrayList<Enemy>(); // Initializing the list of enemies.
        initializeEnemies();
    }

    // Getters and setters for type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getters and setters for difficulty
    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    // Getters and setters for isElite
    public boolean isElite() {
        return isElite;
    }

    public void setElite(boolean elite) {
        isElite = elite;
    }

    // Getters and setters for enemies
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    // Method to add an enemy to the room
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    // Abstract method that can be implemented by subclasses.

    protected void initializeEnemies() {
        /*
         Initialize enemies based on room difficulty and elite status

         if (isElite) {
             enemies.add(new EliteEnemy(difficulty));
         } else {
             enemies.add(new NormalEnemy(difficulty));
         }
        */
    }

    public abstract void enterRoom(Player player); // Player class that will be defined.

    public void displayRoomDetails() {
        // Room details
        System.out.println("Room type: " + type);
        System.out.println("Difficulty level: " + difficulty);
        System.out.println("Is Elite: " + isElite);
        System.out.println("Enemies present: " + enemies.size());
        // Other details to be included
    }

    public void onEnter() {
        // Code to handle room entry
    }

    public void onExit() {
        // Code to handle room exit
    }
}







