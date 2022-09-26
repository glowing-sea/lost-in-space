package base;
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

        Map mapOBJ = new Map(0, map, new char[] {'-', '+', '|','E'});
        Player player = new Player("Jack", 100, 100, 100, new Location(0,1), 0, 0);

        List<Enemy> enemies = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        List<NPC> NPCs = new ArrayList<>();
        List<Merchant> merchants = new ArrayList<>();

        Item item1 = new Item(new Location(3,2), ItemType.Inventory_Boost);
        Item item2 = new Item(new Location(5,2), ItemType.HP_Boost);
        Item item3 = new Item(new Location(5,4), ItemType.HP_Boost);
        items.add(item1);
        items.add(item2);
        items.add(item3);
        enemies.add(new Enemy("Goblin1",30,120,30,new Location(0,4),false));
        NPCs.add(new NPC("Bob", new Location(5,5), "What's Up?"));
        List<Trade> trades = new ArrayList<>();
        trades.add(new Trade(item1, item2));
        merchants.add(new Merchant("Amy", new Location(3,5), "Wants some trades", trades));

        String dialogue = "You are lost on this planet. You (X) should find a way back to your spaceship (H)";
        LEVEL0_INITIAL_STATE = new State(mapOBJ, dialogue, 0, player, enemies, items, NPCs, merchants);
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
        Player player = new Player(null, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, new Location(0,1), Integer.MIN_VALUE, Integer.MIN_VALUE);

        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy("Goblin1",30,120,30,new Location(3,3),false));
        enemies.add(new Enemy("Goblin3",30,120,30,new Location(4,4),false));
        enemies.add(new Enemy("Goblin2",30,120,80,new Location(6,2),false));

        String dialogue = "fight against an enemy";
        LEVEL1_INITIAL_STATE = new State(mapO1, dialogue, 1, player, enemies, null, null, null);
        LEVEL1_LEVEL_UP = new GameLevelUpRequirement(new Location(6,7)); // H is in 6,7
    }
    public static State LEVEL3_INITIAL_STATE;
    // The game should move to the next level if the current state == LEVEL0_LEVEL_UP_STATE
    public static GameLevelUpRequirement LEVEL3_LEVEL_UP;

    static {
        String[] map = new String[] {

                "         ",
                "         ",
                "         ",
                "         ",
                "         ",
                "         ",
                "       H ",
                "---------",
                "         "};

        Map mapO3 = new Map(3, map, new char[] {'-', '+', '|','E'});
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy("Goblin1",30,120,30,new Location(6,6),false));
        enemies.add(new Enemy("Goblin3",30,120,30,new Location(5,7),false));
        enemies.add(new Enemy("Goblin2",30,120,80,new Location(7,6),false));
        Player player = new Player(null, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, new Location(0,1), Integer.MIN_VALUE, Integer.MIN_VALUE);

        String dialogue = "continue adventure";
        LEVEL3_INITIAL_STATE = new State(mapO3, dialogue, 2, player, enemies, null, null , null);
        LEVEL3_LEVEL_UP = new GameLevelUpRequirement(new Location(6,7)); // H is in 6,7
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
        LEVEL10_INITIAL_STATE = new State(mapOBJ, dialogue, 3, player, null, null, null, null);
        LEVEL10_LEVEL_UP = null;
    }

    static {
        starr = new State[] {
                LEVEL0_INITIAL_STATE,
                LEVEL1_INITIAL_STATE,
                LEVEL3_INITIAL_STATE,
                LEVEL10_INITIAL_STATE};
    }
}
