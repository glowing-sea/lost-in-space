package base;

import utility.Utility;

import java.io.File;

/**
 * This the main class where the game runs.
 */

public class Main {

    public static String SAVE_LOAD_DIRECTORY = "C:/game_engine";
    public static String SAVE_FILENAME =  "currentSave.json";

    public static void main(String[] args) {

        // Get and print the initial game state from starr
        State state = GameConfiguration.GAME_STATES[0];
        System.out.println(state);

        // Ensure save load directory is present in local machine
        makeDirectory();

        // Pass the initial state
        keyEventHandler keyEventHandler = new keyEventHandler();
        keyEventHandler.keyEventHandler(state);
    }

    /**
     * load a game from file
     * @param fileName
     * @return the loaded game state
     */
    public static State loadGame(String fileName) {

        return Utility.readFromJSON(fileName);
    }

    /**
     * save a game to file
     * @param s
     */
    public static void saveGame(State s) {

        Utility.writeToJSON(SAVE_LOAD_DIRECTORY + "/" + SAVE_FILENAME, s);
    }

    /**
     * makes the game save / load directory.
     */
    public static void makeDirectory() {
        File f = new File(SAVE_LOAD_DIRECTORY);
        if(f.mkdir()) {
            System.out.println(SAVE_LOAD_DIRECTORY + " created.");
        }
        else if(f.exists()) {
            System.out.println(SAVE_LOAD_DIRECTORY + " already exists.");
        }
        else {
            System.err.println(SAVE_LOAD_DIRECTORY + " could not be created.");
            System.exit(1);
        }
    }
}
