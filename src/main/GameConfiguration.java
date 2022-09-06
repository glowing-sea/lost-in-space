package src.main;
import java.util.List;

/**
 * This class tells what the game look like. This includes,
 * 1. the initial game state at each game level.
 * 2. the requirement for a state to move to the next level.
 * 3. which game state level indicate a finished game.
 * ...
 */

public class GameConfiguration {

    // The state level indicating a finished game.
    public static int FINISH_REQUIREMENT = 1;
    // An array of sequential game from start to finish.
    public static State[] starr;



    // Level 0 configuration

    // The initial state when the game reach level0
    public static State LEVEL0_INITIAL_STATE;
    // The game should move to the next level if the current state == LEVEL0_LEVEL_UP_STATE
    public static GameLevelUpRequirement LEVEL0_LEVEL_UP;

    static {
        String[] map = new String[] {
                "    -----",
                "         ",
                "    +--  ",
                "    |    ",
                "    +----",
                "       A ",
                "      AHA",
                "---------",
                "         "};

        Map mapOBJ = new Map(0, map, new char[] {'-', '+', '|'});
        Player player = new Player("Jack", 100, 100, 100, new Location(0,1), 0, 0);
        List<Enemy> enemies = null;
        String dialogue = "You are lost on this planet. You (X) should find a way back to your spaceship (H)";
        LEVEL0_INITIAL_STATE = new State(mapOBJ, player, null, dialogue, 0);
        LEVEL0_LEVEL_UP = new GameLevelUpRequirement(new Location(6,7)); // H is in 6,7
    }


    // Level 1 Configuration
    public static State LEVEL1_INITIAL_STATE;
    public static GameLevelUpRequirement LEVEL1_LEVEL_UP;

    static {
        String[] map = new String[] {
                "         ",
                "         ",
                "    A    ",
                "   A A   ",
                "   | |   ",
                "   | |   ",
                "  A| |A  ",
                "  H| |H  ",
                "  H|_|H  "};
        Map mapOBJ = new Map(0, map, new char[] {'A', '|', 'H','_'});
        Player player = new Player(null, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, new Location(4,4), Integer.MIN_VALUE, Integer.MIN_VALUE);
        List<Enemy> enemies = null;
        String dialogue = "You Win!";
        LEVEL1_INITIAL_STATE = new State(mapOBJ, player, null, dialogue, 1);
        LEVEL1_LEVEL_UP = null;
    }

    static {
        starr = new State[] {
                LEVEL0_INITIAL_STATE,
                LEVEL1_INITIAL_STATE};
    }
}
