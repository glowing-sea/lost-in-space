package base;

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
}
