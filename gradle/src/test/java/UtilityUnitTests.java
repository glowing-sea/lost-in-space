package tests;

import base.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.Utility;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
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

    // Initialise all fields in the GameConfiguration class
    @BeforeAll
    public static void initialise(){
        GameConfiguration.initialise();
    }

    String fn = "SoftwareEngineering-TestingWriteToFile.json";
    private final String ROOT_DIR = "./saves";
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

        Enemy e1 = new Enemy("Evil",10, 10, 10, new Location(0,0), 20,null);
        Enemy e2 = new Enemy("Pevil",10, 10, 10, new Location(20,10), 20,null);
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(e1);
        enemies.add(e2);

        Item i1 = new Item(new Location(1,1), ItemType.EXP_Boost);
        List<Item> i = new ArrayList<>();
        i.add(i1);

        Player p1 = new Player("talon", 100, 100, 100, new Location(10,20), 0, 1);

        s = new State(new Map(0, map, new char[] {'-', '+', '|'}), "dual", 10, p1, enemies,  i, null, null);
    }

    /**
     * Test the function "make directory", in Main.class
     */
    @Test
    public void testMakeDirectory() {
        Main.makeDirectory();
        File f = new File(Main.SAVE_LOAD_DIRECTORY);

        assertTrue(f.exists());
    }

    /**
     * Test the saving function of this game engine.
     */
    @Test
    public void testWriteToJson() {

        Utility.writeToJSON (ROOT_DIR + "/" + fn, s);
        File f = new File(ROOT_DIR + "/" + fn);
        assertTrue(f.exists());
    }

    /**
     * Test the load function of thie game engine.
     */
    @Test
    public void testReadFromJson() {
        // first test
        State p = Utility.readFromJSON(ROOT_DIR + "/" + fn);
        assertNotNull(p);

        // second test
        assertEquals("dual", p.story);

        // third test
        assertEquals(10, p.level);
    }

    /**
     * test an exception thrown by the Utility class.
     */
    @Test
    void exceptionTesting() {
        // because C:/tempp does not exist on local
        Exception exception = assertThrows(UncheckedIOException.class, () -> Utility.writeToFile("C:/tempp/testing$stuff.txt", "test"));

        // FIXME
        //System.out.println(exception.getMessage());
        //assertEquals("java.io.FileNotFoundException: C://tempp//testing$stuff.txt (The system cannot find the path specified)", exception.getMessage());
    }
}