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

    static {
        initialise();
    }

    public static void initialise(){
        // Game Attributes
        GAME_TITLE = "Lost in Space";
        TIPS = new String[]{
                "[ Game Manual ]",
                "Character Movement: 'w', 's', 'a', 'd'",
                "Interaction: 'fw', 'fs', 'fa', 'fd'",
                "Inventory Management:",
                "'use-index', 'drop-index', 'view-index'",
                "Follow the goals implied in the story ",
                "to advance to the next game level."
        };
        PLAYER_LEVEL_UP_EFFECT = 20;

        // Player Attributes
        YOUR_NAME = "Jack";
        INITIAL_HP = 100;
        INITIAL_ATK = 100;
        INITIAL_DEF = 100;
        INITIAL_EXP = 0;
        INITIAL_LEVEL = 0;
        INITIAL_INVENTORY = new ArrayList<>();
        Item item = new Item(new Location(-1,-1), ItemType.HP_Boost);
        INITIAL_INVENTORY.add(item);
        INITIAL_INVENTORY.add(item);
        INITIAL_INVENTORY.add(item);
        INITIAL_INVENTORY.add(item);
        INITIAL_CAPACITY = 6;
        // Unit Attributes
        DEFAULT_UNIT_NAME = "Unknown";
        DEFAULT_UNIT_SYMBOL = 'U';
        DEFAULT_NPC_DIALOGUE = new String[]{"Bye", "Bye", "How are you?"};
        DEFAULT_UNIT_LOC = new Location(-1,-1);
        DEFAULT_MAP = new String[]{"         ", "         ","         ", "         ",
                "         ", "         ","         ", "         ","         "};
        DEFAULT_WALLS = new char[]{};
        DEFAULT_TRADES = new ArrayList<>();

        setLevel0();
        setLevel1();
        setLevel2();
        setLevel3();
        groupAllLevel ();
    }

    // Game Attributes
    public static String GAME_TITLE;
    public static String[] TIPS;

    // All atk, hp, and def increase by x when the player level increases by 1.
    public static int PLAYER_LEVEL_UP_EFFECT;

    // Player Attributes
    public static String YOUR_NAME;
    public static int INITIAL_HP;
    public static int INITIAL_ATK;
    public static int INITIAL_DEF;
    public static int INITIAL_EXP;
    public static int INITIAL_LEVEL;
    public static List<Item> INITIAL_INVENTORY;
    public static int INITIAL_CAPACITY;

    // Default Unit Attributes (when they are set as null)
    public static String DEFAULT_UNIT_NAME;
    public static char DEFAULT_UNIT_SYMBOL;
    public static String[] DEFAULT_NPC_DIALOGUE;
    public static Location DEFAULT_UNIT_LOC;
    public static String[] DEFAULT_MAP;
    public static char[] DEFAULT_WALLS;
    public static List<Trade> DEFAULT_TRADES;



    // The state level indicating a finished game.
    public static int FINAL_LEVEL;
    // An array of sequential game from start to finish.
    public static State[] GAME_STATES;
    public static GameLevelUpRequirement [] LEVEL_UP_REQUIREMENTS;


    // Level 0 configuration

    // The initial state when the game reach level0
    public static State LEVEL0_INITIAL_STATE;
    // The game should move to the next level if the current state == LEVEL0_LEVEL_UP_STATE
    public static GameLevelUpRequirement LEVEL0_LEVEL_UP_REQUIREMENT;
    public static State LEVEL1_INITIAL_STATE;
    public static GameLevelUpRequirement LEVEL1_LEVEL_UP_REQUIREMENT;
    public static State LEVEL2_INITIAL_STATE;
    public static GameLevelUpRequirement LEVEL2_LEVEL_UP_REQUIREMENT;
    public static State LEVEL3_INITIAL_STATE;
    public static GameLevelUpRequirement LEVEL3_LEVEL_UP_REQUIREMENT;

    private static void setLevel0 (){
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
        String[] bobSays = new String[]{
                "Goodbye Bob", // how the player say goodbye to bob
                "Bye Caption", // how bob say goodbye to the player
                "Hello Captain", // autoplay, talked by Bob
                "` Hi Bob", // autoplay, talked by the player
                "Are you worrying about something?", // autoplay, talked by Bob
                "> Where should I go?", // player option 1 (>)
                "> Get out!", // player option 2 (>)
                "< Go to (H).", // Bob answer 1 (<)
                "< Claim down please!", // Bob answer 2 (<)
                "We are lost on a unknown planet",
                "We need to find a way back",
                "` I see!",
                "> Did you get hurt?",
                "> Is our spaceship find?",
                "< No. I am fine. Thank you.",
                "< Our spaceship is damaged and we have to repair it!",
        };
        String[] aliceSays = new String[]{
                "Goodbye", // how the player say goodbye to bob
                "See you, have a nice trip!", // how bob say goodbye to the player
                "Hello",
                "Who are you?",
                "> I am captain Jack",
                "> Say your name first!",
                "< Glad to see you Jack",
                "< I am Alice ",
        };

        Map mapOBJ = new Map(0, map, new char[] {'-', '+', '|'});
        Player player = new Player(new Location(0,1));

        List<Enemy> enemies = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        List<NPC> NPCs = new ArrayList<>();
        List<Merchant> merchants = new ArrayList<>();

        Item item1 = new Item(new Location(3,2), ItemType.Inventory_Boost);
        Item item2 = new Item(new Location(5,2), ItemType.HP_Boost);
        Item item3 = new Item(new Location(5,4), ItemType.ATK_Boost);
        Item item4 = new Item("Key To Level 1", new Location(3,5));
        Item item5 = new Item(null,ItemType.EXP_Boost);
        Item item6 = new Item(null,ItemType.DEF_Boost);
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        Enemy e = new Enemy("Goblin1",10,150,50,new Location(0,4),20, item2);
        enemies.add(e);
        NPC Bob = new NPC("Bob", new Location(2,0), bobSays);
        NPC Alice = new NPC("Alice", new Location(3,0), aliceSays);
        NPC NoName = new NPC(null, new Location(4,0), null);
        NPCs.add(Bob);
        NPCs.add(Alice);
        NPCs.add(NoName);

        List<Trade> trades = new ArrayList<>();
        trades.add(new Trade(item2, item1));
        trades.add(new Trade(item2, item3));
        trades.add(new Trade(item2, item1));
        trades.add(new Trade(item2, item3));
        trades.add(new Trade(item2, item5));
        trades.add(new Trade(item2, item6));

        merchants.add(new Merchant("Amy", new Location(1,0), new String[]{"Goodbye", "Hope to deal with you again!","Welcome to my store!"}, trades));
        merchants.add(new Merchant(null,new Location(5,0),null,null));

        String dialogue = "This is where your story written in GameConfiguration is displayed.\nYou can have up to three lines. In each line, you can write up to 103 character.\n" +
                "If a line exceed 103 character, the exceed part will not be displayed........................................";
        LEVEL0_INITIAL_STATE = new State(mapOBJ, dialogue, 0, player, enemies, items, NPCs, merchants);


        Location destination = new Location(6,7);
        List<Enemy> enemiesMustBeKilled = new ArrayList<>();
        enemiesMustBeKilled.add(e);
        Item itemRequired = new Item("Key To Level 1", null);
        LEVEL0_LEVEL_UP_REQUIREMENT = new GameLevelUpRequirement(destination,enemiesMustBeKilled,itemRequired); // H is in 6,7
    }

    private static void setLevel1 () {
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
        String[] Csays = new String[]{
                "HUUUUuuuu (Goodbye)",
                "DerrrrRrr!",
                "Duluuuuuuu",
                "Lalalala",
                "How are you?",
                "> I am Captain Jack",
                "> Who are E on map?",
                "< who is Jack ",
                "< Those are evil NPC. Fight against them!",

        };
        Map mapO1 = new Map(1, map, new char[] {'-', '+', '|'});
        Player player = new Player(new Location(0,1));
        ArrayList<Enemy> enemies = new ArrayList<>();
        Item enemyKey = new Item("Enemy Key",null);
        enemies.add(new Enemy("Goblin1",30,120,30,new Location(3,3),20, enemyKey));
        enemies.add(new Enemy("Goblin3",30,120,30,new Location(4,4),20, null));
        enemies.add(new Enemy("Goblin2",30,120,80,new Location(6,2),20, null));
        NPC C = new NPC("C",new Location(0,3),Csays);
        ArrayList<NPC> NPCs = new ArrayList<>(); // using null will let the attribute of the previous state be carry over to this state.
        NPCs.add(C);
        ArrayList<Merchant> merchants = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>(); // Must use empty list instead of null
        Item item = new Item(new Location(1,0), ItemType.EXP_Boost);
        items.add(item);


        String dialogue = "fight against an enemy\n>>>>>";
        LEVEL1_INITIAL_STATE = new State(mapO1, dialogue, 1, player, enemies, items, NPCs, merchants);
        LEVEL1_LEVEL_UP_REQUIREMENT = new GameLevelUpRequirement(new Location(6,7),enemies,enemyKey); // H is in 6,7
    }


    private static void setLevel2 ()  {
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
        String[] Davidsays = new String[]{
                "Bye",
                "I will miss you",
                "Ahhh",
                "This is David",
                "Terrrrrrr",
                "Where are you going?",
                "> I am going to Spaceship",
                "> What happened here",
                "< Good Luck then",
                "< Enemy take your spaceship",
                "Fight against the enemies or dive in your space ship",
        };
        Map mapO3 = new Map(3, map, new char[] {'-', '+', '|'});
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy("Goblin1",30,120,30,new Location(6,6),20, null));
        enemies.add(new Enemy("Goblin3",30,120,30,new Location(5,7),20, null));
        enemies.add(new Enemy("Goblin2",30,120,80,new Location(7,6),20, null));
        Player player = new Player(new Location(0,1));
        ArrayList<Item> items = new ArrayList<>(); // Must use empty list instead of null
        NPC David = new NPC("David",new Location(3,3),Davidsays);
        ArrayList<NPC> NPCs = new ArrayList<>(); // using null will let the attribute of the previous state be carry over to this state.
        NPCs.add(David);
        ArrayList<Merchant> merchants = new ArrayList<>();

        String dialogue = "continue adventure";
        LEVEL2_INITIAL_STATE = new State(mapO3, dialogue, 2, player, enemies, items, NPCs , merchants);
        LEVEL2_LEVEL_UP_REQUIREMENT = new GameLevelUpRequirement(new Location(6,7),null,null); // H is in 6,7
    }


    private static void setLevel3 ()  {
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
        Player player = new Player(new Location(4,4));
        ArrayList<Enemy> enemies = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>(); // Must use empty list instead of null
        ArrayList<NPC> NPCs = new ArrayList<>(); // using null will let the attribute of the previous state be carry over to this state.
        ArrayList<Merchant> merchants = new ArrayList<>();
        String dialogue = "You Win!";
        LEVEL3_INITIAL_STATE = new State(mapOBJ, dialogue, 3, player, enemies, items, NPCs, merchants);
        LEVEL3_LEVEL_UP_REQUIREMENT = null;
    }

    private static void groupAllLevel () {
        GAME_STATES = new State[]{
                LEVEL0_INITIAL_STATE,
                LEVEL1_INITIAL_STATE,
                LEVEL2_INITIAL_STATE,
                LEVEL3_INITIAL_STATE};

        LEVEL_UP_REQUIREMENTS = new GameLevelUpRequirement[]{
                LEVEL0_LEVEL_UP_REQUIREMENT,
                LEVEL1_LEVEL_UP_REQUIREMENT,
                LEVEL2_LEVEL_UP_REQUIREMENT,
                null,
        };
        FINAL_LEVEL = GAME_STATES.length - 1;
    }
}
