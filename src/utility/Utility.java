package utility;

import java.io.FileWriter;
import java.io.IOException;

public class Utility {
    private static final String ROOT_DIR = "C:/temp";

    public static void writeToFile(String fileName) {

        try {
            FileWriter fw = new FileWriter(ROOT_DIR + "/" + fileName);

            fw.write("foo foo foo");

            fw.close();
        }
        catch(IOException ioe) {
            System.err.println(ioe.toString());
        }
    }
}
