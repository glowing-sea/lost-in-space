import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import src.base.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
