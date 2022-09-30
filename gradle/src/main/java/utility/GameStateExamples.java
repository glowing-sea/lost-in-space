package utility;

import base.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A Class store some sample game states
 */
public class GameStateExamples {

    static {
        initialise();
    }

    public static State TEST_STATE_ONE;
    public static State TEST_STATE_TWO;
    public static GameLevelUpRequirement TEST_STATE_ONE_LEVEL_UP;

    public static void initialise(){
        setTestStateOne();
        setTestStateTwo();
    }

    private static void setTestStateOne(){
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

        /*
        |__________________|
        |  X     E - - - - |
        |M                 |
        |N       + - -     |
        |N   i   | i       |
        |N       + - - - - |
        |M   i   i     A   |
        |            A H A |
        |- - - - - - - - - |
         */
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
        trades.add(new Trade(item2, item1));
        trades.add(new Trade(item2, item3));

        merchants.add(new Merchant("Amy", new Location(1,0), new String[]{"Goodbye", "Hope to deal with you again!","Welcome to my store!"}, trades));
        merchants.add(new Merchant(null,new Location(5,0),null,null));

        String dialogue = "This is where your story written in GameConfiguration is displayed.\nYou can have up to three lines. In each line, you can write up to 103 character.\n" +
                "If a line exceed 103 character, the exceed part will not be displayed........................................";
        TEST_STATE_ONE = new State(mapOBJ, dialogue, 0, player, enemies, items, NPCs, merchants);


        Location destination = new Location(6,7);
        List<Enemy> enemiesMustBeKilled = new ArrayList<>();
        enemiesMustBeKilled.add(e);
        Item itemRequired = new Item("Key To Level 1", null);
        TEST_STATE_ONE_LEVEL_UP = new GameLevelUpRequirement(destination,enemiesMustBeKilled,itemRequired); // H is in 6,7
    }

    private static void setTestStateTwo(){
        List<Enemy> enemies = new ArrayList<>();
        Item key = new Item("levelUpKey", new Location(0,3));
        Item reward = new Item(null,ItemType.HP_Boost);
        List<Item> items = new ArrayList<>();
        items.add(key);
        Enemy enemyEasy = new Enemy("easy",10,30,30,new Location(1,0),20,reward);
        Enemy enemyStrong = new Enemy("strong",10,150,50,new Location(1,2),150,reward);
        Enemy enemyTough = new Enemy("tough",100,1000,80,new Location(2,1),350,reward);
        Player player = new Player("Ben",100,100,100,new Location(1,1),0,0);
        enemies.add(enemyEasy);
        enemies.add(enemyStrong);
        enemies.add(enemyTough);

        String[] mapTest = new String[] {
                "         ",
                "EXE      ",
                "-E---- --",
                "         ",
                "-- ------",
                "         ",
                "       H ",
                "---------",
                "         "};
        char[] testWalls = new char[] {'-'};
        Map testMap = new Map(-1,mapTest,testWalls);
        TEST_STATE_TWO = new State(testMap,"abc",0, player,enemies,items, new ArrayList<>(), new ArrayList<>());
    }
}
