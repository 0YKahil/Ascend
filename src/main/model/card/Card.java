package model.card;

/*
 * @filename: Card.java
 * @author: Ykahil, YousifMuziel
 *
 * Abstract representation of a Card object with its playable abstract methods and effects
 */

import exceptions.IllegalCostException;
import exceptions.IllegalMultiplierException;
import model.Intention;

public abstract class Card {
    private String name;
    private Intention type;
    private int statAmount; // represents the "strength" of the card, e.g. 3 attack, 4 block, buff attack by 3, etc.
    private int cost;
    private String description;
    private double statMultiplier; // (default 1) modify statAmount by statAmount * statMultiplier, needs to be decimal

    public Card() {
        this.name = "CARD_NAME";
        this.type = null;
        this.statAmount = 0;
        this.cost = 0;
        this.description = "";
        this.statMultiplier = 1.0;
    }

    public Card(String name, Intention type, int statAmount, int cost) {
        this.name = name;
        this.type = type;
        this.statAmount = statAmount;
        this.cost = cost;
        this.description = "";
        this.statMultiplier = 1.0;
    }

    public Card(String name, Intention type, int statAmount, int cost, String description) {
        this.name = name;
        this.type = type;
        this.statAmount = statAmount;
        this.cost = cost;
        this.description = description;
        this.statMultiplier = 1.0;
    }

    /*
    * GETTERS
    */

    public String getName() {
        return this.name;
    }

    public Intention getType() {
        return this.type;
    }

    public int getStatAmount() {
        return (int) Math.ceil(this.statAmount * statMultiplier);
    }

    public int getBaseStatAmount() {
        return this.statAmount;
    }

    public int getCost() {
        return this.cost;
    }

    public String getDescription() {
        return this.description;
    }

    public double getStatMultiplier() {
        return this.statMultiplier;
    }

    /*
     * SETTERS
     */

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Intention type) {
        this.type = type;
    }

    public void setStatAmount(int amount) {
        this.statAmount = amount;
    }

    public void setCost(int cost) throws IllegalCostException {
        if (cost < 0) {
            throw new IllegalCostException();
        } else {
            this.cost = cost;
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }


    // EFFECTS: if multiplier < 0, throws IllegalMultiplierException
    //          otherwise, sets the card's buffMultiplier to given multiplier
    public void setStatMultiplier(double multiplier) throws IllegalMultiplierException {
        if (multiplier < 0) {
            throw new IllegalMultiplierException();
        } else {
            this.statMultiplier = multiplier;
        }
    }


}
