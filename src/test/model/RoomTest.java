package model;

/*
 * @filename: Room.java
 * @author: YousifMuziel, Ykahil
 *
 * Abstract representation of the Room class object with its abstract methods
 */

import model.player.Player;
import model.enemy.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    private Room room;
    private Player player;
    private Enemy enemy;

    @BeforeEach
    void setUp() {
        // Initialize a concrete Room class, which in this case is a mock implementation for testing.
        room = new Room("TestRoom", 1, false) {
            @Override
            public void enterRoom(Player player) {
            }
        };

        player = new Player("TestPlayer", 1, 100, 10); // Assuming parameters for Player
        enemy = new Enemy("TestEnemy", 50, false, 1); // Assuming parameters for Enemy
        room.addEnemy(enemy); // Adding an enemy to the room for the test
    }

    @Test
    void testRoomType() {
        assertEquals("TestRoom", room.getType());
    }

    @Test
    void testRoomDifficulty() {
        assertEquals(1, room.getDifficulty());
    }

    @Test
    void testIsElite() {
        assertFalse(room.isElite()); //
    }

    @Test
    void testAddEnemy() {
        assertEquals(1, room.getEnemies().size()); // Expecting one enemy
        assertTrue(room.getEnemies().contains(enemy));
    }

}
