package model.card;
/*
 * @filename: Card.java
 * @author: Ykahil
 *
 * Abstract representation of a Card object with its playable abstract methods and effects
 */

import exceptions.IllegalCostException;
import exceptions.IllegalMultiplierException;
import model.Intention;

public abstract class Card {
    String name;
    Intention type;
    int statAmount; // represents the "strength" of the card, e.g. 3 attack, 4 block, buff attack by 3, etc.
    int cost;
    String description;
    double statMultiplier; // (default to 1) modifies statAmount by statAmount * statMultiplier, needs to be decimal

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
    String getName() {
        return this.name;
    }

    // EFFECTS: returns the type of the card
    Intention getType() {
        return this.type;
    }

    // EFFECTS: returns the stat amount of the card considering statMultiplier
    int getStatAmount() {
        return (int) Math.ceil(this.statAmount * statMultiplier);
    }


    // EFFECTS: returns the base stat amount of the card
    int getBaseStatAmount() {
        return this.statAmount;
    }

    // EFFECTS: returns the energy cost of the card
    int getCost() {
        return this.cost;
    }

    // EFFECTS: returns the description of the card
    String getDescription() {
        return this.description;
    }

    // EFFECTS: returns the stat multiplier of the card
    double getStatMultiplier() {
        return this.statMultiplier;
    }

    /*
     * SETTERS
     */

    // MODIFIES: this
    // EFFECTS: sets the card's name to given name
    void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: sets the card's type to given type
    void setType(Intention type) {
        this.type = type;
    }

    // MODIFIES: this
    // EFFECTS: sets the card's attributeAmount to given amount
    void setStatAmount(int amount) {
        this.statAmount = amount;
    }

    // MODIFIES: this
    // EFFECTS: if cost < 0, throws IllegalCostException
    //          otherwise, sets the card's cost to given cost
    void setCost(int cost) throws IllegalCostException {
        if (cost < 0) {
            throw new IllegalCostException();
        } else {
            this.cost = cost;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the card's description to given description
    void setDescription(String description) {
        this.description = description;
    }

    // MODIFIES: this
    // EFFECTS: if multiplier < 0, throws IllegalMultiplierException
    //          otherwise, sets the card's buffMultiplier to given multiplier
    void setStatMultiplier(double multiplier) throws IllegalMultiplierException {
        if (multiplier < 0) {
            throw new IllegalMultiplierException();
        } else {
            this.statMultiplier = multiplier;
        }
    }


}
