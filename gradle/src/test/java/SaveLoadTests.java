package tests;

import base.*;
import org.junit.jupiter.api.BeforeAll;
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


    private final String SAVE_LOAD_DIRECTORY = "./saves";
    private final String SAVE_FILENAME = "save-load-testing.json";


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

        State s = GameStateExamples.TEST_STATE_ONE;

        Main.saveGameTest(s, SAVE_LOAD_DIRECTORY + "/" + SAVE_FILENAME);

        File f = new File(SAVE_LOAD_DIRECTORY + "/" + SAVE_FILENAME);

        assertTrue(f.exists());
    }

    /**
     * Testing the load game function.
     */
    @Test
    public void testLoadGame() {
        State s1 = Main.loadGameTest(SAVE_LOAD_DIRECTORY + "/" + SAVE_FILENAME);
        State s2 = GameStateExamples.TEST_STATE_ONE;
        assertEquals(s1, s2);
    }
}
