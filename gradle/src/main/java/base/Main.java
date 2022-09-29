package base;

import utility.Utility;
import java.io.File;
import java.util.Scanner;


/**
 * This the main class where the game runs.
 */
public class Main {

    public static State loadingState;

    public static String SAVE_LOAD_DIRECTORY = "./saves/";
    public static String SAVE_FILENAME =  "currentSave.json";

    public static void main(String[] args) {


        loadingState = GameConfiguration.GAME_STATES[0];

        // Ensure save load directory is present in local machine
        makeDirectory();

        // Keep listening to the user's input
        Scanner scanner = new Scanner(System.in);
        String input;
        int result;
        do{
            System.out.println(loadingState); // Print out the state
            input = scanner.next();
            result = keyEventHandler.keyEventHandler(loadingState, input);
        } while (result != 100);
    }

    /**
     * This function is used for loading a game state.
     * // FIXME to be deleted
     * @param s State
     */
    public static void mainPhaseTwo(State s) {
        // Pass the new state
        keyEventHandler keyEventHandler = new keyEventHandler();
        keyEventHandler.keyEventHandler(s,null);
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
     * @param s current state
     * @param filename file name
     */
    public static void saveGame(State s, String filename) {
        if (!validFileName(filename)){
            s.messageBox.putMessage("The filename can only contains letters, numbers");
            s.messageBox.putMessage("'-' and '_'");
        } else {
            Utility.writeToJSON(SAVE_LOAD_DIRECTORY + filename + ".json", s);
            s.messageBox.putMessage("Saved successfully! " + "[" + filename + ".json" + "]");
        }
    }

    /**
     * load a game from file
     * @param s current game state
     * @param fileName file name
     */
    public static void loadGame(State s, String fileName) {
        if (!validFileName(fileName)){
            s.messageBox.putMessage("The filename can only contains letters, numbers");
            s.messageBox.putMessage("'-' and '_'");
        } else {
            State sLoad = Utility.readFromJSON(SAVE_LOAD_DIRECTORY + fileName + ".json");
            if (sLoad == null){
                s.messageBox.putMessage("Save not found");
            } else {
                loadingState = sLoad;
                loadingState.messageBox.putMessage("Loaded successfully! " + "[" + fileName + ".json" + "]");
            }
        }
    }


    public static boolean validFileName (String filename){
        if (filename == null || filename.isEmpty())
            return false;
        for (int i = 0; i < filename.length(); i++){
            char c = filename.charAt(i);
            if (!(c == '_' || c == '-' || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')))
                return false;
        }
        return true;
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
