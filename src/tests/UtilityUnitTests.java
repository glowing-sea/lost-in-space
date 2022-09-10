package tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import utility.Utility;

import java.io.File;

public class UtilityUnitTests {

    static String fn;
    private static final String ROOT_DIR = "C:/temp";

    @BeforeClass
    public static void beforeEachTestMethod() {
        fn = "SoftwareEngineering-TestingWriteToFile.txt";
    }

    @Test
    public void testWriteToFile() {

        Utility.writeToFile(fn, "foo foo foo");
        File f = new File(ROOT_DIR + "/" + fn);
        Assert.assertTrue(f.exists());
    }
}
