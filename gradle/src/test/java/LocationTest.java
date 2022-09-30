package tests;
import base.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.GameStateExamples;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test functions in the Location Class
 */
public class LocationTest {

    // Initialise all fields in the GameConfiguration class
    @BeforeAll
    public static void initialise(){
        GameConfiguration.initialise();
    }

    GameStateExamples ex = new GameStateExamples();
    State st = ex.TEST_STATE_TWO;


    @Test
    public void equalTest(){
        Location loc1 = new Location(1,1);
        Location loc2 = new Location(1,1);
        Location loc3 = new Location(2,2);
        assertTrue(loc1.equals(loc2));
        assertFalse(loc1.equals(loc3));
    }

    @Test
    public void isinTest(){
        Location loc1 = new Location(1,1);
        Location loc2 = new Location(1,1);
        Location loc3 = new Location(2,2);
        Location loc4 = new Location(4,2);
        List<Location> locations = new ArrayList<>();
        locations.add(loc1);
        locations.add(loc2);
        locations.add(loc3);
        assertTrue(loc1.isin(locations));
        assertFalse(loc4.isin(locations));
    }

    @Test
    public void findUnitTest(){
        Location easyLoc = new Location(1,0);
        Location strongLoc = new Location(1,2);
        Location toughLoc = new Location(2,1);
        Location itemLoc = new Location(0,0);
        Location randomLoc = new Location(5,5);

        assertNull(randomLoc.findUnit(st));
        assertNotNull(easyLoc.findUnit(st));
        assertNotNull(strongLoc.findUnit(st));
        assertNotNull(toughLoc.findUnit(st));
        assertNull(itemLoc.findUnit(st));
    }

    @Test
    public void locCopyTest(){
        Location old = new Location(1,0);
        Location n = old.clone();
        old.setY(45);
        old.setX(34);

        assertEquals(1,n.getX());
        assertEquals(0,n.getY());
    }
}