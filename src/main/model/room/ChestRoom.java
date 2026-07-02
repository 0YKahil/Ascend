package model.room;

import model.player.Player;

/*
 * @filename: Room.java
 * @author: YousifMuziel, Ykahil
 *
 * Abstract representation of the Room class object with its abstract methods
 */

public class ChestRoom extends Room {
    public ChestRoom(int difficulty) {
        super("Chest", difficulty, false);
    }

    @Override
    public void enterRoom(Player player) {
        onEnter();
    }
}
