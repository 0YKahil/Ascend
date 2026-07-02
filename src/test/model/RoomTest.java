package model;

import model.enemy.Enemy;
import model.player.Player;
import model.room.BattleRoom;
import model.room.ChestRoom;
import model.room.EliteRoom;
import model.room.RestRoom;
import model.room.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RoomTest {
    private Room room;
    private Enemy enemy;

    @BeforeEach
    void setUp() {
        room = new Room("Test Room", 1, false) {
            @Override
            public void enterRoom(Player player) {
                onEnter();
            }
        };
        enemy = new Enemy();
    }

    @Test
    void constructorSetsRoomProperties() {
        assertEquals("Test Room", room.getType());
        assertEquals(1, room.getDifficulty());
        assertFalse(room.isElite());
        assertFalse(room.hasEnemies());
    }

    @Test
    void addEnemyAddsEnemyToRoom() {
        room.addEnemy(enemy);

        assertTrue(room.hasEnemies());
        assertEquals(1, room.getEnemies().size());
        assertTrue(room.getEnemies().contains(enemy));
    }

    @Test
    void addEnemyRejectsNull() {
        assertThrows(NullPointerException.class, () -> room.addEnemy(null));
    }

    @Test
    void removeEnemyRemovesExistingEnemy() {
        room.addEnemy(enemy);

        assertTrue(room.removeEnemy(enemy));
        assertFalse(room.hasEnemies());
    }

    @Test
    void removeEnemyReturnsFalseWhenEnemyIsNotPresent() {
        assertFalse(room.removeEnemy(enemy));
    }

    @Test
    void clearEnemiesRemovesEveryEnemy() {
        room.addEnemy(enemy);
        room.addEnemy(new Enemy());

        room.clearEnemies();

        assertTrue(room.getEnemies().isEmpty());
    }

    @Test
    void enemyListCannotBeModifiedDirectly() {
        assertThrows(UnsupportedOperationException.class,
                () -> room.getEnemies().add(enemy));
    }

    @Test
    void difficultyMustBePositive() {
        assertThrows(IllegalArgumentException.class,
                () -> room.setDifficulty(0));
        assertThrows(IllegalArgumentException.class,
                () -> new BattleRoom(-1));
    }

    @Test
    void roomTypeCannotBeNull() {
        assertThrows(NullPointerException.class, () -> room.setType(null));
    }

    @Test
    void concreteRoomsHaveExpectedProperties() {
        Room battleRoom = new BattleRoom(2);
        Room eliteRoom = new EliteRoom(3);
        Room chestRoom = new ChestRoom(1);
        Room restRoom = new RestRoom(1);

        assertEquals("Battle", battleRoom.getType());
        assertFalse(battleRoom.isElite());
        assertEquals("Elite", eliteRoom.getType());
        assertTrue(eliteRoom.isElite());
        assertEquals("Chest", chestRoom.getType());
        assertEquals("Rest", restRoom.getType());
    }
}
