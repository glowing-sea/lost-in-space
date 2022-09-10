package src.base;
import java.util.ArrayList;
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

    // player still get injury or powered when they enter new level;
    //our player may become stronger during game. so these variables are public.
    public static int gamerhp = 100; //your initial hp
    public static int gameratk = 100; //your initial atk
    public static int gamerdef = 100; //your initial def

    public static int gamerexp = 0; //your initial exp
    public static int gamerlevel = 0; //your initial level


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
        Player player = new Player("Jack", gamerhp, gameratk, gamerdef, new Location(0,1), gamerexp, gamerlevel);
        List<Enemy> enemies = null;
        String dialogue = "You are lost on this planet. You (X) should find a way back to your spaceship (H)";
        LEVEL0_INITIAL_STATE = new State(mapOBJ, player, null, dialogue, 0);
        LEVEL0_LEVEL_UP = new GameLevelUpRequirement(new Location(6,7)); // H is in 6,7
    }
    // Level 1 configuration

    // enemies
    public static State LEVEL1_INITIAL_STATE;
    // The game should move to the next level if the current state == LEVEL0_LEVEL_UP_STATE
    public static GameLevelUpRequirement LEVEL1_LEVEL_UP;

    static {
        String[] map = new String[] {

                "         ",
                "         ",
                "------ --",
                "         ",
                "-- - ----",
                "         ",
                "       H ",
                "---------",
                "         "};

        Map mapO1 = new Map(1, map, new char[] {'-', '+', '|','E'});
        Player player = new Player("Jack", gamerhp, gameratk, gamerdef, new Location(0,1), gamerexp, gamerlevel);

        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy("Goblin1",30,120,30,new Location(3,3),false));
        enemies.add(new Enemy("Goblin3",30,120,30,new Location(4,4),false));
        enemies.add(new Enemy("Goblin2",30,120,80,new Location(6,2),false));

        String dialogue = "fight against an enemy";
        LEVEL1_INITIAL_STATE = new State(mapO1, player, enemies, dialogue, 1);
        LEVEL1_LEVEL_UP = new GameLevelUpRequirement(new Location(6,7)); // H is in 6,7
    }

    // Level 10 Configuration
    public static State LEVEL10_INITIAL_STATE;
    public static GameLevelUpRequirement LEVEL10_LEVEL_UP;

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
        LEVEL10_INITIAL_STATE = new State(mapOBJ, player, null, dialogue, 2);
        LEVEL10_LEVEL_UP = null;
    }

    static {
        starr = new State[] {
                LEVEL0_INITIAL_STATE,
                LEVEL1_INITIAL_STATE,
                LEVEL10_INITIAL_STATE};
    }
}
