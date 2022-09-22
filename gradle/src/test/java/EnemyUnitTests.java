import base.Enemy;
import base.Location;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @Author Albert Yu
 *
 */
public class EnemyUnitTests {

    Enemy c;

    @BeforeEach
    public void beforeEachTestMethod() {
        c = new Enemy("talon", 100, 5, 5, new Location(0, 0), false);

    }

    @Test
    public void testGetters() {
        Assertions Assert;
        assertEquals("talon", c.getName());
    }
}
