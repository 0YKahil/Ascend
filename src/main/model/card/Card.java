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

    // EFFECTS: Constructs a new card without any givens (null type and placeholders)
    public Card() {
        this.name = "CARD_NAME";
        this.type = null;
        this.statAmount = 0;
        this.cost = 0;
        this.description = "";
        this.statMultiplier = 1.0;
    }

    // EFFECTS: Constructs a new card object with given name, intent, cost, stat amount, and empty description
    public Card(String name, Intention type, int statAmount, int cost) {
        this.name = name;
        this.type = type;
        this.statAmount = statAmount;
        this.cost = cost;
        this.description = "";
        this.statMultiplier = 1.0;
    }

    // EFFECTS: Constructs a new card object with givens for all fields (including description) except buffMultiplier
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

    // EFFECTS: returns the name of the card
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns the type of the card
    public Intention getType() {
        return this.type;
    }

    // EFFECTS: returns the stat amount of the card considering statMultiplier
    public int getStatAmount() {
        return (int) Math.ceil(this.statAmount * statMultiplier);
    }


    // EFFECTS: returns the base stat amount of the card
    public int getBaseStatAmount() {
        return this.statAmount;
    }

    // EFFECTS: returns the energy cost of the card
    public int getCost() {
        return this.cost;
    }

    // EFFECTS: returns the description of the card
    public String getDescription() {
        return this.description;
    }

    // EFFECTS: returns the stat multiplier of the card
    public double getStatMultiplier() {
        return this.statMultiplier;
    }

    /*
     * SETTERS
     */

    // MODIFIES: this
    // EFFECTS: sets the card's name to given name
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: sets the card's type to given type
    public void setType(Intention type) {
        this.type = type;
    }

    // MODIFIES: this
    // EFFECTS: sets the card's attributeAmount to given amount
    public void setStatAmount(int amount) {
        this.statAmount = amount;
    }

    // MODIFIES: this
    // EFFECTS: if cost < 0, throws IllegalCostException
    //          otherwise, sets the card's cost to given cost
    public void setCost(int cost) throws IllegalCostException {
        if (cost < 0) {
            throw new IllegalCostException();
        } else {
            this.cost = cost;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the card's description to given description
    public void setDescription(String description) {
        this.description = description;
    }

    // MODIFIES: this
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
