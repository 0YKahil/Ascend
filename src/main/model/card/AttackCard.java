package model.card;

/*
 * @filename: AttackCard.java
 * @author: Ykahil, YousifMuziel
 *
 * Representation of an Attack Card that deals damage
 */

import model.Intention;

public class AttackCard extends Card {

    // EFFECTS: constructs an Attack type card with given name, statAmount, cost, and description
    public AttackCard(String name, int statAmount, int cost, String description) {
        super(name, Intention.ATTACK, statAmount, cost, description);
    }

    // EFFECTS: constructs an Attack type card with given name, statAmount, cost, and blank description
    public AttackCard(String name, int statAmount, int cost) {
        super(name, Intention.ATTACK, statAmount, cost);
    }

}
