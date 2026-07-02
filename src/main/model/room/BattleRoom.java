package model.room;

import model.player.Player;

/*
 * @filename: Room.java
 * @author: YousifMuziel, Ykahil
 *
 * Abstract representation of the Room class object with its abstract methods
 */
public class BattleRoom extends Room {
    public BattleRoom(int difficulty) {
        super("Battle", difficulty, false);
    }

    @Override
    public void enterRoom(Player player) {
        onEnter();
    }
}
