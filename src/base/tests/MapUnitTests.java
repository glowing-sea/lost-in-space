package src.base.tests;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import src.base.Map;

/**
 * @author Albert Yu
 *
 *
 */
public class MapUnitTests {

    static String[] testMap;
    static Map m;

    @BeforeAll
    static void beforeEachTestMethod() {
        testMap = new String[] {
                "    -----",
                "         ",
                "    +--  ",
                "    |    ",
                "    +----",
                "       A ",
                "      AHA",
                "---------",
                "         "};

        m = new Map(1, testMap, new char[] {'-', '+', '|'});
    }

    @Test
    public void testCaseGetMapID() {
        assertEquals(1, m.getMapID());
    }
}
