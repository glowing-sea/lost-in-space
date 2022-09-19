package src.base.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import src.base.Enemy;
import src.base.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author Albert Yu
 * > Journal (Albert Yu):
 * > ATTEMPT 1: tried to make everything static, so as to incorporate a Main Method
 *   > results: this does NOT work.
 *   >          JUnit does not allow test cases to be made static.
 * > ATTEMPT 2: try running the test case from something else other than the Main Method.
 *              > Makefile? NO.
 *              > Maybe I can find a test case runner, an executable that specifically runs JUnit test cases.
 *              > Docker
 *              > JUnit Platform Commons
 */
public class EnemyUnitTests {

    private static Enemy c;

    @BeforeAll
    static void beforeEachTestMethod() {
        c = new Enemy("talon", 100, 5, 5, new Location(0, 0), false);

    }

    @Test
    void testGetters() {
        assertEquals("talon", c.getName());

    }
}
