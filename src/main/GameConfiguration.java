package src.main;
import java.util.List;

public class GameConfiguration {

    // When State.level == 1, the game is completed
    public static int FINISH_REQUIREMENT = 1;


    // Level 0 Configuration
    public static Map LEVEL0_MAP;
    public static Player LEVEL0_PLAYER;
    public static List<Enemy> LEVEL0_ENEMY;
    public static String LEVEL0_DIALOGUE;
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

        LEVEL0_MAP = new Map(0, map);
        LEVEL0_PLAYER = new Player("Jack", 100, 100, 100, new Location(0,1), 0, 0);
        LEVEL0_ENEMY = null;
        LEVEL0_DIALOGUE = "You are lost on this planet. You (X) should find a way back to your spaceship (H)";
        LEVEL0_LEVEL_UP = new GameLevelUpRequirement(new Location(6,7)); // H is in 6,7
    }


    // Level 1 Configuration
    public static Map LEVEL1_MAP;
    public static Player LEVEL1_PLAYER;
    public static List<Enemy> LEVEL1_ENEMY = null;
    public static String LEVEL1_DIALOGUE;
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
        LEVEL1_MAP = new Map(0, map);
        LEVEL1_PLAYER = new Player(null, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, new Location(4,4), Integer.MIN_VALUE, Integer.MIN_VALUE);
        LEVEL1_ENEMY = null;
        LEVEL1_DIALOGUE = "You Win!";
        LEVEL1_LEVEL_UP = null;
    }
}
