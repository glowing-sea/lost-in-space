package tests;

import base.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.GameStateExamples;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This unit tests are meant for the Save and Load Feature
 * @author Albert Yu
 */
public class SaveLoadTests {

    private String SAVE_LOAD_DIRECTORY = "C:/game_engine";
    private String SAVE_FILENAME = "save-load-testing.json";

    /**
     * Making the game engine working directory test.
     */
    @Test
    public void testMakeDirectory() {
        Main.makeDirectory();
        File f = new File(Main.SAVE_LOAD_DIRECTORY);

        assertTrue(f.exists());
    }

    /**
     * Testing the save game function.
     */
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

    /**
     * Testing the load game function.
     */
    @Test
    public void testLoadGame() {
        State state = Main.loadGame(SAVE_LOAD_DIRECTORY + "/" + "load-testing.json");

        System.out.println(state);

        assertEquals(0, state.player.getLoc().getX());
        assertEquals(0, state.player.getLoc().getY());

        // TRY (1)
        // Pass the initial state. FIXME TRY (1): does NOT WORK: (same error)
        //keyEventHandler keyEventHandler = new keyEventHandler();
        //keyEventHandler.keyEventHandler(s);

        //Main.mainPhaseTwo(s);
/**
        // TRY (2)
        Scanner scanner = new Scanner(System.in);
        String input;
        // keep taking key input until a "q" is found.
        do {
            input = scanner.next();     // FIXME TRY(2) it ERRORS out here. (same error)
            String[] tokens = input.split("-");
            String value = "";
            if (tokens.length == 2)
                value = tokens[1];

            // Update the game state according to the inputs
            int op = -1;
            switch (tokens[0]) {
                // Movement & Interaction
                case "w","s","a","d" -> state.player.move(state, tokens[0], 1);
                case "ww","ss","aa","dd" -> state.player.move(state, tokens[0], 2);
                case "fw","fa","fs","fd", "f" -> Player.interact(state, input);

                //talking with NPCs
                case "A","(A)" -> op = 1;
                case "B","(B)" -> op = 2;
                case "L","(L)" -> op = 3;
                // Inventory Management
                case "use" -> Player.takeOutItem(state, value, 0);
                case "drop" -> Player.takeOutItem(state, value, 1);
                case "view" -> Player.takeOutItem(state, value, 2);

                // Special Inputs
                case "tips" -> {for (String st : GameConfiguration.tips){state.messageBox.putMessage(st);}}
                case "quest" -> Player.getQuest(state); //print current location and destination location
                case "levelup" -> {
                    if (!state.isFinish())
                        state.gameLevelUp(GameConfiguration.GAME_STATES[state.level + 1]);} // For testing only, direct get to the next level.

                // save
                // saving the game WORKS
                case "save" -> Main.saveGame(state);
                // load (1)
                // 1. need to exit this state
                // 2. need to return to main?
                // 3. need to execute keyEventHandler keyEventHandler = new keyEventHandler();
                //                    keyEventHandler.keyEventHandler(state);
                // again
                //
                // load (2)
                // FIXME loading the game does NOT work presently
                case "load" -> {State s = Main.loadGame(Main.SAVE_LOAD_DIRECTORY + "/" + Main.SAVE_FILENAME);
                    Main.mainPhaseTwo(s);}

                case "q" -> { // Should we save our game here? --Zhishang
                    System.out.println("=== Thank you for playing our game. See you soon. ===");
                    continue;
                }

                default -> {
                    state.messageBox.putMessage("Sorry, '" + input + "' is not a value input.");
                }
            }

            NPC npc1= null;
            for (NPC npc: state.NPCs){
                if(npc.contacting){
                    npc1 = npc;
                    break;
                }
            }
            if(npc1!=null) {
                npc1.setOperations(op);
                npc1.interact(state);
            }

            // If the player has not reached the final game level, check if the player can proceed to the next level
            if (!state.isFinish()){
                int level = state.level;
                GameLevelUpRequirement levelUpRequirement = GameConfiguration.LEVEL_UP_REQUIREMENTS[level];
                if (levelUpRequirement.requirementSatisfied(state)){
                    state.gameLevelUp(GameConfiguration.GAME_STATES[level + 1]);
                }
            }
            // If the player's hp become zero, the game is over.
            if (state.isGameOver()){
                System.out.println("=== Oh no, you lost all your HP. ===");
                break;
            }

            System.out.println(state);
        } while (!Objects.equals(input, "q"));
 **/
    }

}
