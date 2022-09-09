package tests;

import base.Enemy;
import base.Location;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * @Author Albert Yu
 */
public class EnemyUnitTests {

    Enemy c;

    @Before
    public void beforeEachTestMethod() {
        c = new Enemy("talon", 100, 5, 5, new Location(0, 0), false);

    }

    @Test
    public void testGetters() {
        Assert.assertEquals("talon", c.getName());

    }
}
