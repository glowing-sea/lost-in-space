package base;

import utility.Utility;

/**
 * This the main class where the game runs.
 */

public class Main {
    public static void main(String[] args) {

        // Get and print the initial game state from starr
        State state = GameConfiguration.GAME_STATES[0];
        System.out.println(state);
        // Pass the initial state
        keyEventHandler keyEventHandler = new keyEventHandler();
        keyEventHandler.keyEventHandler(state);
    }

    /**
     * load a game from file
     * @param fileName
     * @return the loaded game state
     */
    private State loadGame(String fileName) {

        return Utility.readFromJSON(fileName);
    }

    /**
     * save a game to file
     * @param s
     * @param fileName
     */
    private void saveGame(State s, String fileName) {

        Utility.writeToJSON(fileName, s);
    }
}
