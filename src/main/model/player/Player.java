package model.player;

import model.card.Card;

import java.util.ArrayList;

public class Player {
    private String name;
    private Integer level;
    private Integer health;
    private Integer energy;
    private final int maxEnergy;
    private int block;
    ArrayList<Card> deck;


    public Player(String name, Integer level, Integer health, Integer energy, ArrayList<Card> deck) {
        this.name = name;
        this.level = level;
        this.health = health;
        this.energy = energy;
        this.maxEnergy = energy;
        this.block = 0;
        this.deck = deck;
    }

    public Player(String name, ArrayList<Card> deck) {
        this.name = name;
        this.deck = deck;
        this.level = 0;
        this.health = 100;
        this.energy = 3;
        this.maxEnergy = 3;
        this.block = 0;
    }


    public void addEnergy(Integer amountToAdd) {
        energy = energy + amountToAdd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public int getBlock() {
        return block;
    }

    public void addBlock(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("block amount cannot be negative");
        }
        block += amount;
    }

    public void clearBlock() {
        block = 0;
    }

    public int takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("damage cannot be negative");
        }

        int blockedDamage = Math.min(block, damage);
        block -= blockedDamage;
        int healthBeforeDamage = health;
        health = Math.max(0, health - (damage - blockedDamage));
        return healthBeforeDamage - health;
    }

    public Integer getEnergy() {
        return energy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void resetEnergy() {
        energy = maxEnergy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }
}
