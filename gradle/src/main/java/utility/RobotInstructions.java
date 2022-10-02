package utility;

import base.KeyEventHandler;
import base.Location;
import base.State;

import java.util.ArrayList;
import java.util.List;

/**
 * @author William Barter
 * automate testing
 */
public class RobotInstructions {

    /**
     * Take a list of input separated by space, execute them one by one.
     * @author Haoting Chen
     * @param st current state
     * @param in a list of input separated by space, e.g. "w s d t buy-1"
     */
    public static void inputReader(State st, String in){
        String[] inputs = in.split(" ");
        for (String input : inputs){
            KeyEventHandler.keyEventHandler(st, input);
        }
    }

    public static List<String> Level0() {
        List<String> Level0 = new ArrayList<>();
        Level0.add("s");
        Level0.add("dd");
        Level0.add("dd");
        Level0.add("dd");
        Level0.add("ss");
        Level0.add("a");
        Level0.add("use-1");
        Level0.add("fa");
        Level0.add("d");
        Level0.add("ww");
        Level0.add("aa");
        Level0.add("a");
        Level0.add("fw");
        Level0.add("a");
        Level0.add("ss");
        Level0.add("ss");
        Level0.add("s");
        Level0.add("dd");
        Level0.add("dd");
        return Level0;
    }

    public static List<String> Level1() {
        List<String> Level1 = new ArrayList<>();
        Level1.add("s");
        Level1.add("dd");
        Level1.add("dd");
        Level1.add("d");
        Level1.add("ss");
        Level1.add("aa");
        Level1.add("fa");
        Level1.add("use-1");
        Level1.add("fs");
        Level1.add("use-1");
        Level1.add("ss");
        Level1.add("s");
        Level1.add("a");
        Level1.add("fa");
        Level1.add("fa");
        Level1.add("dd");
        Level1.add("dd");
        return Level1;
    }
    public static List<String> Level2() {
        List<String> Level2 = new ArrayList<>();
        Level2.add("dd");
        Level2.add("dd");
        Level2.add("dd");
        Level2.add("d");
        Level2.add("ss");
        Level2.add("a");
        Level2.add("ss");
        Level2.add("d");
        Level2.add("ss");
        Level2.add("a");
        return Level2;
    }


}
