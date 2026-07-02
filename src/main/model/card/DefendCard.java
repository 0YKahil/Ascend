package model.card;

import model.Intention;

/*
 * @filename: DefendCard.java
 * @author: Ykahil, YousifMuziel
 *
 * Representation of a Defend Card responsible for block value of Player
 */

public class DefendCard extends Card {
    public DefendCard(String name, int statAmount, int cost, String description) {
        super(name, Intention.DEFEND, statAmount, cost, description);
    }

    public DefendCard(String name, int statAmount, int cost) {
        super(name, Intention.DEFEND, statAmount, cost);
    }
}
