package utility;

import base.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A Class store some sample game states
 */
public class GameStateExamples {
    public static final State TEST_STATE_ONE;
    public static final State TEST_STATE_TWO;


    static {
        String[] map = new String[] {
                " X  E----",
                "         ",
                "N   +--  ",
                "  i |M   ",
                "    +----",
                "  i i  A ",
                "      AHA",
                "---------",
                "         "};

        Map mapOBJ = new Map(0, map, new char[] {'-', '+', '|'});
        Player player = new Player("Jack", 100, 100, 100, new Location(0,1),20, 20);

        List<Enemy> enemies = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        List<NPC> NPCs = new ArrayList<>();
        List<Merchant> merchants = new ArrayList<>();

        Item item1 = new Item(new Location(3,2), ItemType.Inventory_Boost);
        Item item2 = new Item(new Location(5,2), ItemType.HP_Boost);
        Item item3 = new Item(new Location(5,4), ItemType.ATK_Boost);
        items.add(item1);
        items.add(item2);
        items.add(item3);
        enemies.add(new Enemy("Goblin1",10,150,50,new Location(0,4),20, item2));
        NPCs.add(new NPC("Bob", new Location(2,0), new String[]{"Welcome to my land!", "`Where should I go?"}));
        List<Trade> trades = new ArrayList<>();
        trades.add(new Trade(item1, item2));
        merchants.add(new Merchant("Amy", new Location(3,5), new String[]{"bye","bye","Wants some trades"}, trades));

        String dialogue = "This is where your story written in GameConfiguration is displayed.\nYou can have up to three lines. In each line, you can write up to 103 character.\n" +
                "If a line exceed 103 character, the exceed part will not be displayed........................................";
        TEST_STATE_ONE = new State(mapOBJ, dialogue, 0, player, enemies, items, NPCs, merchants);
    }

    static {
        ArrayList<Enemy> enemies = new ArrayList<>();
        Item item = new Item(new Location(-1,-1), ItemType.HP_Boost);
        Enemy enemyEasy = new Enemy("easy",10,30,30,new Location(1,0),20,item);
        Enemy enemyStrong = new Enemy("strong",10,150,50,new Location(1,2),150,item);
        Enemy enemyTough = new Enemy("tough",100,1000,80,new Location(2,1),350,item);
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
        TEST_STATE_TWO = new State(testMap,"abc",0, player,enemies,new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }
}
