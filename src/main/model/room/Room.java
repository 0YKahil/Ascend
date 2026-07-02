package model.room;

import model.enemy.Enemy;
import model.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Base class for every room in the tower.
 *
 * <p>A room owns its enemy list. Subclasses can populate the room by calling
 * {@link #addEnemy(Enemy)} after the {@code Room} constructor has completed.</p>
 */
public abstract class Room {
    private String type;
    private int difficulty;
    private boolean elite;
    private final List<Enemy> enemies;

    protected Room(String type, int difficulty, boolean elite) {
        this.type = Objects.requireNonNull(type, "type cannot be null");
        setDifficulty(difficulty);
        this.elite = elite;
        this.enemies = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = Objects.requireNonNull(type, "type cannot be null");
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        if (difficulty < 1) {
            throw new IllegalArgumentException("difficulty must be at least 1");
        }
        this.difficulty = difficulty;
    }

    public boolean isElite() {
        return elite;
    }

    public void setElite(boolean elite) {
        this.elite = elite;
    }

    /**
     * Returns a read-only view of the enemies currently in this room.
     */
    public List<Enemy> getEnemies() {
        return Collections.unmodifiableList(enemies);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(Objects.requireNonNull(enemy, "enemy cannot be null"));
    }

    public boolean removeEnemy(Enemy enemy) {
        return enemies.remove(enemy);
    }

    public boolean hasEnemies() {
        return !enemies.isEmpty();
    }

    public void clearEnemies() {
        enemies.clear();
    }

    public abstract void enterRoom(Player player);

    public void displayRoomDetails() {
        System.out.println("Room type: " + type);
        System.out.println("Difficulty level: " + difficulty);
        System.out.println("Is elite: " + elite);
        System.out.println("Enemies present: " + enemies.size());
    }

    public void onEnter() {
        // Optional hook for subclasses.
    }

    public void onExit() {
        // Optional hook for subclasses.
    }
}
