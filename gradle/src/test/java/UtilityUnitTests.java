// package gradle.src.test.java;
import base.*;
import utility.*;
//import gradle.src.main.java.base.*;
//import gradle.src.main.java.utility.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Albert Yu
 *
 * Unit test cases for Utility.java
 *
 */
public class UtilityUnitTests {

    String fn = "SoftwareEngineering-TestingWriteToFile.json";
    private final String ROOT_DIR = "C:/temp";
    private State s;

    @BeforeEach
    public void beforeEachTestMethod() {

        String[] map = new String[] {
                "    -----",
                "         ",
                "    +--  ",
                "    |    ",
                "    +----",
                "       A ",
                "      AHA",
                "---------",
                "         "};

        Enemy e1 = new Enemy("Evil",10, 10, 10, new Location(0,0), false);
        Enemy e2 = new Enemy("Pevil",10, 10, 10, new Location(20,10), false);
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(e1);
        enemies.add(e2);

        Item i1 = new Item(new Location(1,1), ItemType.EX_Boost);
        List<Item> i = new ArrayList<>();
        i.add(i1);

        Player p1 = new Player("talon", 100, 100, 100, new Location(10,20), 0, 1);

        s = new State(new Map(0, map, new char[] {'-', '+', '|'}), "dual", 10, p1, enemies,  i, null, null);
    }
    @Test
    public void testWriteToJson() {

        Utility.writeToJSON (ROOT_DIR + "/" + fn, s);
        File f = new File(ROOT_DIR + "/" + fn);
        assertTrue(f.exists());
    }

    @Test
    public void testReadFromJson() {
        // first test
        State p = Utility.readFromJSON(ROOT_DIR + "/" + fn);
        assertNotNull(p);

        // second test
        assertEquals("dual", p.getStory());

        // third test
        assertEquals(10, p.getLevel());
    }
}