package main;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * @Author Albert Yu
 */
public class EnemyUnitTests {

    main.Enemy c;

    @Before
    public void beforeEachTestMethod() {
        c = new main.Enemy("talon", 100, 5, 5, new main.Location(0, 0), false);

    }

    @Test
    public void testGetters() {
        Assert.assertEquals("talon", c.getName());

    }
}
