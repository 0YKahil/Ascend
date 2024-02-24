package model;

/*
 * @filename: CarTest.java
 * @author: Ykahil, YousifMuziel
 *
 * Tests for all Card Types
 */

import exceptions.IllegalCostException;
import exceptions.IllegalMultiplierException;
import model.card.AttackCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CardTest {
    private AttackCard ac;

    @BeforeEach
    void runBefore() {
        ac = new AttackCard("Strike", 6, 1);
        ac.setDescription("Deals " + ac.getStatAmount() + " damage");
    }

    @Test
    void testAttackConstructor() {
        assertEquals("Strike", ac.getName());
        assertEquals(6, ac.getStatAmount());
        assertEquals(6, ac.getBaseStatAmount());
        assertEquals("Deals 6 damage", ac.getDescription());
        assertEquals(1, ac.getStatMultiplier());
        assertEquals(Intention.ATTACK, ac.getType());
        assertEquals(1, ac.getCost());
    }

    @Test
    void testSetName() {
        assertEquals("Strike", ac.getName());
        ac.setName("HeadButt");
        assertEquals("HeadButt", ac.getName());
    }

    @Test
    void testSetType() {
        assertEquals(Intention.ATTACK, ac.getType());
        ac.setType(Intention.OTHER);
        assertEquals(Intention.OTHER, ac.getType());
    }

    @Test
    void testSetStatAmount() {
        assertEquals(6, ac.getStatAmount());
        ac.setStatAmount(3);
        assertEquals(3, ac.getStatAmount());
    }

    @Test
    void testSetCostNoException() {
        try {
            ac.setCost(4);
        } catch (IllegalCostException e) {
            fail("IllegalCostException should not be thrown");
        }
        assertEquals(4, ac.getCost());
    }

    @Test
    void testSetCostIllegalCost() {
        try {
            ac.setCost(-2);
            fail("Expected IllegalCostException");
        } catch (IllegalCostException e) {
            // expected
        }
    }

    @Test
    void testSetStatMultiplierNoException() {
        try {
            ac.setStatMultiplier(1.25);
        } catch (IllegalMultiplierException e) {
            fail("IllegalMultiplierException should not be thrown");
        }
        assertEquals(1.25, ac.getStatMultiplier());
    }

    @Test
    void testGetStatAmountModified() {
        assertEquals(6, ac.getStatAmount());
        try {
            ac.setStatMultiplier(1.25);
        } catch (IllegalMultiplierException e) {
            fail("IllegalMultiplierException should not be thrown");
        }
        assertEquals(1.25, ac.getStatMultiplier());
        assertEquals(8, ac.getStatAmount());
    }
}
