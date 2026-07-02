package model.enemy;

import model.Intention;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Enemy {
    private final String name;
    private int health;
    private int attackDamage;
    private final boolean isElite;
    private final Intention[] intentions;
    private Intention currentIntention;
    private final int defense;
    private int block;

    public Enemy() {
        this("Slime", 20, 5);
    }

    public Enemy(String name, int health, int attackDamage) {
        this(name, health, false, defaultIntentions(), null, attackDamage, 5);
    }

    public Enemy(String name,
                 int health,
                 boolean isElite,
                 Intention[] intentions,
                 Intention currentIntention,
                 int attackDamage,
                 int defense) {
        if (health <= 0) {
            throw new IllegalArgumentException("health must be positive");
        }
        if (attackDamage < 0) {
            throw new IllegalArgumentException("attack damage cannot be negative");
        }
        if (defense < 0) {
            throw new IllegalArgumentException("defense cannot be negative");
        }
        if (intentions == null || intentions.length == 0) {
            throw new IllegalArgumentException("enemy must have at least one intention");
        }
        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.health = health;
        this.attackDamage = attackDamage;
        this.isElite = isElite;
        this.intentions = Arrays.copyOf(intentions, intentions.length);
        this.defense = defense;
        this.block = 0;
        if (currentIntention != null
                && !Arrays.asList(this.intentions).contains(currentIntention)) {
            throw new IllegalArgumentException("current intention must be in intentions");
        }
        this.currentIntention = currentIntention;
        if (this.currentIntention == null) {
            nextIntention();
        }
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public boolean isElite() {
        return isElite;
    }

    public Intention[] getIntentions() {
        return Arrays.copyOf(intentions, intentions.length);
    }

    public Intention getCurrentIntention() {
        return currentIntention;
    }

    public int getDefense() {
        return defense;
    }

    public int getBlock() {
        return block;
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

    public boolean isDefeated() {
        return health == 0;
    }

    public void increaseDamage(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount cannot be negative");
        }

        attackDamage += amount;
    }

    public void defend() {
        block = defense;
    }

    public void clearBlock() {
        block = 0;
    }

    public Intention nextIntention() {
        int selectedIndex = ThreadLocalRandom.current().nextInt(intentions.length);
        currentIntention = intentions[selectedIndex];
        return currentIntention;
    }

    private static Intention[] defaultIntentions() {
        return new Intention[]{Intention.ATTACK, Intention.DEFEND};
    }
}
