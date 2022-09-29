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

        State state = null;

        if(args.length == 0) {
            // Get and print the initial game state from starr
            state = GameConfiguration.GAME_STATES[0];
            System.out.println(state);
        }
        else if(args.length == 1) {
            state = loadGame(SAVE_LOAD_DIRECTORY + "/" + SAVE_FILENAME);
            System.out.println(state);
        }
        else {
            System.err.println("Incorrect number of arguments.");
            System.exit(1);
        }
        // Ensure save load directory is present in local machine
        makeDirectory();

        // Pass the initial state
        keyEventHandler keyEventHandler = new keyEventHandler();
        keyEventHandler.keyEventHandler(state);
    }

    /**
     * This function is used for loading a game state.
     * // FIXME to be deleted
     * @param s State
     */
    public static void mainPhaseTwo(State s) {
        // Pass the new state
        keyEventHandler keyEventHandler = new keyEventHandler();
        keyEventHandler.keyEventHandler(s);
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
     * save a game to file
     * this method is only used for unit case testing.
     * @param s
     * @param filename
     */
    public static void saveGame(State s, String filename) {
        Utility.writeToJSON(filename, s);
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
            // do nothing
            //System.out.println(SAVE_LOAD_DIRECTORY + " already exists.");
        }
        else {
            System.err.println(SAVE_LOAD_DIRECTORY + " could not be created.");
            System.exit(1);
        }
    }
}
