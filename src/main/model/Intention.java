package model;

/*
 * Represents the Intention of the enemy (whether it will attack, defend, buff, or debuff)
 * OR the card Type (Attack Card, Defend Card, Skill Card)
 *
 */

public enum Intention {
    ATTACK, // ATTACK intention does X amount of damage by caster to target
    DEFEND, // DEFEND intention applies X BLOCK to caster
    BUFF,   // BUFF modifies buff multiplier by
    DEBUFF, // DEBUFF increases debuff multiplier thereby decreasing a targets Y stat type by X
    OTHER   // OTHER represents cards that have a different effect than modifying stats (e.g. skill cards)
}
