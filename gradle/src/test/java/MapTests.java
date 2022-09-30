package tests;

import base.GameConfiguration;
import base.Location;
import base.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.GameStateExamples;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Albert Yu
 * @author Haoting Chen
 *
 */
public class MapTests {

    // Initialise all fields in the GameConfiguration class
    @BeforeAll
    public static void initialise(){
        GameConfiguration.initialise();
    }

    String[] testMap;
    Map m;

    @BeforeEach
    public void beforeEachTestMethod() {
        GameStateExamples.initialise();
        m = GameStateExamples.TEST_STATE_ONE.map;
    }

    @Test
    public void testCaseGetMapID() {
        assertEquals(0, m.getMapID());
    }

    @Test
    public void testEqual() {
        GameStateExamples ex = new GameStateExamples();
        Map m1 = ex.TEST_STATE_ONE.map;
        Map m2 = ex.TEST_STATE_TWO.map;
        assertNotEquals(m1, m2);
        assertNotEquals(m2, m1);
        assertEquals(m1, m1);
        assertEquals(m2, m2);
    }

    @Test
    public void testReachable() {
        Location loc1 = new Location(2,4);
        assertFalse(m.reachable(loc1));
        Location loc2 = new Location(2,6);
        assertFalse(m.reachable(loc2));
        Location loc3 = new Location(4,4);
        assertFalse(m.reachable(loc3));
        Location loc4 = new Location(2,2);
        assertTrue(m.reachable(loc4));
        Location loc5 = new Location(1,1);
        assertTrue(m.reachable(loc5));
    }

    @Test
    public void lineNotWellFormed() {
        String[] map = new String[]{"         ", "         ","         ", "         ",
                "         ", "         ","         ", "         "};
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Map m = new Map(0,map,null);
        });

        String expectedMessage = "The map is not well-formed.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void columnNotWellFormed() {
        String[] map = new String[]{"         ", "          ","         ", "         ",
                "         ", "         ","         ", "         ", "         "};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Map m = new Map(0,map,null);
        });

        String expectedMessage = "The map is not well-formed.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
