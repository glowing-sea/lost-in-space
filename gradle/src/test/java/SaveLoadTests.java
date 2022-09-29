package tests;

import base.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.GameStateExamples;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This unit tests are meant for the Save and Load Feature
 * @author Albert Yu
 */
public class SaveLoadTests {

    private String SAVE_LOAD_DIRECTORY = "C:/game_engine";
    private String SAVE_FILENAME = "save-load-testing.json";

    /**
     *
     */
    @Test
    public void testMakeDirectory() {
        Main.makeDirectory();
        File f = new File(Main.SAVE_LOAD_DIRECTORY);

        assertTrue(f.exists());
    }

    @Test
    public void testSaveGame() {

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
                "Duluuuuuuu",
                "Lalalala",
                "How are you?O", // O indicate that you want player to do selection
                "(A) I am Captain Jack",
                "(B) Who are E on map?",
                "(L) HUUUUuuuu",
                "~Ar who is Jack ",
                "~Br Those are evil NPC",
                "~Br Fight against them",
                "~Lr DerrrrRrr!"
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
        State s = new State(mapO1, dialogue, 1, player, enemies, items, NPCs, merchants);

        Main.saveGame(s, SAVE_LOAD_DIRECTORY + "/" + SAVE_FILENAME);

        File f = new File(SAVE_LOAD_DIRECTORY + "/" + SAVE_FILENAME);

        assertTrue(f.exists());
    }

    // FIXME loading the game does NOT work presently.
    @Test
    public void testLoadGame() {
        State s = Main.loadGame(SAVE_LOAD_DIRECTORY + "/" + "load-testing.json");

        System.out.println(s);

        Main.mainPhaseTwo(s);
    }

}
