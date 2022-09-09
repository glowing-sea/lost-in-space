package src.tests;

import src.base.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Albert Yu
 */
public class MapUnitTests {

    String[] testMap;
    Map m;

    @Before
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
        Assert.assertEquals(1, m.getMapID());
    }
}
