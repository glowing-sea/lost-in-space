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

    private String SAVE_LOAD_DIRECTORY = "./saves";
    private String SAVE_FILENAME = "save-load-testing.json";

    GameStateExamples ex = new GameStateExamples();

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

        State s = ex.TEST_STATE_ONE;

        Main.saveGame(s, SAVE_LOAD_DIRECTORY + "/" + SAVE_FILENAME);

        File f = new File(SAVE_LOAD_DIRECTORY + "/" + SAVE_FILENAME);

        assertTrue(f.exists());
    }

    /**
     * Testing the load game function.
     */
    @Test
    public void testLoadGame() {
        State s1 = Main.loadGame(SAVE_LOAD_DIRECTORY + "/" + SAVE_FILENAME);
        State s2 = ex.TEST_STATE_ONE;
        assertEquals(s1, s2);
    }
}
