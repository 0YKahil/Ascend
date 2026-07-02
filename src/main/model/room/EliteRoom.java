package model.room;

import model.player.Player;

/*
 * @filename: Room.java
 * @author: YousifMuziel, Ykahil
 *
 * Abstract representation of the Room class object with its abstract methods
 */

public class EliteRoom extends Room {
    public EliteRoom(int difficulty) {
        super("Elite", difficulty, true);
    }

    @Override
    public void enterRoom(Player player) {
        onEnter();
    }
}
