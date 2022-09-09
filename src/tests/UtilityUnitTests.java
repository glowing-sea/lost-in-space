package tests;

import org.junit.BeforeClass;
import org.junit.Test;
import utility.Utility;

public class UtilityUnitTests {

    static String fn;

    @BeforeClass
    public static void beforeEachTestMethod() {
        fn = "SoftwareEngineering-TestingWriteToFile.txt";
    }

    @Test
    public void testWriteToFile() {
        Utility.writeToFile(fn);
    }
}
