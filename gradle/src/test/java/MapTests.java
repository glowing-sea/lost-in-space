package tests;

import base.Location;
import base.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.GameStateExamples;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Albert Yu
 *
 *
 */
public class MapTests {

    String[] testMap;
    Map m;

    @BeforeEach
    public void beforeEachTestMethod() {
        m = GameStateExamples.TEST_STATE_ONE.map;
    }

    @Test
    public void testCaseGetMapID() {
        assertEquals(0, m.getMapID());
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
}
