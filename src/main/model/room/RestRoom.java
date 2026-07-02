package model.room;

import model.player.Player;

/*
 * @filename: Room.java
 * @author: YousifMuziel, Ykahil
 *
 * Abstract representation of the Room class object with its abstract methods
 */

public class RestRoom extends Room {
    public RestRoom(int difficulty) {
        super("Rest", difficulty, false);
    }

    @Override
    public void enterRoom(Player player) {
        onEnter();
    }
}
