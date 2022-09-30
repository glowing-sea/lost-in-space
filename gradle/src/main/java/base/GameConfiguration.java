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

    public static final String[] tips = {
            "[ Game Manual ]",
            "Character Movement: 'w', 's', 'a', 'd'",
            "Interaction: 'fw', 'fs', 'fa', 'fd'",
            "Inventory Management:",
            "'use-index', 'drop-index', 'view-index'",
            "Follow the goals implied in the story ",
            "to advance to the next game level."
    };

    public static final String GAME_TITLE = "Lost in Space";

    // Initial Stats of the player
    public static String YOUR_NAME = "Jack";
    public static final int INITIAL_HP = 100;
    public static final int INITIAL_ATK = 100;
    public static final int INITIAL_DEF = 100;
    public static final int INITIAL_EXP = 0;
    public static final int INITIAL_LEVEL = 0;
    public static List<Item> INITIAL_INVENTORY = new ArrayList<>();
    static {
        Item item = new Item(new Location(-1,-1), ItemType.HP_Boost);
        INITIAL_INVENTORY.add(item);
        INITIAL_INVENTORY.add(item);
        INITIAL_INVENTORY.add(item);
        INITIAL_INVENTORY.add(item);
        INITIAL_INVENTORY.add(item);
        INITIAL_INVENTORY.add(item);
    }
    public static final int INITIAL_CAPACITY = 6;


    // The state level indicating a finished game.
    public static final int FINAL_LEVEL;
    // An array of sequential game from start to finish.
    public static final State[] GAME_STATES;
    public static final GameLevelUpRequirement [] LEVEL_UP_REQUIREMENTS;



    // These are the default attributes when an argument passing to the constructor is null
    public static final String DEFAULT_UNIT_NAME = "Unknown";
    public static final char DEFAULT_UNIT_SYMBOL = 'U';
    public static final String[] DEFAULT_NPC_DIALOGUE = new String[]{"Bye", "Bye", "How are you?"};

    public static final Location DEFAULT_UNIT_LOC = new Location(-1,-1);

    public static final String[] DEFAULT_MAP = new String[]{"         ", "         ","         ", "         ",
            "         ", "         ","         ", "         ","         "};
    public static final char[] DEFAULT_WALLS = new char[]{};

    public static final List<Trade> DEFAULT_TRADES = new ArrayList<>();


    // Level 0 configuration

    // The initial state when the game reach level0
    public static State LEVEL0_INITIAL_STATE;
    // The game should move to the next level if the current state == LEVEL0_LEVEL_UP_STATE
    public static GameLevelUpRequirement LEVEL0_LEVEL_UP_REQUIREMENT;

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
        Item item4 = new Item(new Location(3,5), ItemType.Key);
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        enemies.add(new Enemy("Goblin1",10,150,50,new Location(0,4),20, item2));
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
        trades.add(new Trade(item2, item1));
        trades.add(new Trade(item2, item3));

        merchants.add(new Merchant("Amy", new Location(1,0), new String[]{"Goodbye", "Hope to deal with you again!","Welcome to my store!"}, trades));
        merchants.add(new Merchant(null,new Location(5,0),null,null));

        String dialogue = "This is where your story written in GameConfiguration is displayed.\nYou can have up to three lines. In each line, you can write up to 103 character.\n" +
                "If a line exceed 103 character, the exceed part will not be displayed........................................";
        LEVEL0_INITIAL_STATE = new State(mapOBJ, dialogue, 0, player, enemies, items, NPCs, merchants);
        LEVEL0_LEVEL_UP_REQUIREMENT = new GameLevelUpRequirement(new Location(6,7),false,true); // H is in 6,7
    }
    // Level 1 configuration

    // enemies
    public static State LEVEL1_INITIAL_STATE;
    // The game should move to the next level if the current state == LEVEL0_LEVEL_UP_STATE
    public static GameLevelUpRequirement LEVEL1_LEVEL_UP_REQUIREMENT;

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
        enemies.add(new Enemy("Goblin1",30,120,30,new Location(3,3),20, null));
        enemies.add(new Enemy("Goblin3",30,120,30,new Location(4,4),20, null));
        enemies.add(new Enemy("Goblin2",30,120,80,new Location(6,2),20, null));
        ArrayList<Item> items = new ArrayList<>(); // Must use empty list instead of null
        NPC C = new NPC("C",new Location(0,3),Csays);
        ArrayList<NPC> NPCs = new ArrayList<>(); // using null will let the attribute of the previous state be carry over to this state.
        NPCs.add(C);
        ArrayList<Merchant> merchants = new ArrayList<>();


        String dialogue = "fight against an enemy\n>>>>>";
        LEVEL1_INITIAL_STATE = new State(mapO1, dialogue, 1, player, enemies, items, NPCs, merchants);
        LEVEL1_LEVEL_UP_REQUIREMENT = new GameLevelUpRequirement(new Location(6,7),true,false); // H is in 6,7
    }
    public static State LEVEL2_INITIAL_STATE;
    // The game should move to the next level if the current state == LEVEL0_LEVEL_UP_STATE
    public static GameLevelUpRequirement LEVEL2_LEVEL_UP_REQUIREMENT;

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
        LEVEL2_LEVEL_UP_REQUIREMENT = new GameLevelUpRequirement(new Location(6,7),false,false); // H is in 6,7
    }

    // Level 3 Configuration
    public static State LEVEL3_INITIAL_STATE;
    public static GameLevelUpRequirement LEVEL3_LEVEL_UP_REQUIREMENT;

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
        Player player = new Player(new Location(4,4));
        ArrayList<Enemy> enemies = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>(); // Must use empty list instead of null
        ArrayList<NPC> NPCs = new ArrayList<>(); // using null will let the attribute of the previous state be carry over to this state.
        ArrayList<Merchant> merchants = new ArrayList<>();
        String dialogue = "You Win!";
        LEVEL3_INITIAL_STATE = new State(mapOBJ, dialogue, 3, player, enemies, items, NPCs, merchants);
        LEVEL3_LEVEL_UP_REQUIREMENT = null;
    }

    static {
                GAME_STATES = new State[] {
                LEVEL0_INITIAL_STATE,
                LEVEL1_INITIAL_STATE,
                LEVEL2_INITIAL_STATE,
                LEVEL3_INITIAL_STATE};

                LEVEL_UP_REQUIREMENTS = new GameLevelUpRequirement[] {
                        LEVEL0_LEVEL_UP_REQUIREMENT,
                        LEVEL1_LEVEL_UP_REQUIREMENT,
                        LEVEL2_LEVEL_UP_REQUIREMENT,
                        null,
                };
                FINAL_LEVEL = GAME_STATES.length - 1;
    }
}
