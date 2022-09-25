package gradle.src.test.java;

import gradle.src.main.java.base.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Albert Yu
 *
 *
 */
public class MapUnitTests {

    String[] testMap;
    Map m;

    @BeforeEach
    public void beforeEachTestMethod() {
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
