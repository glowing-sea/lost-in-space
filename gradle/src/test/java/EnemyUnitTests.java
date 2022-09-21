import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import src.base.Enemy;
import src.base.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author Albert Yu

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
